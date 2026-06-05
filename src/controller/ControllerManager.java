package controller;

/**
 * Singleton class responsible for managing the controllers in the application.
 */
public class ControllerManager
{
    /** 
     * The single instance of the ControllerManager. 
     */
    private static ControllerManager instance;
    
    /**
     * Private constructor to initialize the controllers.
     */
    private ControllerManager()
    {        
        // Construct controllers
        MenuPanelController menuPanelController = MenuPanelController.getInstance();
        NewGamePanelController newGamePanelController = NewGamePanelController.getInstance();
        GamePanelController gamePanelController = GamePanelController.getInstance();
        LoadGameController loadGameController = LoadGameController.getInstance();
        SettingsPanelController settingsPanelController = SettingsPanelController.getInstance();
        ScoresPanelController scoresPanelController = ScoresPanelController.getInstance();
    }
    
    /**
     * Returns the single instance of the ControllerManager.
     * If the instance is null, it creates one.
     * 
     * @return the instance of ControllerManager
     */
    public static ControllerManager getInstance()
    {
        if (instance == null) instance = new ControllerManager();
        return instance;
    }
}
