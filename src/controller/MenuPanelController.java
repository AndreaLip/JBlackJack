package controller;

import view.LoadGamePanel;
import view.MenuPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Controller class for managing interactions with the menu panel.
 */
public class MenuPanelController extends BaseController
{
	/**
	 * Singleton instance of MenuPanelController
	 */
	 private static MenuPanelController instance;
	 
	 /**
	  * Instance of MenuPanel used in this controller
	  */
	 private MenuPanel menuPanel = MenuPanel.getInstance(); 
	 
	 /**
	  * Instance of LoadGamePanel used in this controller
	  */
	 private LoadGamePanel loadGamePanel = LoadGamePanel.getInstance();   

    /**
     * Private constructor to prevent direct instantiation.
     * Sets up ActionListeners for buttons in MenuPanel.
     */
    private MenuPanelController()
    {               
    	
        // ActionListener for NewGameButton
        menuPanel.getNewGameButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                panelManager.showPanel("newGamePanel");
            }
        });
     
        // ActionListener for LoadGameButton
        menuPanel.getLoadGameButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                loadSaves();  // Load saves when button is clicked
                panelManager.showPanel("loadGamePanel");
            }
        });
        
        // ActionListener for SettingsButton
        menuPanel.getSettingsButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                panelManager.showPanel("settingsPanel");
            }
        });

        // ActionListener for ScoresButton
        menuPanel.getScoresButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                panelManager.showPanel("scoresPanel");
            }
        });

        // ActionListener for Exit Button
        menuPanel.getExitButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);  // Exit the application when Exit Button is clicked
            }
        });
    }       

    /**
     * Returns the single instance of the MenuPanelController.
     * If the instance is null, it creates one.     
     * 
     * @return the singleton instance of MenuPanelController
     */
    public static MenuPanelController getInstance()
    {
        if (instance == null) instance = new MenuPanelController();
        return instance;
    }
    
    /**
     * Retrieves and displays saved game information on the load game panel.
     */
    public void loadSaves()
    {
        int loadNumber = 1;  // Initialize the save slot number
        if (NewGamePanelController.numberOfSaves() > 0)
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) 
            {
                String line;
                // Read each line from the saves file, up to a maximum of 5 lines
                while ((line = reader.readLine()) != null && loadNumber < 6) 
                {
                    String[] words = line.split(" ");
                    // Construct text to display on the load game panel button
                    String textButton = "Save: "+words[0]+" | Nickname: "+words[1]+" | Opponents: "+words[8]
                                        +" | Money: "+words[3]+"$ | Played: "+words[4]+" | Won: "+words[5]
                                        +" | Lost: "+ words[6]+" | Level: "+words[7];
                    loadGamePanel.getAButton("button"+loadNumber).setText(textButton);  // Set button text
                    loadNumber++;  // Increment the save slot number
                }
            } catch (IOException e) {
                e.printStackTrace();  
            }       
        }   
    }
}
