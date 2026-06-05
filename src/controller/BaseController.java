package controller;

import view.PanelManager;

/**
 * Abstract base class for controllers in the application.
 * Provides a common reference to the {@link PanelManager}.
 */
public abstract class BaseController
{
    /** 
     * Instance of {@link PanelManager} used to manage panels in the application. 
     */
    PanelManager panelManager = PanelManager.getInstance();
    
    /**
	  * File path for saves
	  */
	 private static String filePath;
	 
	 /**
	  * Constructor for BaseController.
	  */
	 public BaseController()
	 {
	     filePath = System.getProperty("user.home") + "/Desktop/saves.txt";		 
	 }

	 /**
	  * Gets the file path.
	  *
	  * @return the file path as a String
	  */
	 public static String getFilePath()
	 {
	     return filePath;
	 }
}
	 