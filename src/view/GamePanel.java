package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.Timer;
import controller.AudioManager;
import model.ModelManager;
import model.Player;

/**
 * GamePanel class represents the main game panel where the gameplay occurs.
 * It contains components for displaying the dealer, human player, opponents, and action buttons.
 */
public class GamePanel extends BasePanel
{
    /**
     * Singleton instance of GamePanel.
     */
    private static GamePanel instance;

    /**
     * ModelManager instance to manage the game state.
     */
    private ModelManager mm;

    /**
     * Background image for the panel.
     */
    private Image backgroundImage;

    /**
     * Number representing the save state.
     */
    private int saveNumber;
            
    // dealer components
    
    /** 
     * Dealer player instance. 
     */ 
    private Player dealer;
    
    /** 
     * Dealer avatar button and button representing the deck of cards. 
     */
    private JButton dealerAvatar, cards;
    
    /**
     * Panels for dealer components, dealer cards, first card and other cards
     */
    private JPanel dealerPanel, cardsDealerPanel, dCard1, dCard2;

    // human player components
    
    /**
     *  Human player instance.
     */
    private Player humanPlayer;
    
    /**
     * Human player avatar button.
     */
    private JButton hpAvatar;
    
    /**
     *  Panels for the cards of the human player respectively: main, first cards and other cards
     */
    private JPanel hpCards, hpCard1, hpCard2;
    
    /**
     * Labels for the human player's name, money and bets.
     */
    private JLabel hpLabelName, hpLabelMoney, hpLabelBet;

    // opponents components
    
    /**
     * List of opponent players.
     */
    private List<Player> opponents;    
    
    /**
     * Buttons for images of opponents' avatars
     */
    private JButton p1Avatar, p2Avatar, p3Avatar;
    
    /**
     * Panels of opponents for the first card and the others
     */
    private JPanel p1Card1, p1Card2, p2Card1, p2Card2, p3Card1, p3Card2;
    
    /**
     * Opponent' panels for cards and names
     */
    private JPanel p1Cards, p2Cards, p3Cards, p1lp, p2lp, p3lp;
    
    /**
     * Labels for opponent's name
     */
    private JLabel p1LabelName, p2LabelName, p3LabelName;
    
    /**
     * Labels for opponent's money
     */
    private JLabel p1LabelMoney, p2LabelMoney, p3LabelMoney;
    
    /**
     * Labels for opponent's bets
     */
    private JLabel p1LabelBet, p2LabelBet, p3LabelBet;

    // action panel components
    
    /**
     * Labels for displaying games played, games won, lost, player level and in-game requests.
     */
    private JLabel played, level, won, lost, request;
    
    /**
     * Spinner for bet selection
     */
    private JSpinner action2;
    
    /**
     * action buttons for in-game decisions
     */
    private JButton action1, action3, action4, action5;

