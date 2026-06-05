package view;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * Singleton manager for handling panels with CardLayout.
 */
public class PanelManager extends JPanel 
{
    /**
     * Singleton instance of PanelManager.
     */
    private static PanelManager instance;
    
    /**
     * CardLayout used for managing panels.
     */
    private CardLayout cardLayout;

    /**
     * Private constructor initializing PanelManager instance.
     * Sets up CardLayout and adds initial panels.
     */
    private PanelManager() 
    {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        // Creating panels
        MenuPanel menuPanel = MenuPanel.getInstance();
        NewGamePanel newGamePanel = NewGamePanel.getInstance();
        GamePanel gamePanel = GamePanel.getInstance();
        LoadGamePanel loadGamePanel = LoadGamePanel.getInstance();
        SettingsPanel settingsPanel = SettingsPanel.getInstance();
        ScoresPanel scoresPanel = ScoresPanel.getInstance();
        	
        // Adding panels to PanelManager using addPanel method
        add(menuPanel, "menuPanel");
        add(newGamePanel, "newGamePanel");
        add(gamePanel, "gamePanel");
        add(loadGamePanel, "loadGamePanel");
        add(settingsPanel, "settingsPanel");
        add(scoresPanel, "scoresPanel");
    }
    
    /**
     * Retrieves the singleton instance of PanelManager.
     * @return The PanelManager instance.
     */
    public static PanelManager getInstance()
    {
        if (instance == null) instance = new PanelManager();
        return instance;
    }

    /**
     * Shows a specific panel identified by the given string in CardLayout.
     * @param panelName The name of the panel to show.
     */
    public void showPanel(String panelName) 
    {
        cardLayout.show(this, panelName);
    }  
}