package view;

import java.awt.Dimension;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * BasePanel serves as the foundation for custom panels in the application.
 * It provides a back button and a default layout using GridBagLayout.
 */
public abstract class BasePanel extends JPanel 
{

    /** 
     * Default width of the panel 
     */
    private static final int DEFAULT_WIDTH = 800;

    /** 
     * Default height of the panel 
     */
    private static final int DEFAULT_HEIGHT = 600;

    /** 
     * Button used for navigating back 
     */
    private JButton backButton;
    
    /**
     * Path to the background image for the panel.
     */
    private final String imagePath;

    /**
     * Constructs a BasePanel with default settings.
     * Initializes the layout with a GridBagLayout, adds a back button,
     * and configures preferred size.
     */
    public BasePanel() 
    {
    	this.imagePath = "images/panel images/";
    	
        setLayout(new GridBagLayout()); // Use GridBagLayout as the layout manager
        GridBagConstraints gbc = new GridBagConstraints();

        // Configure constraints for the "Back" button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align to the top-left corner
        gbc.insets = new Insets(10, 10, 0, 0); // Add a 10 pixel margin from the top-left border

        this.backButton = new JButton("BACK");
        add(backButton, gbc);

        // Configure constraints for an empty component that occupies the remaining space
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH; // Occupy all available space
        add(new JLabel(), gbc);

        backButton.setVisible(false); // Initially hide the back button
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT)); // Set preferred size
    }

    /**
     * Loads an image from the specified resource path and returns it as a {@code BufferedImage}.
     * @param resourcePath The path to the image relative to the resource folder.
     * @return A {@code BufferedImage} containing the loaded image, or null if the image cannot be loaded.
     */
    public static BufferedImage loadImageResource(String resourcePath) 
    {
        ClassLoader classLoader = MenuPanel.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath)) 
        {
            if (inputStream == null) 
            {
                System.err.println("Resource not found: " + resourcePath);
                return null;
            }
         // Use ImageIO to read the image from InputStream
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Retrieves the back button associated with this panel.
     *
     * @return The back button component.
     */
    public JButton getBackButton() 
    {
        return backButton;
    }
    
    /**
	  * Gets the file path.
	  *
	  * @return the image path as a String
	  */
	 public  String getImagePath()
	 {
	     return imagePath;
	 }
}