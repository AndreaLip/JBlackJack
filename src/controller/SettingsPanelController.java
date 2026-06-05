package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.SettingsPanel;

/**
 * Controller class for managing actions and interactions with the SettingsPanel.
 */
public class SettingsPanelController extends BaseController
{
    /**
     * Singleton instance of SettingsPanelController.
     */
    private static SettingsPanelController instance;
    
    /**
     * Instance of SettingsPanel for accessing and managing UI components.
     */
    private SettingsPanel settingsPanel = SettingsPanel.getInstance();

    /**
     * Private constructor to initialize SettingsPanelController.
     * 
     * Assigns ActionListener to the back button on the Settings panel to navigate
     * back to the menu panel.
     */
    private SettingsPanelController() 
    {
        // Assign ActionListener to backButton
        settingsPanel.getBackButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                panelManager.showPanel("menuPanel");
            }
        });
    }

    /**
     * Returns the singleton instance of SettingsPanelController.
     *
     * @return The instance of SettingsPanelController.
     */
    public static SettingsPanelController getInstance()
    {
        if (instance == null) instance = new SettingsPanelController();
        return instance;
    }
}