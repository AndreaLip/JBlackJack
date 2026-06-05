package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * Panel for setting up a new game, including player's name, amount to play, opponents number,
 * avatar selection, and start game button.
 */
public class NewGamePanel extends BasePanel
{
    /**
     * Singleton instance of NewGamePanel.
     */
    private static NewGamePanel instance;	
    
    /**
     * Background image displayed on the panel.
     */
    private Image backgroundImage;
    
    /**
     * Label to display error messages.
     */
    private JLabel errorLabel;
    
    /**
     * Label for player's name field.
     */
    private JLabel playerName;
    
    /**
     * Text field for entering player's name.
     */
    private JTextField nameField;
    
    /**
     * Label for amount to play field.
     */
    private JLabel amountToPlay;
    
    /**
     * ComboBox for selecting amount to play.
     */
    private JComboBox<Integer> amountBox;
    
    /**
     * Label for avatar selection.
     */
    private JLabel avatarLabel;
    
    /**
     * Label for selecting number of opponents.
     */
    private JLabel opponentsLabel;
    
    /**
     * ComboBox for selecting number of opponents.
     */
    private JComboBox<Integer> opponentsBox;	
	
    /**
     * Map storing avatar buttons.
     */
    private Map<String, JButton> avatarButtons = new HashMap<>();
    
    /**
     * Button for player's selected image.
     */
    private JButton playerImageButton;
    
    /**
     * Button to start the game.
     */
    private JButton startGameButton;

