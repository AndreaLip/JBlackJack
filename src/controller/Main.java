package controller;

import model.ModelManager;
import view.GameView;
import java.util.Observer;
import javax.swing.Timer;

/**
 * The Main class initializes and starts the application for the blackjack game.
 * It sets up the model, view, and initiates the game loop using a timer.
 */
public class Main
{
	/**
     * The main method that starts the application.
     * 
     * @param args Command line arguments (not used in this application)
     */
	public static void main(String[] args) 
	{		
		// create the model
		ModelManager mm = ModelManager.getInstance();		
		// create the view
		GameView gv = new GameView();		
		// create the controller
		ControllerManager cm = ControllerManager.getInstance();		
		// make everything visible
		gv.setVisible(true);		
		// trigger the Observer-Observable mechanism
		mm.addObserver(gv);		
		// start the background music
		AudioManager.play("menu song.wav", true);
		// Configure the timer to update the game state
        Timer timer = new Timer(1000 / 60, e -> {
            mm.updateGameState();
            gv.repaint();
        });
        timer.start();
	}
}
