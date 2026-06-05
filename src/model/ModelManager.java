package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import model.BJCards.Card;

/**
 * The ModelManager class manages the state and logic of the blackjack game.
 * It includes methods for handling players, bets, card distribution, and game updates.
 */
public class ModelManager extends Observable 
{
	/**
     * The singleton instance of ModelManager.
     */
    private static ModelManager instance;
    
    /**
     * The human player in the game.
     */
    private Player hp;
    
    /**
     * The dealer in the game.
     */
    private Player dealer;
    
    /**
     * The list of opponent players.
     */
    private List<Player> opponents;     

    
    /**
     * Constructs a ModelManager.
     */
    private ModelManager() 
    {
    }
    
    /**
     * Gets the singleton instance of the ModelManager.
     * 
     * @return the singleton instance of ModelManager
     */
    public static ModelManager getInstance() 
    {
        if (instance == null) instance = new ModelManager();
        return instance;
    }
    
    /**
     * Gets the human player.
     * 
     * @return the human player
     */
    public Player getHp()
    {
        return hp;
    }

    /**
     * Sets the human player.
     * 
     * @param hp the human player to set
     */
    public void setHp(Player hp)
    {
        this.hp = hp;
    }

    /**
     * Gets the list of opponent players.
     * 
     * @return the list of opponents
     */
    public List<Player> getOpponents()
    {
        return opponents;
    }

    /**
     * Sets the list of opponent players.
     * 
     * @param opponents the list of opponents to set
     */
    public void setOpponents(List<Player> opponents)
    {
        this.opponents = opponents;
    }

    /**
     * Sets the dealer.
     * 
     * @param dealer the dealer to set
     */
    public void setDealer(Player dealer)
    {
        this.dealer = dealer;
    }

    /**
     * Gets the dealer.
     * 
     * @return the dealer
     */
    public Player getDealer()
    {
        return dealer;
    }

    /**
     * Inserts the first bet for the human player and each opponent.
     */
    public void insertFirstBet()
    {
        hp.setMoney(hp.getMoney() - hp.getBet());
        Random random = new Random();
        for (Player pl : opponents)
        {
        	if (pl.getMoney() > 0)
        	{
	        	List<Integer> bets = possibleBets(pl);
	            int size = bets.size();
	            if (size < 8) size = 8;
	            int randomElement = bets.get(random.nextInt(size / 8)); 
	            pl.setBet(randomElement);
	            pl.setMoney(pl.getMoney() - randomElement);
        	}
        }
        updateFirstBets();
    }

    /**
     * Gets the possible bets for a player.
     * 
     * @param p the player
     * @return a list of possible bets
     */
    public List<Integer> possibleBets(Player p)
    {
        int maxBet = p.getMoney();
        List<Integer> bets = new ArrayList<>();
        int b = 25;            
        while (b <= maxBet)
        {
            bets.add(b);
            b = b + 25;            
        }
        if (bets.isEmpty()) bets.add(0);
        return bets;
    }

    /**
     * Distributes two cards to each player and the dealer.
     * Shuffles the deck if there are less than 30 cards remaining.
     */
    public void distributeCards()
    {
        // When the deck has less than 30 cards, the dealer shuffles it
        if(dealer.getDeck().countCards() < 30) dealer.setDeck(dealer.newDeck());
		String[] op = namesOpponents(opponents);

        for (int i = 0; i < 2; i++)    
        {
			hp.getCards().add(dealer.getDeck().dealCard());
			for (String s : op)
	        {
	        	Player p = getPlayerByName(opponents, s);
	        	p.getCards().add(dealer.getDeck().dealCard());
            }	
            dealer.getCards().add(dealer.getDeck().dealCard());
        }
        hp.setHand(handValue(hp));
        for (String s : op)
        {
        	Player p = getPlayerByName(opponents, s);
               p.setHand(handValue(p));
        }
        dealer.setHand(handValue(dealer));
        updateFirstCards();
    }

    /**
     * Calculates the value of a player's hand.
     * 
     * @param p the player
     * @return the value of the player's hand
     */
    private int handValue(Player p)
    {
        int r = 0;
        boolean acePresent = false;
        for (Card c : p.getCards())
        {
            if (c.getJbvalue() == 1) acePresent = true;
            r += c.getJbvalue();
        }
        if (acePresent && r < 12) r = r + 10;
        return r;
    }

    /**
     * Deals a card to the human player.
     */
    public void getHpCard() 
    {
        getCard(hp);
        updateHpCard();
    }

    /**
     * Deals a card to a player.
     * 
     * @param p the player
     */
    private void getCard(Player p)
    {
        Card c = dealer.getDeck().dealCard();
        p.getCards().add(c);
        p.setHand(handValue(p));
    }

    /**
     * Determines whether a player should take another card based on their hand value.
     * 
     * @param p the player
     * @return true if the player should stay, false otherwise
     */
    private boolean choiceOfHand(Player p)
    {
        Random randomChoice = new Random();
        int hand = p.getHand();
        if (hand < 11) getCard(p);
        else if(hand >= 11 && hand < 18)  
        {
            int i = randomChoice.nextInt(2);
            if (i == 1) 
            {
                doubleDown(p);
            }
            getCard(p);
        }
        else if(hand >= 18 && hand < 22) return true;
        return false;
    }

