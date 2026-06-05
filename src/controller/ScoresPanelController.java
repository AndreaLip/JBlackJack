package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ScoresPanel;

/**
 * Controller class for managing actions and interactions with the ScoresPanel.
 */
public class ScoresPanelController extends BaseController
{
    /**
     * Singleton instance of ScoresPanelController.
     */
    private static ScoresPanelController instance;
    
    /**
     * Instance of ScoresPanel for accessing and managing UI components.
     */
    private ScoresPanel scoresPanel = ScoresPanel.getInstance();
    
    /**
     * Private constructor to initialize ScoresPanelController.
     * 
     * Assigns ActionListener to the back button on the Scores panel to navigate
     * back to the menu panel.
     */
    private ScoresPanelController() 
    {
        // Assign ActionListener to backButton
        scoresPanel.getBackButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                panelManager.showPanel("menuPanel");
            }
        });
    }
    
    /**
     * Returns the singleton instance of ScoresPanelController.
     *
     * @return The instance of ScoresPanelController.
     */
    public static ScoresPanelController getInstance()
    {
        if (instance == null) instance = new ScoresPanelController();
        return instance;
    }
}