    /**
     * Private constructor for GamePanel.
     * Initializes the panel layout and components.
     */
    private GamePanel()
    {
        super();
        getBackButton().setVisible(true);

        // Reference to the ModelManager
        mm = ModelManager.getInstance();

        // Set background image
        Image imageContent = loadImageResource(getImagePath() + "gamePanel.jpg");        
        backgroundImage = new ImageIcon(imageContent).getImage();

        // Set layout for the panel
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Configure constraints for the "Back" button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 4; 
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(getBackButton(), gbc);
        
        // Dealer name label
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel nameDealer = new JLabel("Dealer");
        nameDealer.setFont(nameDealer.getFont().deriveFont(14f));
        nameDealer.setForeground(Color.WHITE);
        add(nameDealer, gbc);
        
        gbc.insets = new Insets(0, 5, 5, 5);

        // Dealer avatar and panel
        dealerAvatar = createAvatar();
        dealerAvatar.setContentAreaFilled(true);
        dealerAvatar.setBorderPainted(true);
        dealerAvatar.setPreferredSize(new Dimension(80,80));
        setImageAvatar("dealer", dealerAvatar);
        dealerPanel = createAvatarPanel(dealerAvatar);        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1; 
        gbc.weighty = 0; 
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER; 
        add(dealerPanel, gbc);
        
        // Deck of cards next to the dealer
        gbc.insets = new Insets(2, 15, 2, 2);
        gbc.anchor = GridBagConstraints.SOUTH; 
        cards = new JButton();
        cards.setPreferredSize(new Dimension(30, 50));
        cards.setContentAreaFilled(false);
        cards.setBorderPainted(false);
        setImageCard("BACK", cards);
        gbc.gridx = 1;
        gbc.gridy = 0;        
        dealerPanel.add(cards, gbc);
        gbc.fill = GridBagConstraints.BOTH;
                
        gbc.insets = new Insets(2, 2, 2, 2);

        // Dealer cards panel
        dCard1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        dCard2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        cardsDealerPanel = createCardsPanel(dCard1, dCard2);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1; 
        gbc.weighty = 0; 
        gbc.gridwidth = 4; 
        add(cardsDealerPanel, gbc);
        
        // Empty component to occupy remaining space
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        JPanel p = new JPanel();
        request = new JLabel("How much do you want to bet?");
        request.setForeground(Color.RED);  
        request.setFont(request.getFont().deriveFont(18f));
        p.add(request, gbc);
        p.setOpaque(false);
        add(p, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;

        // Human player components
        hpCard1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        hpCard2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        hpCards = createCardsPanel(hpCard1, hpCard2);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 0; 
        add(hpCards, gbc);
        
        hpAvatar = createAvatar();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 0; 
        add(hpAvatar, gbc);
        
        hpLabelName = new JLabel("");        
        hpLabelMoney = new JLabel("");
        System.out.println(hpLabelName.getText() + " " + hpLabelMoney.getText());
        JPanel hplp = addLabelsPanel(hpLabelName, hpLabelMoney);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(hplp, gbc);
        
        // Player 1 components
        p1Card1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        p1Card2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        p1Cards = createCardsPanel(p1Card1, p1Card2);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weighty = 0; 
        add(p1Cards, gbc);
        
        p1Avatar = createAvatar();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weighty = 0;
        add(p1Avatar, gbc);
        
        p1LabelName = new JLabel("");
        p1LabelMoney = new JLabel("");
        p1lp = addLabelsPanel(p1LabelName, p1LabelMoney);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(p1lp, gbc);
        
        // Player 2 components
        p2Card1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        p2Card2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        p2Cards = createCardsPanel(p2Card1, p2Card2);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weighty = 0; 
        add(p2Cards, gbc);
        
        p2Avatar = createAvatar();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weighty = 0;
        add(p2Avatar, gbc);
        
        p2LabelName = new JLabel("");
        p2LabelMoney = new JLabel("");
        p2lp = addLabelsPanel(p2LabelName, p2LabelMoney);
        gbc.gridx = 2;
        gbc.gridy = 6;
        add(p2lp, gbc);
        
        // Player 3 components
        p3Card1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        p3Card2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        p3Cards = createCardsPanel(p3Card1, p3Card2);
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.weighty = 0;
        add(p3Cards, gbc);
        
        p3Avatar = createAvatar();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.weighty = 0; 
        add(p3Avatar, gbc);
        
        p3LabelName = new JLabel("");
        p3LabelMoney = new JLabel("");
        p3lp = addLabelsPanel(p3LabelName, p3LabelMoney);
        gbc.gridx = 3;
        gbc.gridy = 6;
        add(p3lp, gbc);
        
        gbc.fill = GridBagConstraints.BOTH; // Occupies all horizontal space
        gbc.gridwidth = 4; 

        // Action buttons panel
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0;
        gbc.weighty = 0;
        JPanel actionPanel = new JPanel(new FlowLayout());
        add(actionPanel, gbc);
        
        actionPanel.add(new JLabel("Played: "));
        played = new JLabel("0");
        actionPanel.add(played);
        
        actionPanel.add(Box.createHorizontalStrut(20));  
        
        actionPanel.add(new JLabel("Won: "));
        won = new JLabel("0");
        actionPanel.add(won);
        
        actionPanel.add(Box.createHorizontalStrut(20));      
        
        actionPanel.add(new JLabel("Lost: "));
        lost = new JLabel("0");
        actionPanel.add(lost);
        
        actionPanel.add(Box.createHorizontalStrut(20));      

        actionPanel.add(new JLabel("Level: "));
        level = new JLabel("0");
        actionPanel.add(level);
        
        actionPanel.add(Box.createHorizontalStrut(50));       
        
        actionPanel.add(action1 = new JButton("Bet"));
        action1.setPreferredSize(new Dimension(60, 30));
        
        actionPanel.add(action2 = new JSpinner(new SpinnerListModel()));
        action2.setPreferredSize(new Dimension(60, 30));
        
        actionPanel.add(action3 = new JButton("+ HIT"));
        action3.setPreferredSize(new Dimension(80, 30));
        action3.setEnabled(false);
        
        actionPanel.add(action4 = new JButton("- STAND"));
        action4.setPreferredSize(new Dimension(80, 30));
        action4.setEnabled(false);
        
        actionPanel.add(action5 = new JButton("X2 DD"));
        action5.setPreferredSize(new Dimension(80, 30));
        action5.setEnabled(false);
    }
	
	// View update methods
	
    /**
     * Updates the bet amount and money for a specific player.
     * 
     * @param name The name of the player whose bet and money are to be updated.
     */
	public void updateBet(String name)
	{		
		switch (name)
		{
		case "player1":
			if(p1LabelBet != null)
			{
			Player p1 = ModelManager.getPlayerByName(opponents, name);
			p1LabelBet.setText(p1.getBet()+"");
			p1LabelMoney.setText(p1.getMoney()+"");
			break;
			}
		case "player2":			
			if(p2LabelBet != null)
			{
			Player p2 = ModelManager.getPlayerByName(opponents, name);
			p2LabelBet.setText(p2.getBet()+"");
			p2LabelMoney.setText(p2.getMoney()+"");
			break;
			}
		case "player3":
			if(p3LabelBet != null)
			{
			Player p3 = ModelManager.getPlayerByName(opponents, name);
			p3LabelBet.setText(p3.getBet()+"");
			p3LabelMoney.setText(p3.getMoney()+"");
			}
			break;
		default:
			if(hpLabelBet != null) 
			{
				hpLabelBet.setText(humanPlayer.getBet()+"");
				hpLabelMoney.setText(humanPlayer.getMoney()+"");
			}
		}
	}
	
	/**
	 * Updates the initial bets for all players including the human player.
	 * Uses a timer to update each player's bet and money labels over time.
	 */
	public void updateFirstBets() 
	{
	    // Retrieve names of opponents
	    String[] op = ModelManager.namesOpponents(opponents);
	    
	    // Index to track the current state
	    final int[] currentState = {0};	    
	    
	    // Update human player's bet label and money label
	    hpLabelBet = new JLabel("");
	    addBetPanel(hpCard2, humanPlayer.getBet() + "", hpLabelBet);
	    hpLabelMoney.setText(humanPlayer.getMoney() + "");
	    
	    // Hide the request component
	    request.setVisible(false);

	    // Create a timer that performs an action every 750 milliseconds
	    Timer timer = new Timer(750, new ActionListener() 
	    {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Check if the index is within limits
	            if (currentState[0] < op.length) 
	            {
	                String name = op[currentState[0]];
	                switch (name) 
	                {
	                    case "player1":
	                        // Update player1's bet and money labels
	                        Player p1 = ModelManager.getPlayerByName(opponents, name);
	                        p1LabelBet = new JLabel("");
	                        addBetPanel(p1Card2, p1.getBet() + "", p1LabelBet);
	                        p1LabelMoney.setText(p1.getMoney() + "");
	                        break;
	                    case "player2":
	                        // Update player2's bet and money labels
	                        Player p2 = ModelManager.getPlayerByName(opponents, name);
	                        p2LabelBet = new JLabel("");
	                        addBetPanel(p2Card2, p2.getBet() + "", p2LabelBet);
	                        p2LabelMoney.setText(p2.getMoney() + "");
	                        break;
	                    case "player3":
	                        // Update player3's bet and money labels
	                        Player p3 = ModelManager.getPlayerByName(opponents, name);
	                        p3LabelBet = new JLabel("");
	                        addBetPanel(p3Card2, p3.getBet() + "", p3LabelBet);
	                        p3LabelMoney.setText(p3.getMoney() + "");
	                        break;
	                }
	                // Increment the current state for the next iteration
	                currentState[0]++;
	            } 
	            else {
	                // Stop the timer when all updates are done
	                ((Timer) e.getSource()).stop();
	            }
	        }
	    });
	    // Start the timer
	    timer.start();
	}
	