    /**
     * Doubles down a player's bet.
     * 
     * @param p the player
     */
    public void doubleDown(Player p)
    {
        if (p.getMoney() >= p.getBet()) 
        {
            p.setMoney(p.getMoney() - p.getBet());
            p.setBet(p.getBet() * 2);
        }
    }

    /**
     * Executes the turns for all opponents and the dealer.
     */
    public void otherShifts()
    {
    	
        for (String s : namesOpponents(opponents)) 
        {
        	Player p = getPlayerByName(opponents, s);
        	boolean stay = false;
            while (p.getHand() < 22 && !stay) stay = choiceOfHand(p);
        }        
        while (dealer.getHand() < 17) getCard(dealer);
        updateCards();
    }

    /**
     * Checks the hands of all players and determines the outcome of the game.
     */
    public void checkHands() 
    {
        String message = "false";
        int dealerHand = dealer.getHand();
        System.out.println("dealer's hand: " + dealerHand + ", human player's hand: " + hp.getHand());
        if (dealerHand > 21)  // Dealer busts
        { 
            if (hp.getHand() <= 21) 
            {
                hp.setMoney(hp.getMoney() + hp.getBet() * 2); // Payout double the bet
                hp.setWon(hp.getWon() + 1);
                if (hp.getWon() > 0 && hp.getWon() % 3 == 0) 
                {
                	hp.setLevel(hp.getLevel() + 1);
                    System.out.println("level up");
                }
                message = "true";
            } 
            else hp.setLost(hp.getLost() + 1);    
        } 
        else 
        {
            if (hp.getHand() <= 21 && hp.getHand() > dealerHand) 
            {
                hp.setMoney(hp.getMoney() + hp.getBet() * 2); // Payout double the bet
                hp.setWon(hp.getWon() + 1);
                if (hp.getWon() > 0 && hp.getWon() % 3 == 0) 
                {
                	hp.setLevel(hp.getLevel() + 1);
                    System.out.println("level up");
                }
                message = "true";
            } 
            else hp.setLost(hp.getLost() + 1);
        }        
        hp.setPlayed(hp.getPlayed() + 1);
        hp.setBet(0);
        hp.setHand(0);
        hp.getCards().clear();

        for (String s : namesOpponents(opponents)) 
        {
        	Player p = getPlayerByName(opponents, s);
        	
            if (dealerHand > 21) // Dealer busts
            { 
                if (p.getHand() <= 21) p.setMoney(p.getMoney() + p.getBet() * 2); // Payout double the bet
            } 
            else 
            {
                if (p.getHand() <= 21 && p.getHand() > dealerHand) p.setMoney(p.getMoney() + p.getBet() * 2); // Payout double the bet
            }
            p.setBet(0);
            p.setHand(0);
            p.getCards().clear();
        }
        dealer.setHand(0);
        dealer.getCards().clear();
        updateGame(message);
    }
    
    /**
     * Retrieves a Player object from the list based on the player's name.
     * 
     * @param op   the list of Player objects
     * @param name the name of the player to search for
     * @return the Player object with the specified name, an exception is thrown if it is not found
     */
    public static Player getPlayerByName(List<Player> op, String name) 
    {
        return op.stream()
                 .filter(p -> p.getPlayerName().equals(name))
                 .findFirst()
                 .orElseThrow(() -> new IllegalArgumentException("Player not found: " + name));
    }

    /**
     * Retrieves an array of names of opponents who have placed a bet.
     * 
     * @param op the list of Player objects
     * @return an array of player names who have placed a bet (bet > 0)
     */
    public static String[] namesOpponents(List<Player> op) 
    {
        return op.stream().filter(p -> p.getBet() > 0)
        				  .map(p -> p.getPlayerName())
        				  .toArray(String[]::new);    
    }

    // Methods to notify and update the view 

    /**
     * Notifies observers to update the first bets.
     */
    public void updateFirstBets()
    {
        setChanged();
        notifyObservers("bets");
    }

    /**
     * Notifies observers to update the first cards dealt.
     */
    public void updateFirstCards()
    {
        setChanged();
        notifyObservers("cards1");
    }

    /**
     * Notifies observers to update the cards dealt.
     */
    public void updateCards()
    {
        setChanged();
        notifyObservers("cards2");
    }
    
    /**
     * Notifies observers to update the human player's card.
     */
    public void updateHpCard()
    {
        setChanged();
        notifyObservers("hp");
    }

    /**
     * Notifies observers to update the game state with a message.
     * 
     * @param message the message to notify observers with
     */
    public void updateGame(String message)
    {
        setChanged();
        notifyObservers(message);
    }

    /**
     * Notifies observers to update the game state.
     */
    public void updateGameState()
    {
        setChanged();
        notifyObservers();
    }
}

