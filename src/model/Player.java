package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.BJCards.Card;

/**
 * The Player class represents a player in a blackjack game.
 * Each player has a name, an avatar image, a list of cards, money, a bet amount, 
 * a hand value, a deck of cards, number of games played, level, games won, and games lost.
 */
public class Player 
{

    /**
     * The name of the player.
     */
    private String playerName;

    /**
     * The name of the avatar image.
     */
    private String avatarImageName;

    /**
     * The list of cards the player holds.
     */
    private List<Card> cards;

    /**
     * The amount of money the player has.
     */
    private int money;

    /**
     * The amount of the player's current bet.
     */
    private int bet;

    /**
     * The value of the player's current hand.
     */
    private int hand;

    /**
     * The deck of cards associated with the player.
     */
    private BJCards deck;

    /**
     * The number of games played by the player.
     */
    private int played;

    /**
     * The level of the player.
     */
    private int level;

    /**
     * The number of games won by the player.
     */
    private int won;

    /**
     * The number of games lost by the player.
     */
    private int lost;

    /**
     * Private constructor for the builder.
     * Use {@link Player.Builder} to create instances of {@link Player}.
     * 
     * @param builder the Player builder
     */
    private Player(Builder builder) 
    {
        this.playerName = builder.playerName;
        this.avatarImageName = builder.avatarImageName;
        this.cards = builder.cards;
        this.money = builder.money;
        this.bet = builder.bet;
        this.hand = builder.hand;
        this.deck = builder.deck;
        this.played = builder.played;
        this.level = builder.level;
        this.won = builder.won;
        this.lost = builder.lost;
    }

    /**
     * Gets the name of the player.
     * 
     * @return the player's name
     */
    public String getPlayerName() 
    {
        return playerName;
    }