	/**
	 * Updates the cards for players and the dealer.
	 * Manages the addition of cards to player and dealer panels based on their current state.
	 */
	public void updateCards()
	{
	    String[] op = ModelManager.namesOpponents(opponents);
	    String[] allPlayers = new String[op.length + 1]; // Adding the dealer
	    System.arraycopy(op, 0, allPlayers, 0, op.length);
	    allPlayers[allPlayers.length - 1] = "dealer";

	    // Index to track the current state
	    final int[] currentState = {0};
	    final int[] index = {2, 0};

	    // Create a timer that performs an action every 750 milliseconds
	    Timer timer = new Timer(750, new ActionListener()
	    {
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	            // Check if the index is within limits
	            if (currentState[0] < allPlayers.length)
	            {
	                String name = allPlayers[currentState[0]];
	                switch (name)
	                {
	                    case "player1":
	                        updateBet("player1");
	                        Player p1 = ModelManager.getPlayerByName(opponents, name);
	                        int newCards1 = p1.getCards().size();
	                        index[1] = newCards1;
	                        if (newCards1 > 2)
	                        {
	                            addCard(p1Card1, createCard(p1.getCards().get(index[0]).toString()));
	                            index[0]++;
	                        }
	                        break;
	                    case "player2":
	                        updateBet("player2");
	                        Player p2 = ModelManager.getPlayerByName(opponents, name);
	                        int newCards2 = p2.getCards().size();
	                        index[1] = newCards2;
	                        if (newCards2 > 2)
	                        {
	                            addCard(p2Card1, createCard(p2.getCards().get(index[0]).toString()));
	                            index[0]++;
	                        }
	                        break;
	                    case "player3":
	                        updateBet("player3");
	                        Player p3 = ModelManager.getPlayerByName(opponents, name);
	                        int newCards3 = p3.getCards().size();
	                        index[1] = newCards3;
	                        if (newCards3 > 2)
	                        {
	                            addCard(p3Card1, createCard(p3.getCards().get(index[0]).toString()));
	                            index[0]++;
	                        }
	                        break;
	                    case "dealer":
	                        turnSecondCard();
	                        int newCardsDealer = dealer.getCards().size();
	                        index[1] = newCardsDealer;
	                        if (newCardsDealer > 2)
	                        {
	                            addCard(dCard2, createCard(dealer.getCards().get(index[0]).toString()));
	                            index[0]++;
	                        }
	                        break;
	                }
	                // Increment the current state for the next iteration
	                if (index[0] == index[1])
	                {
	                    currentState[0]++;
	                    index[0] = 2;
	                }
	            }
	            else
	            {
	                ((Timer) e.getSource()).stop();
	                mm.checkHands(); // Check hands when all updates are done
	            }
	        }
	    });
	    // Start the timer
	    timer.start();
	}
	
	/**
	 * Updates the initial cards for players and the dealer.
	 * Manages the addition of initial cards to player and dealer panels based on their current state.
	 */
	public void updateFirstCards()
	{
	    // Get opponent names and add "hpPlayer" at the beginning of the array
	    String[] op = ModelManager.namesOpponents(opponents);
	    String[] allPlayers = new String[op.length + 2]; // Adding two elements: "hpPlayer" and "dealer"
	    allPlayers[0] = "hpPlayer";
	    // Copy elements from 'op' to 'allPlayers' starting from position 1
	    System.arraycopy(op, 0, allPlayers, 1, op.length);
	    // Add dealer at the end of the array
	    allPlayers[allPlayers.length - 1] = "dealer";

	    // Indices to track current state
	    final int[] currentPlayerIndex = {-1};
	    final int[] currentCardIndex = {0};

	    // Create a timer that performs an action every 750 milliseconds
	    Timer timer = new Timer(750, new ActionListener()
	    {
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	            currentPlayerIndex[0]++;
	            if (currentPlayerIndex[0] < allPlayers.length)
	            {
	                String currentPlayer = allPlayers[currentPlayerIndex[0]];
	                switch (currentPlayer)
	                {
	                    case "hpPlayer":
	                        // Add card for the human player
	                        addCard(hpCard2, createCard(humanPlayer.getCards().get(currentCardIndex[0]).toString()));
	                        break;
	                    case "player1":
	                        // Add card for player1
	                        addCard(p1Card2, createCard(ModelManager.getPlayerByName(opponents, "player1").getCards().get(currentCardIndex[0]).toString()));
	                        break;
	                    case "player2":
	                        // Add card for player2
	                        addCard(p2Card2, createCard(ModelManager.getPlayerByName(opponents, "player2").getCards().get(currentCardIndex[0]).toString()));
	                        break;
	                    case "player3":
	                        // Add card for player3
	                        addCard(p3Card2, createCard(ModelManager.getPlayerByName(opponents, "player3").getCards().get(currentCardIndex[0]).toString()));
	                        break;
	                    case "dealer":
	                        // Add card for the dealer
	                        if (currentCardIndex[0] == 1)
	                            addCard(dCard1, createCard("BACK")); // Add a facedown card for dealer's first card
	                        else
	                        {
	                            addCard(dCard1, createCard(dealer.getCards().get(currentCardIndex[0]).toString()));
	                            // Increment the current card index for the next iteration
	                            currentCardIndex[0]++;
	                            currentPlayerIndex[0] = -1; // Reset to process again from the beginning after dealer's cards
	                        }
	                        break;
	                }
	            }
	            else
	            {
	                // Stop the timer after adding all cards
	                ((Timer) e.getSource()).stop();
	                // Enable action buttons action3, action4, action5
	                action3.setEnabled(true);
	                action4.setEnabled(true);
	                action5.setEnabled(true);
	            }
	        }
	    });
	    // Set initial delay based on the number of players and dealer
	    timer.setInitialDelay(allPlayers.length * 750);
	    // Start the timer
	    timer.start();
	}
	
	/**
	 * Updates the visual representation of the human player's card on the GUI.
	 * This method adds the last card from the human player's hand to the specified card panel.
	 */
	public void updateHpCard()
	{
	    addCard(hpCard1, createCard(humanPlayer.getCards().getLast().toString()));
	}

	/**
	 * Turns the dealer's second card face-up on the GUI.
	 * Sets the image of the dealer's second card to the specified button component.
	 */
	public void turnSecondCard()
	{
	    setImageCard(dealer.getCards().get(1).toString(), (JButton) dCard1.getComponent(1));
	}
	
	/**
	 * Updates the game state based on the provided message indicating win or loss.
	 * Displays a message indicating whether the player won or lost, plays corresponding audio,
	 * updates player and opponents' statistics, updates UI elements, and resets game visuals.
	 *
	 * @param message "true" if the player won, "false" if the player lost.
	 */
	public void updateGame(String message)
	{
	    if (message.equals("true"))
	    {
	        request.setText("YOU WON!!!");
	        request.setVisible(true);
	        if (humanPlayer.getWon() > 0 && humanPlayer.getWon() % 3 == 0) {
	            AudioManager.play("level up.wav", false);
	        } else {
	            AudioManager.play("won.wav", false);
	        }
	    }
	    else
	    {
	        request.setText("YOU LOST!!!");
	        request.setVisible(true);
	        AudioManager.play("lost.wav", false);
	    }
	    
	    Timer timer = new Timer(5000, new ActionListener() 
	    {
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	            // Update player statistics on UI
	            played.setText(humanPlayer.getPlayed() + "");
	            won.setText(humanPlayer.getWon() + "");
	            lost.setText(humanPlayer.getLost() + "");
	            level.setText(humanPlayer.getLevel() + "");
	            hpLabelMoney.setText(humanPlayer.getMoney() + "");
	    
	            // Update opponents' money on UI
	            for (Player p : opponents)
	            {
	                String name = p.getPlayerName();
	                switch (name)
	                {
	                    case "player1":
	                        p1LabelMoney.setText(ModelManager.getPlayerByName(opponents, name).getMoney() + "");
	                        break;
	                    case "player2":
	                        p2LabelMoney.setText(ModelManager.getPlayerByName(opponents, name).getMoney() + "");
	                        break;
	                    case "player3":
	                        p3LabelMoney.setText(ModelManager.getPlayerByName(opponents, name).getMoney() + "");
	                        break;
	                }
	            }
	            
	            // Reset UI elements and prepare for the next game round
	            request.setVisible(false);
	            request.setText("How much do you want to bet?");
	            System.out.println("deck has: " + dealer.getDeck().countCards() + " cards"); // DEBUG PRINT
	            removeCards();
	            
	            // Stop the timer after execution
	            ((Timer) e.getSource()).stop();
	        }
	    });
	    // Start the timer
	    timer.setRepeats(false); // Ensure the timer does not repeat the action
	    timer.start();
	}
	
	/**
	 * Checks the total sum of possible human player bets
	 *
	 * @return The total sum of all bets.
	 */
	private int checkBets()
	{
		List<Integer> values = mm.possibleBets(mm.getHp());
	    int sum = 0;
	    for (Integer i : values) sum += i;
	    return sum;
	}

	/**
	 * Removes all cards from player and dealer panels and resets the game interface.
	 * Enables betting actions and updates the request message based on the current betting status.
	 * Plays the game over sound if the human player has run out of money
	 */
	public void removeCards()
	{
	    // Remove cards from all panels
	    JPanel[] panels = {hpCard1, hpCard2, p1Card1, p1Card2, p2Card1, p2Card2, p3Card1, p3Card2, dCard1, dCard2};
	    for (JPanel p : panels) {
	        p.removeAll();
	    }
	    
	    if (checkBets() != 0)
	    {
	        // Enable betting actions
	        action1.setEnabled(true);
	        action2.setEnabled(true);
	        // Set new bet model for action2 spinner
	        action2.setModel(setBets());
	        // Update request message for betting
	        request.setText("How much do you want to bet?");
	        request.setVisible(true);
	    }
	    else 
	    {
	        // Display game over message
	        request.setText("GAME OVER!");
	        request.setVisible(true);
	        // Play game over sound
	        AudioManager.play("game over.wav", false);
	    }
	}

	// Methods for setting
	
	/**
	 * Sets counters for the human player based on the current game state.
	 * Updates played, level, won, and lost counters.
	 */
	public void setCounters()
	{
	    played.setText(mm.getHp().getPlayed() + "");
	    level.setText(mm.getHp().getLevel() + "");
	    won.setText(mm.getHp().getWon() + "");
	    lost.setText(mm.getHp().getLost() + "");
	}

	/**
	 * Sets the image of an avatar button using the provided image path.
	 * 
	 * @param path The file path of the avatar image.
	 * @param avatarButton The JButton component representing the avatar.
	 */
	private void setImageAvatar(String path, JButton avatarButton)
	{
	    Image image = loadImageResource("images/avatar/" + path + ".jpg").getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    	ImageIcon icon = new ImageIcon(image);
        avatarButton.setIcon(icon);
	}

	/**
	 * Sets the image of a card button using the provided image path.
	 * 
	 * @param path The file path of the card image.
	 * @param card The JButton component representing the card.
	 */
	private void setImageCard(String path, JButton card)
	{
	    Image image = loadImageResource("images/cards/" + path + ".jpg").getScaledInstance(30, 50, Image.SCALE_SMOOTH);
	    ImageIcon icon = new ImageIcon(image);
	    card.setIcon(icon);
	}

	/**
	 * Sets the image of chips on a button using the provided image path.
	 * 
	 * @param path The file path of the chips image.
	 * @param chips The JButton component representing the chips.
	 */
	private void setChips(String path, JButton chips)
	{
        Image image = loadImageResource("images/cards/" + path + ".png").getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    ImageIcon icon = new ImageIcon(image);
        chips.setIcon(icon);
        chips.setContentAreaFilled(false); // Do not fill the content area
        chips.setBorderPainted(false); // Do not paint the border
	   
	}

	/**
	 * Sets the avatar image and labels for all players in the game panel.
	 * Updates avatar images, player names, and money labels based on opponent list size.
	 */
	public void setAvatarAndLabels()
	{
	    // Set avatar and labels for human player
	    setImageAvatar(humanPlayer.getAvatarImageName(), hpAvatar);
	    hpLabelName.setText(humanPlayer.getPlayerName());
	    hpLabelName.setForeground(Color.WHITE);
	    hpLabelMoney.setText(humanPlayer.getMoney() + "");
	    hpLabelMoney.setForeground(Color.WHITE);

	    // Set avatar and labels for other players (if any)
	    switch (opponents.size())
	    {
	        case 3:
	            setImageAvatar(opponents.get(2).getAvatarImageName(), p3Avatar);
	            p3LabelName.setText(opponents.get(2).getPlayerName());
	            p3LabelName.setForeground(Color.WHITE);
	            p3LabelMoney.setText(opponents.get(2).getMoney() + "");
	            p3LabelMoney.setForeground(Color.WHITE);
	        case 2:
	            setImageAvatar(opponents.get(1).getAvatarImageName(), p2Avatar);
	            p2LabelName.setText(opponents.get(1).getPlayerName());
	            p2LabelName.setForeground(Color.WHITE);
	            p2LabelMoney.setText(opponents.get(1).getMoney() + "");
	            p2LabelMoney.setForeground(Color.WHITE);
	        case 1:
	            setImageAvatar(opponents.get(0).getAvatarImageName(), p1Avatar);
	            p1LabelName.setText(opponents.get(0).getPlayerName());
	            p1LabelName.setForeground(Color.WHITE);
	            p1LabelMoney.setText(opponents.get(0).getMoney() + "");
	            p1LabelMoney.setForeground(Color.WHITE);
	            break;
	    }
	}
		
	/**
	 * Sets specified player panels invisible based on the provided player number.
	 * 
	 * @param numberPlayer The player number indicating which panels to hide.
	 */
	public void setPlayersInvisible(int numberPlayer)
	{
	    switch (numberPlayer) 
	    {
	        case 0:
	            // Hide player 1 panels
	            p1Cards.setVisible(false);
	            p1Avatar.setVisible(false);
	            p1lp.setVisible(false);
	        case 1:
	            // Hide player 2 panels
	            p2Cards.setVisible(false);
	            p2Avatar.setVisible(false);
	            p2lp.setVisible(false);
	        case 2:
	            // Hide player 3 panels
	            p3Cards.setVisible(false);
	            p3Avatar.setVisible(false);
	            p3lp.setVisible(false);
	            break;
	    }
	}

	/**
	 * Sets all player panels visible.
	 */
	public void setPlayersVisible()
	{
	    // Set all player panels visible
	    p1Cards.setVisible(true);
	    p1Avatar.setVisible(true);
	    p1lp.setVisible(true);
	    p2Cards.setVisible(true);
	    p2Avatar.setVisible(true);
	    p2lp.setVisible(true);
	    p3Cards.setVisible(true);
	    p3Avatar.setVisible(true);
	    p3lp.setVisible(true);
	}

	/**
	 * Sets up a SpinnerListModel for bets based on possible bet amounts.
	 * 
	 * @return The SpinnerListModel containing possible bet amounts.
	 */
	public SpinnerListModel setBets()
	{
	    List<Integer> bets = mm.possibleBets(mm.getHp());
	    SpinnerListModel sm = new SpinnerListModel(bets);
	    return sm;
	}

	/**
	 * Adds a bet panel to a card panel displaying the specified bet amount.
	 * 
	 * @param card The JPanel where the bet panel will be added.
	 * @param bet The string representation of the bet amount.
	 * @param LabelBet The JLabel component displaying the bet amount.
	 */
	public void addBetPanel(JPanel card, String bet, JLabel LabelBet)
	{
	    card.add(createBetPanel(LabelBet, bet));
	    AudioManager.play("bet.wav", false);
	    card.revalidate();
	}

	/**
	 * Adds a card button to a panel displaying the specified card.
	 * 
	 * @param panel The JPanel where the card button will be added.
	 * @param card The JButton representing the card.
	 */
	public void addCard(JPanel panel, JButton card)
	{
	    panel.add(card);
	    AudioManager.play("card.wav", false);
	    panel.revalidate();
	}

	/**
	 * Creates a bet panel displaying the bet amount and chips.
	 * 
	 * @param betAmount The JLabel displaying the bet amount.
	 * @param bet The string representation of the bet amount.
	 * @return The JPanel representing the bet panel.
	 */
	public JPanel createBetPanel(JLabel betAmount, String bet) 
	{
	    JPanel p = new JPanel();
	    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical arrangement
	    JButton chips = new JButton();
	    chips.setPreferredSize(new Dimension(30, 30));  // Use setPreferredSize to specify dimension
	    setChips("chips", chips);        
	    betAmount.setText(bet);
	    betAmount.setForeground(Color.WHITE);
	    betAmount.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center align the label
	    chips.setAlignmentX(Component.CENTER_ALIGNMENT);      // Center align the chips button
	    p.add(chips);
	    p.add(betAmount);
	    p.setOpaque(false);
	    return p;
	}

	/**
	 * Creates a panel displaying player labels for name and money.
	 * 
	 * @param labelName The JLabel displaying the player name.
	 * @param labelMoney The JLabel displaying the player money.
	 * @return The JPanel representing the labels panel.
	 */
	private JPanel addLabelsPanel(JLabel labelName, JLabel labelMoney)
	{
	    JPanel labelsPanel = new JPanel(new GridLayout(2, 2, 2, 2));      
	    JLabel name = new JLabel("Name:");
	    name.setForeground(Color.WHITE);
	    labelsPanel.add(name);
	    labelsPanel.add(labelName);
	    JLabel money =  new JLabel("$:");
	    money.setForeground(Color.WHITE);
	    labelsPanel.add(money);
	    labelsPanel.add(labelMoney);
	    labelsPanel.setOpaque(false);
	    return labelsPanel;
	}

	/**
	 * Creates a panel displaying two card panels vertically arranged.
	 * 
	 * @param panel1 The first JPanel to be displayed.
	 * @param panel2 The second JPanel to be displayed.
	 * @return The JPanel representing the cards panel.
	 */
	private JPanel createCardsPanel(JPanel panel1, JPanel panel2)
	{
	    JPanel cardsPanel = new JPanel(new GridLayout(2, 1, 2, 2)); 
	    panel1.setMinimumSize(new Dimension(100, 55)); // Set minimum size for panel1
	    panel1.setOpaque(false);
	    panel2.setMinimumSize(new Dimension(100, 55)); // Set minimum size for panel2
	    panel2.setOpaque(false);
	    cardsPanel.add(panel1);
	    cardsPanel.add(panel2);
	    cardsPanel.setOpaque(false);
	    return cardsPanel;        
	}

	/**
	 * Creates a card button with the specified card name.
	 * 
	 * @param name The name of the card to be created.
	 * @return The JButton representing the card.
	 */
	public JButton createCard(String name)
	{
	    JButton card = new JButton();
	    Dimension cd = new Dimension(30, 50);
	    card.setPreferredSize(cd);
	    card.setContentAreaFilled(false);
	    card.setBorderPainted(false);
	    setImageCard(name, card);
	    return card;
	}
	
	/**
	 * Creates an avatar button with default settings.
	 * 
	 * @return The JButton representing the avatar.
	 */
	private JButton createAvatar()
	{
	    JButton avatarButton = new JButton();
	    Dimension d = new Dimension(130, 80);
	    avatarButton.setPreferredSize(d);
	    avatarButton.setContentAreaFilled(false);
	    avatarButton.setBorderPainted(false);
	    return avatarButton;
	}

	/**
	 * Creates an avatar panel with the specified avatar button.
	 * 
	 * @param avatarButton The JButton representing the avatar.
	 * @return The JPanel representing the avatar panel.
	 */
	private JPanel createAvatarPanel(JButton avatarButton) 
	{
	    JPanel avatarPanel = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(2, 2, 2, 2);        
	    gbc.gridx = 0;
	    gbc.gridy = 0;       
	    avatarPanel.add(avatarButton, gbc);
	    avatarPanel.setOpaque(false);
	    return avatarPanel;
	}
    
    // set and get
    
	/**
	 * Retrieves the singleton instance of GamePanel.
	 * If the instance is null, creates a new instance.
	 * 
	 * @return The singleton instance of GamePanel.
	 */
	public static GamePanel getInstance()
	{
	    if (instance == null) instance = new GamePanel();
	    return instance;
	}

	/**
	 * Retrieves the save number associated with the game.
	 * 
	 * @return The save number.
	 */
	public int getSaveNumber()
	{
	    return saveNumber;
	}

	/**
	 * Sets the save number for the game.
	 * 
	 * @param saveNumber The save number to set.
	 */
	public void setSaveNumber(int saveNumber)
	{
	    this.saveNumber = saveNumber;
	}

	/**
	 * Retrieves the human player object.
	 * 
	 * @return The human player object.
	 */
	public Player getHumanPlayer()
	{
	    return humanPlayer;
	}

	/**
	 * Sets the human player object.
	 * 
	 * @param humanPlayer The human player object to set.
	 */
	public void setHumanPlayer(Player humanPlayer)
	{
	    this.humanPlayer = humanPlayer;
	}

	/**
	 * Retrieves the list of opponents.
	 * 
	 * @return The list of opponents.
	 */
	public List<Player> getOpponents()
	{
	    return opponents;
	}

	/**
	 * Sets the list of opponents.
	 * 
	 * @param opponents The list of opponents to set.
	 */
	public void setOpponents(List<Player> opponents)
	{
	    this.opponents = opponents;
	}

	/**
	 * Retrieves the dealer player object.
	 * 
	 * @return The dealer player object.
	 */
	public Player getDealer()
	{
	    return dealer;
	}

	/**
	 * Sets the dealer player object.
	 * 
	 * @param dealer The dealer player object to set.
	 */
	public void setDealer(Player dealer)
	{
	    this.dealer = dealer;
	}

	/**
	 * Retrieves the action button 1.
	 * 
	 * @return The JButton representing action button 1.
	 */
	public JButton getAction1()
	{
	    return action1;
	}

	/**
	 * Retrieves the action spinner 2.
	 * 
	 * @return The JSpinner representing action spinner 2.
	 */
	public JSpinner getAction2()
	{
	    return action2;
	}

	/**
	 * Sets the SpinnerListModel for action spinner 2.
	 * 
	 * @param sm The SpinnerListModel to set for action spinner 2.
	 */
	public void setAction2(SpinnerListModel sm)
	{
	    this.action2.setModel(sm);
	}

	/**
	 * Retrieves the action button 3.
	 * 
	 * @return The JButton representing action button 3.
	 */
	public JButton getAction3()
	{
	    return action3;
	}

	/**
	 * Retrieves the action button 4.
	 * 
	 * @return The JButton representing action button 4.
	 */
	public JButton getAction4()
	{
	    return action4;
	}

	/**
	 * Retrieves the action button 5.
	 * 
	 * @return The JButton representing action button 5.
	 */
	public JButton getAction5()
	{
	    return action5;
	}

	/**
	 * Retrieves the JPanel representing hit points cards.
	 * 
	 * @return The JPanel representing hit points cards.
	 */
	public JPanel getHpCards()
	{
	    return hpCards;
	}

	/**
	 * Retrieves the JPanel representing hit points card 2.
	 * 
	 * @return The JPanel representing hit points card 2.
	 */
	public JPanel getHpCard2()
	{
	    return hpCard2;
	}

	/**
	 * Retrieves the JLabel for game request.
	 * 
	 * @return The JLabel for game request.
	 */
	public JLabel getRequest()
	{
	    return request;
	}

	/**
	 * Sets the JLabel for game request.
	 * 
	 * @param request The JLabel for game request to set.
	 */
	public void setRequest(JLabel request)
	{
	    this.request = request;
	}

	/**
	 * Sets the JPanel for hit points card 2.
	 * 
	 * @param hpCard2 The JPanel for hit points card 2 to set.
	 */
	public void setHpCard2(JPanel hpCard2)
	{
	    this.hpCard2 = hpCard2;
	}

	/**
	 * Overrides the toString method to provide a string representation of GamePanel.
	 * 
	 * @return A string representation of GamePanel including human player and opponents.
	 */
	@Override
	public String toString()
	{
	    return "humanPlayer=" + humanPlayer + ", opponents=" + opponents + "]";
	}

	/**
	 * Overrides the paintComponent method to draw the background image.
	 * 
	 * @param g The Graphics object to paint.
	 */
	@Override
	public void paintComponent(Graphics g) 
	{
	    Graphics2D g2=(Graphics2D)g;
	    if (backgroundImage != null) g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}
