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
 * LoadGamePanel class representing the panel for loading saved games.
 * It includes buttons for five save slots and corresponding delete buttons.
 */
public class LoadGamePanel extends BasePanel
{
    /**
     * Singleton instance of LoadGamePanel.
     */
    private static LoadGamePanel instance;

    /**
     * Path to the background image for the panel.
     */
    private final String imagePath = "images/panel images/loadgame.jpg";

    /**
     * Background image displayed on the panel.
     */
    private Image backgroundImage;    

    /**
     * Buttons for loading saved games.
     */
    private JButton button1, button2, button3, button4, button5;

    /**
     * Buttons for deleting saved games.
     */
    private JButton buttond1, buttond2, buttond3, buttond4, buttond5;

    /**
     * Private constructor initializing LoadGamePanel instance.
     * Sets up UI components and layout.
     */
    private LoadGamePanel()
    {
        super();
        getBackButton().setVisible(true);

        setLayout(new GridBagLayout()); // Use GridBagLayout
        
        Image imageContent = loadImageResource(imagePath);        
        backgroundImage = new ImageIcon(imageContent).getImage();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Configure constraints for the "Back" button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(getBackButton(), gbc);

        gbc.insets = new Insets(10, 20, 10, 0); // Reduce vertical space between buttons
        Dimension buttonSize = new Dimension(600, 30); // Width 600, Height 30

        gbc.weightx = 1;
        gbc.weighty = 0.25;

        // Add buttons with custom dimensions
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER; 
        button1 = new JButton("Save 1");
        button1.setPreferredSize(buttonSize);
        add(button1, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; 
        buttond1 = new JButton("X");
        add(buttond1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
        button2 = new JButton("Save 2");
        button2.setPreferredSize(buttonSize);
        add(button2, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; 
        buttond2 = new JButton("X");
        add(buttond2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER; 
        button3 = new JButton("Save 3");
        button3.setPreferredSize(buttonSize);
        add(button3, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; 
        buttond3 = new JButton("X");
        add(buttond3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER; 
        button4 = new JButton("Save 4");
        button4.setPreferredSize(buttonSize);
        add(button4, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; 
        buttond4 = new JButton("X");
        add(buttond4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER; 
        button5 = new JButton("Save 5");
        button5.setPreferredSize(buttonSize);
        add(button5, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; 
        buttond5 = new JButton("X");
        add(buttond5, gbc);        

        gbc.gridy++;
        gbc.weighty = 0.75;
        gbc.fill = GridBagConstraints.BOTH; 
        add(new JLabel(), gbc);
    }

    /**
     * Retrieves the singleton instance of LoadGamePanel.
     * @return The LoadGamePanel instance.
     */
    public static LoadGamePanel getInstance()
    {
        if (instance == null) instance = new LoadGamePanel();
        return instance;
    }

    /**
     * Resets the text of the save buttons.
     */
    public void cleanPanel()
    {
        JButton[] buttons = {button1, button2, button3, button4, button5};
        for (int i = 1; i < buttons.length + 1; i++) buttons[i-1].setText("Save " + i);
    }

    /**
     * Retrieves the button for loading save slot 1.
     * @return The button for save slot 1.
     */
    public JButton getButton1()
    {
        return button1;
    }

    /**
     * Retrieves the button for loading save slot 2.
     * @return The button for save slot 2.
     */
    public JButton getButton2()
    {
        return button2;
    }

    /**
     * Retrieves the button for loading save slot 3.
     * @return The button for save slot 3.
     */
    public JButton getButton3()
    {
        return button3;
    }

    /**
     * Retrieves the button for loading save slot 4.
     * @return The button for save slot 4.
     */
    public JButton getButton4()
    {
        return button4;
    }

    /**
     * Retrieves the button for loading save slot 5.
     * @return The button for save slot 5.
     */
    public JButton getButton5()
    {
        return button5;
    }

    /**
     * Retrieves the delete button for save slot 1.
     * @return The delete button for save slot 1.
     */
    public JButton getButtond1()
    {
        return buttond1;
    }

    /**
     * Retrieves the delete button for save slot 2.
     * @return The delete button for save slot 2.
     */
    public JButton getButtond2()
    {
        return buttond2;
    }

    /**
     * Retrieves the delete button for save slot 3.
     * @return The delete button for save slot 3.
     */
    public JButton getButtond3()
    {
        return buttond3;
    }

    /**
     * Retrieves the delete button for save slot 4.
     * @return The delete button for save slot 4.
     */
    public JButton getButtond4()
    {
        return buttond4;
    }

    /**
     * Retrieves the delete button for save slot 5.
     * @return The delete button for save slot 5.
     */
    public JButton getButtond5()
    {
        return buttond5;
    }

    /**
     * Retrieves a button based on its name.
     * @param name The name of the button.
     * @return The button with the specified name, or null if no such button exists.
     */
    public JButton getAButton(String name)
    {
        if (name.equals("button1")) return button1;
        if (name.equals("button2")) return button2;
        if (name.equals("button3")) return button3;
        if (name.equals("button4")) return button4;
        if (name.equals("button5")) return button5;
        return null;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if (backgroundImage != null) g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}