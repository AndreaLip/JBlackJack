package view;

import javax.swing.JFrame;
import java.util.Observable;
import java.util.Observer;

/**
 * GameView class represents the main window of the JBlackJack game.
 * It observes the model and updates the UI components based on changes in the model.
 */
public class GameView extends JFrame implements Observer 
{
    /**
     * The title of the game window.
     */
    public static final String TITLE = "JBLACK JACK";

    /**
     * The panel manager that manages different panels in the game.
     */
    private PanelManager panelManager = PanelManager.getInstance();

    /**
     * The game panel where the game is played.
     */
    private GamePanel gamePanel = GamePanel.getInstance();

    /**
     * Constructs a GameView instance.
     * Initializes the frame, sets up the panel manager, and shows the initial panel.
     *
     */
    public GameView() 
    {
        super(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600); // Set the window size
        setLocationRelativeTo(null); // Center the window on the screen

        // Add the panel manager to the view
        add(panelManager);

        // Set the initial panel to be shown
        this.panelManager.showPanel("menuPanel");
    }

    /**
     * Gets the panel manager.
     * 
     * @return The panel manager.
     */
    public PanelManager getPanelManager()
    {
        return panelManager;
    }

    /**
     * Updates the view based on changes in the observed model.
     *
     * @param o The observable object.
     * @param arg The argument passed by the observable object.
     */
    @Override
    public void update(Observable o, Object arg) 
    {
        if (arg != null)
        {
            switch ((String) arg) 
            {
                case "bets":
                    gamePanel.updateFirstBets();
                    break;
                case "cards1":
                    gamePanel.updateFirstCards();
                    break;
                case "cards2":
                    gamePanel.updateCards();
                    break;
                case "hp":
                    gamePanel.updateHpCard();
                    break;
                default:
                    gamePanel.updateGame((String) arg);
                    break;
            }
        }    
        panelManager.repaint();
    }
}