    /**
     * Private constructor initializing NewGamePanel instance.
     * Sets up UI components and layout.
     */
    private NewGamePanel() 
    {
        super();
        getBackButton().setVisible(true);
        
        setLayout(new GridBagLayout()); // Set GridBagLayout for the panel
        
        Image imageContent = loadImageResource(getImagePath() + "newgame.jpg");        
        backgroundImage = new ImageIcon(imageContent).getImage();  
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Configure constraints for the "Back" button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(getBackButton(), gbc);
        
        gbc.insets = new Insets(5, 0, 10, 5);
        
        // Name label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1.5;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        playerName = new JLabel("Enter your nickname:");
        playerName.setFont(new Font("Arial", Font.PLAIN, 24));
        playerName.setForeground(Color.WHITE);
        add(playerName, gbc);

        gbc.insets = new Insets(0, 0, 12, 0); // aligns the textField at the JLabelName
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1.5;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        nameField = new JTextField(6);
        add(nameField, gbc);
        
        gbc.insets = new Insets(5, 0, 10, 5); 
        
        // Amount label and box
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.EAST;
        amountToPlay = new JLabel("Amount to play ($):");
        amountToPlay.setFont(new Font("Arial", Font.PLAIN, 24));
        amountToPlay.setForeground(Color.WHITE);
        add(amountToPlay, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.WEST;
        Integer[] values1 = { null, 25, 50, 100, 250, 500, 1000, 2000, 5000 };
        amountBox = new JComboBox<Integer>(values1);
        amountBox.setPreferredSize(new Dimension(70, 20));
        add(amountBox, gbc);
        
        // Opponents label and box
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        opponentsLabel = new JLabel("Opponents number:");
        opponentsLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        opponentsLabel.setForeground(Color.WHITE);
        add(opponentsLabel, gbc);
        
        gbc.insets = new Insets(10, 0, 0, 0);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        Integer[] values2 = {null, 0, 1, 2, 3 };
        opponentsBox = new JComboBox<Integer>(values2);
        opponentsBox.setPreferredSize(new Dimension(70, 20));
        add(opponentsBox, gbc);
        
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Error label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        gbc.weighty = 0.01;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        errorLabel = new JLabel("Fill in all the fields correctly and press the star button!");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        add(errorLabel, gbc);
        errorLabel.setVisible(false);
        
        // Avatar label
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        avatarLabel = new JLabel("Choose an image for your avatar");
        avatarLabel.setForeground(Color.WHITE);
        avatarLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        add(avatarLabel, gbc);

        // Avatar buttons
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;

        JPanel avatarPanel = createAvatarPanel();        
        avatarPanel.setOpaque(false); // makes the panel invisible
        add(avatarPanel, gbc); // adds the panel to the main panel

        // Start Game button
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        startGameButton = new JButton("START GAME");
        add(startGameButton, gbc);

    }
    
    /**
     * Retrieves the singleton instance of NewGamePanel.
     * @return The NewGamePanel instance.
     */
    public static NewGamePanel getInstance()
    {
        if (instance == null) instance = new NewGamePanel();
        return instance;
    }
    
    /**
     * Retrieves the error label used for displaying validation messages.
     * @return The error label component.
     */
    public JLabel getErrorLabel()
    {
        return errorLabel;
    }
    
    /**
     * Retrieves the combo box for selecting opponents number.
     * @return The opponents number combo box.
     */
    public JComboBox<Integer> getOpponentsBox()
    {
        return opponentsBox;
    }
    
    /**
     * Retrieves the combo box for selecting amount to play.
     * @return The amount to play combo box.
     */
    public JComboBox<Integer> getAmountBox()
    {
        return amountBox;
    }
    
    /**
     * Retrieves the text field for entering player's name.
     * @return The player's name text field.
     */
    public JTextField getNameField()
    {
        return nameField;
    }
    
    /**
     * Sets a new text field for entering player's name.
     * @param newTextField The new text field component.
     */
    public void setNameField(JTextField newTextField)
    {
        nameField = newTextField;
    }

    /**
     * Retrieves the button for starting the game.
     * @return The start game button.
     */
    public JButton getStartGameButton()
    {
        return startGameButton;
    }
    
    /**
     * Retrieves the button for displaying player's selected image.
     * @return The player image button.
     */
    public JButton getPlayerImageButton()
    {
        return playerImageButton;
    }
    
    /**
     * Sets a new button for displaying player's selected image.
     * @param newButton The new button component.
     */
    public void setPlayerImageButton(JButton newButton)
    {
        playerImageButton = newButton;
    }
    
    /**
     * Retrieves the map of avatar buttons.
     * @return The map of avatar buttons.
     */
    public Map<String, JButton> getAvatarButtons()
    {
        return avatarButtons;
    }

    /**
     * Creates a panel containing a grid of buttons and each button represents an avatar
     * @return A {@code JPanel} containing the avatar buttons.
     */
    private JPanel createAvatarPanel()
    {
    	JPanel avatarPanel = new JPanel(new FlowLayout());
        
        String[] avatarNames = {
            "avatar1.jpg", "avatar2.jpg", "avatar3.jpg", "avatar4.jpg", 
            "avatar5.jpg", "avatar6.jpg", "avatar7.jpg", "avatar8.jpg", 
            "avatar9.jpg", "avatar10.jpg", "avatar11.jpg", "avatar12.jpg"
        };

        for (int i = 0; i < avatarNames.length; i++) 
        {
            String avatarName = avatarNames[i];
            Image avatar = loadImageResource("images/avatar/" + avatarName).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JButton avatarButton = createAvatarButton(new ImageIcon(avatar), "avatar" + (i + 1));
            avatarButton.putClientProperty("avatarName", "avatar" + (i + 1)); // Stores the file name as a property
            avatarPanel.add(avatarButton);
            avatarButtons.put("avatar" + (i + 1), avatarButton);
        }
        return avatarPanel;
    }
    
    /**
     * Creates a new avatar button with the specified icon and name.
     * @param icon The icon image for the button.
     * @param avatarName The name of the avatar.
     * @return The created avatar button.
     */
    private JButton createAvatarButton(ImageIcon icon, String avatarName) 
    {
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        return button;
    }
    
    /**
     * Resets all input fields and selections to their default state.
     */
    public void resetFields() 
    {
        nameField.setText("");
        amountBox.setSelectedIndex(0);
        opponentsBox.setSelectedIndex(0);
        playerImageButton = null; // or reset to default image
        errorLabel.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2=(Graphics2D)g;
        if (backgroundImage != null) g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
