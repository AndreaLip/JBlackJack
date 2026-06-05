package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;

/**
 * Singleton panel representing the ScoresPanel, extending BasePanel.
 */
public class ScoresPanel extends BasePanel
{	
	/**
	 * Singleton instance of ScoresPanel.
	 */
	private static ScoresPanel instance;
	
	/**
	 * Background image displayed on the panel.
	 */
	private Image backgroundImage;

	/**
	 * Private constructor initializing ScoresPanel instance.
	 * Configures layout and initializes background image.
	 */
	private ScoresPanel()
	{
		super(); // Call to superclass constructor (BasePanel)
		getBackButton().setVisible(true);
		
		setLayout(new GridBagLayout()); // Sets GridBagLayout for the panel
		Image imageContent = loadImageResource(getImagePath() + "scores.jpg");        
        backgroundImage = new ImageIcon(imageContent).getImage();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Configures constraints for the "Back" button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(getBackButton(), gbc); // Adds the "Back" button to the panel
	}
	
	/**
	 * Retrieves the singleton instance of ScoresPanel.
	 * @return The ScoresPanel instance.
	 */
	public static ScoresPanel getInstance()
	{
		if (instance == null) instance = new ScoresPanel();
        return instance;
    }
	
	/**
	 * Overrides paintComponent to draw the background image.
	 * @param g The Graphics context used for painting.
	 */
	@Override
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2=(Graphics2D)g;
		if (backgroundImage != null) g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
