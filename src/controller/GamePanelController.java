package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ModelManager;
import model.Player;
import view.GamePanel;

/**
 * Controller class for the GamePanel, managing game actions and interactions.
 */
public class GamePanelController extends BaseController
{
    /**
     * Singleton instance of the GamePanelController.
     */
    private static GamePanelController instance;

    /**
     * Instance of GamePanel used for the game interface.
     */
    private GamePanel gamePanel = GamePanel.getInstance();

    /**
     * Instance of ModelManager used to manage the game model.
     */
    private ModelManager modelManager = ModelManager.getInstance();

    /**
     * Private constructor to initialize the GamePanelController and set up action listeners.
     */
    private GamePanelController() 
    {    	
        // Assign ActionListener to the backButton
        gamePanel.getBackButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to quit the game?", "LEAVE THE GAME", JOptionPane.CANCEL_OPTION);
                if (answer == JOptionPane.YES_OPTION)
                {
                    saveGame();
                    gamePanel.removeCards();
                    gamePanel.setPlayersVisible(); // Make all players visible
                    gamePanel.getAction1().setEnabled(true);
                    gamePanel.getAction2().setEnabled(true);
                    gamePanel.getRequest().setText("How much do you want to bet?");
                    gamePanel.getRequest().setVisible(true);
                    panelManager.showPanel("menuPanel");
                    AudioManager.play("menu song.wav", true); // Start the game music
                }
            }
        });

        // Assign ActionListener to action1 button
        gamePanel.getAction1().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Integer bet = (Integer)gamePanel.getAction2().getValue();
                modelManager.getHp().setBet(bet);
                modelManager.insertFirstBet();                            
                gamePanel.getAction1().setEnabled(false);
                gamePanel.getAction2().setEnabled(false);
                modelManager.distributeCards();
            }
        });

        // Assign ActionListener to action3 button
        gamePanel.getAction3().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                modelManager.getHpCard();
                if (gamePanel.getHumanPlayer().getHand() > 21) 
                {
                    gamePanel.getAction3().setEnabled(false);
                    gamePanel.getAction4().setEnabled(false);
                    gamePanel.getAction5().setEnabled(false);
                    modelManager.otherShifts();
                }
            }
        });

        // Assign ActionListener to action4 button
        gamePanel.getAction4().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                gamePanel.getAction3().setEnabled(false);
                gamePanel.getAction4().setEnabled(false);
                gamePanel.getAction5().setEnabled(false);
                modelManager.otherShifts();
            }
        });

        // Assign ActionListener to action5 button
        gamePanel.getAction5().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                modelManager.doubleDown(gamePanel.getHumanPlayer());
                gamePanel.updateBet("human");
                modelManager.getHpCard();
                gamePanel.getAction3().setEnabled(false);
                gamePanel.getAction4().setEnabled(false);
                gamePanel.getAction5().setEnabled(false);
                modelManager.otherShifts();
            }
        });
    }

    /**
     * Returns the single instance of the GamePanelController.
     * If the instance is null, it creates one.
     * 
     * @return the instance of GamePanelController
     */
    public static GamePanelController getInstance()
    {
        if (instance == null) instance = new GamePanelController();
        return instance;
    }

    /**
     * Saves the game state to a file.
     */
    private void saveGame() 
    {
        int saveNum = gamePanel.getSaveNumber() - 1; // Consider saveNum as 1-based index
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) 
        {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit the method if there's an error reading the file
        }
        // Check if the index is valid
        if (saveNum < 0 || saveNum >= 5) // Assume saveNum should be between 0 and 4 (5 lines)
        { 
            System.out.println("Invalid saveNum index."); 
            return;
        }
        // Build the new save line
        String newSaveData = buildSaveData(); 

        // Overwrite the line if it exists, otherwise add it
        if (saveNum < fileContent.size()) fileContent.set(saveNum, newSaveData);
        else 
        {
            // Add empty lines until the desired index is reached, if necessary
            while (fileContent.size() <= saveNum) fileContent.add("");
            fileContent.set(saveNum, newSaveData);
        }

        // Write the modified content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath())))
        {
            for (String line : fileContent) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds the save data string to be written to the file.
     * 
     * @return the save data string
     */
    private String buildSaveData() 
    {
        String line = gamePanel.getSaveNumber() + " ";
        line += gamePanel.getHumanPlayer().hptoString() + " " + gamePanel.getOpponents().size();
        for (Player p : gamePanel.getOpponents()) line += " " + p.toString();
        return line;
    }
}