    /**
     * Sets the player's name.
     * 
     * @param playerName the name of the player
     */
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }
    
    /**
     * Gets the name of the avatar image.
     * 
     * @return the avatar image name
     */
    public String getAvatarImageName() 
    {
        return avatarImageName;
    }
    
    /**
     * Sets the name of the avatar image for the player.
     * 
     * @param avatarImageName the name of the avatar image
     */
    public void setAvatarImageName(String avatarImageName)
    {
        this.avatarImageName = avatarImageName;
    }

    /**
     * Gets the amount of money the player has.
     * 
     * @return the player's money
     */
    public int getMoney() 
    {
        return money;
    }
    
    /**
     * Sets the amount of money the player has.
     * 
     * @param money the amount of money
     */
    public void setMoney(int money)
    {
        this.money = money;
    }

    /**
     * Gets the list of cards the player has.
     * 
     * @return the player's cards
     */
    public List<Card> getCards() 
    {
        return cards;
    }

    /**
     * Sets the list of cards the player has.
     * 
     * @param cards the new list of cards
     */
    public void setCards(List<Card> cards) 
    {
        this.cards = cards;
    }

	/**
     * Gets the amount of the player's current bet.
     * 
     * @return the bet amount
     */
    public int getBet() 
    {
        return bet;
    }

    /**
     * Sets the amount of the player's current bet.
     * 
     * @param bet the new bet amount
     */
    public void setBet(int bet) 
    {
        this.bet = bet;
    }

    /**
     * Gets the value of the player's current hand.
     * 
     * @return the hand value
     */
    public int getHand() 
    {
        return hand;
    }

    /**
     * Sets the value of the player's current hand.
     * 
     * @param hand the new hand value
     */
    public void setHand(int hand) 
    {
        this.hand = hand;
    }

    /**
     * Gets the deck of cards associated with the player.
     * 
     * @return the player's deck of cards
     */
    public BJCards getDeck() 
    {
        return deck;
    }

    /**
     * Sets the deck of cards associated with the player.
     * 
     * @param deck the new deck of cards
     */
    public void setDeck(BJCards deck) 
    {
        this.deck = deck;
    }

    /**
     * Gets the number of games played by the player.
     * 
     * @return the number of games played
     */
    public int getPlayed() 
    {
        return played;
    }

    /**
     * Sets the number of games played by the player.
     * 
     * @param played the new number of games played
     */
    public void setPlayed(int played) 
    {
        this.played = played;
    }

    /**
     * Gets the level of the player.
     * 
     * @return the player's level
     */
    public int getLevel() 
    {
        return level;
    }

    /**
     * Sets the level of the player.
     * 
     * @param level the new level of the player
     */
    public void setLevel(int level) 
    {
        this.level = level;
    }

    /**
     * Gets the number of games won by the player.
     * 
     * @return the number of games won
     */
    public int getWon() 
    {
        return won;
    }

    /**
     * Sets the number of games won by the player.
     * 
     * @param won the new number of games won
     */
    public void setWon(int won) 
    {
        this.won = won;
    }

    /**
     * Gets the number of games lost by the player.
     * 
     * @return the number of games lost
     */
    public int getLost() 
    {
        return lost;
    }

    /**
     * Sets the number of games lost by the player.
     * 
     * @param lost the new number of games lost
     */
    public void setLost(int lost) 
    {
        this.lost = lost;
    }
    
    /**
     * Creates a new instance of BJCards representing a new deck of cards.
     * 
     * @return a new BJCards object representing a new deck of cards
     */
    public BJCards newDeck()
    {
        BJCards deck = new BJCards();
        return deck;
    }

    /**
     * Builder class for creating instances of {@link Player}.
     */
    public static class Builder 
    {
        private String playerName;
        private String avatarImageName;
        private List<Card> cards;
        private int money;
        private int bet;
        private int hand;
        private BJCards deck;
        private int played;
        private int level;
        private int won;
        private int lost;

        /**
         * Constructs a new Builder for {@link Player}.
         * 
         * @param playerName the name of the player
         * @param avatarImageName the name of the avatar image
         */
        public Builder(String playerName, String avatarImageName) 
        {
            this.playerName = playerName;
            this.avatarImageName = avatarImageName;
            this.cards = new ArrayList<>();
        }

        /**
         * Sets the starting money for the player.
         * 
         * @param money the starting money
         * @return the Builder instance
         */
        public Builder money(int money) 
        {
            this.money = money;
            return this;
        }
        
        /**
         * Sets the list of cards the player has.
         * 
         * @param cards the new list of cards
         * @return the Builder instance
         */
        public Builder cards(List<Card> cards) 
        {
            this.cards = cards;
            return this;
        }

        /**
         * Sets the amount of the player's current bet.
         * 
         * @param bet the new bet amount
         * @return the Builder instance
         */
        public Builder bet(int bet) 
        {
            this.bet = bet;
            return this;
        }

        /**
         * Sets the value of the player's current hand.
         * 
         * @param hand the new hand value
         * @return the Builder instance
         */
        public Builder hand(int hand) 
        {
            this.hand = hand;
            return this;
        }

        /**
         * Sets the deck of cards associated with the player.
         * 
         * @return the Builder instance
         */
        public Builder deck() 
        {
            this.deck = new BJCards();
            return this;
        }

        /**
         * Sets the number of games played by the player.
         * 
         * @param played the new number of games played
         * @return the Builder instance
         */
        public Builder played(int played) 
        {
            this.played = played;
            return this;
        }

        /**
         * Sets the level of the player.
         * 
         * @param level the new level of the player
         * @return the Builder instance
         */
        public Builder level(int level) 
        {
            this.level = level;
            return this;
        }

        /**
         * Sets the number of games won by the player.
         * 
         * @param won the new number of games won
         * @return the Builder instance
         */
        public Builder won(int won) 
        {
            this.won = won;
            return this;
        }

        /**
         * Sets the number of games lost by the player.
         * 
         * @param lost the new number of games lost
         * @return the Builder instance
         */
        public Builder lost(int lost) 
        {
            this.lost = lost;
            return this;
        }

        /**
         * Builds and returns the {@link Player} instance.
         * 
         * @return the created Player instance
         */
        public Player build() 
        {
            return new Player(this);
        }
    }

    /**
     * Generates a hash code for the player based on the avatar image name and player name.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() 
    {
        return Objects.hash(avatarImageName, playerName);
    }

    /**
     * Compares this player to the specified object for equality.
     * 
     * @param obj the object to compare with
     * @return true if the players are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        return Objects.equals(avatarImageName, other.avatarImageName) && Objects.equals(playerName, other.playerName);
    }

    /**
     * Returns a string representation of the player.
     * 
     * @return a string with the player's name, avatar image name, and money
     */
    @Override
    public String toString() 
    {
        return playerName + " " + avatarImageName + " " + money;
    }
    
    /**
     * Returns a string representation of the human player.
     * 
     * @return a string representation of the player including playerName, avatarImageName, money, 
     * played, won, lost games, and level
     */
    public String hptoString()
    {
    	return toString() + " " + played + " " + won + " " + lost + " " + level;
    }
}
