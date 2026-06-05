package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * SettingsPanel extends BasePanel and provides a settings menu for the application.
 * It includes buttons for various settings options and a background image.
 */
public class SettingsPanel extends BasePanel {

    /** 
     * Singleton instance of SettingsPanel 
     */
    private static SettingsPanel instance;

    /** 
     * Background image for the panel 
     */
    private Image backgroundImage;

    /** 
     * Buttons for different settings options 
     */
    private JButton button1, button2, button3, button4, button5;

    /**
     * Private constructor initializes the settings panel.
     * Sets up layout, adds buttons, and configures background image.
     */
    private SettingsPanel() 
    {
        super(); // Call superclass constructor (BasePanel)

        // Make the back button visible
        getBackButton().setVisible(true);

        // Set layout to GridBagLayout
        setLayout(new GridBagLayout());
        Image imageContent = loadImageResource(getImagePath() + "settings.jpg");        
        backgroundImage = new ImageIcon(imageContent).getImage();
        
        // Initialize GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Configure constraints for the "Back" button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(getBackButton(), gbc);

        // Reduce vertical space between buttons
        gbc.insets = new Insets(0, 0, 0, 100);
        gbc.anchor = GridBagConstraints.EAST;

        // Add buttons with custom dimensions
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.15;
        gbc.weighty = 0.15;

        Dimension buttonSize = new Dimension(200, 30); // Width 200, Height 30

        button1 = new JButton("Video");
        button1.setPreferredSize(buttonSize);
        add(button1, gbc);

        gbc.gridy++;
        button2 = new JButton("Audio");
        button2.setPreferredSize(buttonSize);
        add(button2, gbc);

        gbc.gridy++;
        button3 = new JButton("Game");
        button3.setPreferredSize(buttonSize);
        add(button3, gbc);

        gbc.gridy++;
        button4 = new JButton("Controller");
        button4.setPreferredSize(buttonSize);
        add(button4, gbc);

        gbc.gridy++;
        button5 = new JButton("Credits");
        button5.setPreferredSize(buttonSize);
        add(button5, gbc);

        // Fill remaining space
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JLabel(), gbc);
    }

    /**
     * Returns the singleton instance of SettingsPanel.
     *
     * @return The singleton instance of SettingsPanel.
     */
    public static SettingsPanel getInstance() 
    {
        if (instance == null)
            instance = new SettingsPanel();
        return instance;
    }

    /**
     * Overrides paintComponent to draw the background image.
     *
     * @param g The Graphics context.
     */
    @Override
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;
        if (backgroundImage != null) g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}