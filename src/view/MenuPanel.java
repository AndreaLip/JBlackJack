package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * MenuPanel class representing the main menu panel of the application.
 * It includes buttons for new game, load game, settings, scores, and exit.
 */
public class MenuPanel extends BasePanel
{
    /**
     * Singleton instance of MenuPanel.
     */
    private static MenuPanel instance;
    
    /**
     * Background image displayed on the panel.
     */
    private Image backgroundImage;
    
    /**
     * Button for starting a new game.
     */
    private JButton newGameButton;
    
    /**
     * Button for loading an existing game.
     */
    private JButton loadGameButton;
    
    /**
     * Button for accessing the settings menu.
     */
    private JButton settingsButton;
    
    /**
     * Button for viewing scores.
     */
    private JButton scoresButton;
    
    /**
     * Button for exiting the application.
     */
    private JButton exitButton;
   
    /**
     * Private constructor initializing MenuPanel instance.
     * Sets up UI components and layout.
     */
    private MenuPanel()
    {
        super();          
        setLayout(new GridBagLayout()); // Use GridBagLayout
        
        Image imageContent = loadImageResource(getImagePath() + "menu.png");        
        backgroundImage = new ImageIcon(imageContent).getImage();

        Dimension buttonSize = new Dimension(110, 30);
        
        // Create buttons
        newGameButton = new JButton("NEW GAME");
        newGameButton.setPreferredSize(buttonSize);
        loadGameButton = new JButton("LOAD GAME");
        loadGameButton.setPreferredSize(buttonSize);
        settingsButton = new JButton("SETTINGS");
        settingsButton.setPreferredSize(buttonSize);
        scoresButton = new JButton("SCORES");
        scoresButton.setPreferredSize(buttonSize);
        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(buttonSize);
        
        // Create a golden border for the buttons
        javax.swing.border.Border goldenBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.ORANGE, 2), // Border thickness and color
                BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Inner margin

        // Apply the golden border to all buttons
        newGameButton.setBorder(goldenBorder);
        loadGameButton.setBorder(goldenBorder);
        settingsButton.setBorder(goldenBorder);
        scoresButton.setBorder(goldenBorder);
        exitButton.setBorder(goldenBorder);
        
        // Configure constraints for the margin above the buttons
        GridBagConstraints gbcMargin = new GridBagConstraints();
        gbcMargin.gridx = 0; // Grid position in terms of column
        gbcMargin.gridy = 0; // Grid position in terms of row
        gbcMargin.insets = new Insets(150, 0, 0, 0); // Margin above the buttons (top, left, bottom, right)
        add(new JLabel(), gbcMargin); // Add an empty component with the margin above
        
        // Configure constraints for the buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Space between the buttons (top, left, bottom, and right)
        gbc.gridx = 0; // Grid position in terms of column
        gbc.gridy = GridBagConstraints.RELATIVE; // Grid position in terms of row, position buttons one below the other
        gbc.fill = GridBagConstraints.NONE; // Do not fill horizontally
        gbc.weightx = 0; // Do not use all available horizontal space
        gbc.anchor = GridBagConstraints.CENTER; // Center the buttons
        
        // Add buttons to the panel
        add(newGameButton, gbc);
        add(loadGameButton, gbc);
        add(settingsButton, gbc);
        add(scoresButton, gbc);
        add(exitButton, gbc);
    } 

	/**
     * Retrieves the singleton instance of MenuPanel.
     * @return The MenuPanel instance.
     */
    public static MenuPanel getInstance()
    {
        if (instance == null) instance = new MenuPanel();
        return instance;
    }
    
    /**
     * Retrieves the button for starting a new game.
     * @return The new game button.
     */
    public JButton getNewGameButton()
    {
        return newGameButton;
    }

    /**
     * Retrieves the button for loading an existing game.
     * @return The load game button.
     */
    public JButton getLoadGameButton()
    {
        return loadGameButton;
    }

    /**
     * Retrieves the button for accessing the settings menu.
     * @return The settings button.
     */
    public JButton getSettingsButton()
    {
        return settingsButton;
    }

    /**
     * Retrieves the button for viewing scores.
     * @return The scores button.
     */
    public JButton getScoresButton()
    {
        return scoresButton;
    }

    /**
     * Retrieves the button for exiting the application.
     * @return The exit button.
     */
    public JButton getExitButton()
    {
        return exitButton;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;          
        if (backgroundImage != null) g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}