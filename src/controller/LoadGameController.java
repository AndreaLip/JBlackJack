package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import model.ModelManager;
import model.Player;
import view.GamePanel;
import view.LoadGamePanel;

/**
 * Controller responsible for managing the loading and deletion of saved game data.
 * This controller handles interactions with the LoadGamePanel and coordinates with 
 * other controllers and the model to load saved games and handle save deletion.
 */
public class LoadGameController extends BaseController
{
    /** 
     * The singleton instance of LoadGameController. 
     */
    private static LoadGameController instance;	
    
    /** 
     * The singleton instance of ModelManager for managing game data. 
     */
    private ModelManager modelManager = ModelManager.getInstance();
    
    /** 
     * The singleton instance of LoadGamePanel for UI interactions related to loading games. 
     */
    private LoadGamePanel loadGamePanel = LoadGamePanel.getInstance();
    
    /** 
     * The singleton instance of MenuPanelController for controlling menu interactions. 
     */
    private MenuPanelController menuPanelController = MenuPanelController.getInstance();
    
    /** 
     * The singleton instance of GamePanel for displaying the loaded game. 
     */
    private GamePanel gamePanel = GamePanel.getInstance();
    
    /**
     * Private constructor to initialize listeners for UI components and set up actions
     * for loading and deleting game saves.
     */
    private LoadGameController()
    {
        checkSave();

        // ActionListener assignment for the backButton
        loadGamePanel.getBackButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // switch to the menuPanel
                panelManager.showPanel("menuPanel");
            }
        });
          
        // ActionListener assignment for button1 
        loadGamePanel.getButton1().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Load game data for save slot 1 if available and switch to the gamePanel
                if (NewGamePanelController.numberOfSaves() > 0)
                {
                    loadGame(recoverData(0));
                    panelManager.showPanel("gamePanel");
                }
            }
        });
        
        // ActionListener assignment for button2 
        loadGamePanel.getButton2().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Load game data for save slot 2 if available and switch to the gamePanel
                if (NewGamePanelController.numberOfSaves() > 1)
                {                        
                    loadGame(recoverData(1));
                    panelManager.showPanel("gamePanel");
                }
            }
        });
                
        // ActionListener assignment for button3 
        loadGamePanel.getButton3().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Load game data for save slot 3 if available and switch to the gamePanel
                if (NewGamePanelController.numberOfSaves() > 2)
                {
                    loadGame(recoverData(2));
                    panelManager.showPanel("gamePanel");
                }
            }
        });
                
        // ActionListener assignment for button4 
        loadGamePanel.getButton4().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Load game data for save slot 4 if available and switch to the gamePanel
                if (NewGamePanelController.numberOfSaves() > 3)
                {
                    loadGame(recoverData(3));
                    panelManager.showPanel("gamePanel");
                }
           }
        });
                
        // ActionListener assignment for button5 
        loadGamePanel.getButton5().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Load game data for save slot 5 if available and switch to the gamePanel
                if (NewGamePanelController.numberOfSaves() > 4)
                {
                    loadGame(recoverData(4));
                    panelManager.showPanel("gamePanel");
                }
            }
        });
        
        // ActionListener assignment for buttond1 
        loadGamePanel.getButtond1().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Prompt user to confirm deletion of save slot 1 if not already empty
                if (!loadGamePanel.getButton1().getText().equals("Save 1"))
                {
                    int answer = JOptionPane.showConfirmDialog(null, "Do you want to delete save 1? ", "DELETE SAVE", JOptionPane.CANCEL_OPTION);
                    if (answer == JOptionPane.YES_OPTION) pressButtonX(1);
                }
            }
        });
        
        // ActionListener assignment for buttond2 
        loadGamePanel.getButtond2().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Prompt user to confirm deletion of save slot 2 if not already empty
                if (!loadGamePanel.getButton2().getText().equals("Save 2"))
                {
                    int answer = JOptionPane.showConfirmDialog(null, "Do you want to delete save 2? ", "DELETE SAVE", JOptionPane.CANCEL_OPTION);
                    if (answer == JOptionPane.YES_OPTION) pressButtonX(2);                
                }
            }
        });
        
        // ActionListener assignment for buttond3 
        loadGamePanel.getButtond3().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Prompt user to confirm deletion of save slot 3 if not already empty
                if (!loadGamePanel.getButton3().getText().equals("Save 3"))
                {
                    int answer = JOptionPane.showConfirmDialog(null, "Do you want to delete save 3? ", "DELETE SAVE", JOptionPane.CANCEL_OPTION);
                    if (answer == JOptionPane.YES_OPTION) pressButtonX(3);                
                }
            }
        });
        
        // ActionListener assignment for buttond4 
        loadGamePanel.getButtond4().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Prompt user to confirm deletion of save slot 4 if not already empty
                if (!loadGamePanel.getButton4().getText().equals("Save 4"))
                {
                    int answer = JOptionPane.showConfirmDialog(null, "Do you want to delete save 4? ", "DELETE SAVE", JOptionPane.CANCEL_OPTION);
                    if (answer == JOptionPane.YES_OPTION) pressButtonX(4);                
                }
            }
        });
        
        // ActionListener assignment for buttond5 
        loadGamePanel.getButtond5().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Prompt user to confirm deletion of save slot 5 if not already empty
                if (!loadGamePanel.getButton5().getText().equals("Save 5"))
                {
                    int answer = JOptionPane.showConfirmDialog(null, "Do you want to delete save 5? ", "DELETE SAVE", JOptionPane.CANCEL_OPTION);
                    if (answer == JOptionPane.YES_OPTION) pressButtonX(5);                
                }
            }
        });
    }
    
     /**
     * Retrieves the single instance of LoadGameController.
     * If the instance is null, it creates one.
     * 
     * @return the instance of LoadGameController
     */
    public static LoadGameController getInstance()
    {
        if (instance == null) instance = new LoadGameController();
        return instance;
    }
    
    /**
     * Handles the process of deleting a save slot, updates the saves file, 
     * and refreshes the menu with updated save information.
     * 
     * @param nSave the number of the save slot to delete
     */
    private void pressButtonX(int nSave)
    {
        deleteSave(nSave);
        System.out.println("Deleted save " + nSave);
        loadGamePanel.cleanPanel();
        menuPanelController.loadSaves();
    }
    
    /**
     * Deletes the data for the specified save slot from the saves file.
     * 
     * @param nSave the number of the save slot to delete
     */
    private void deleteSave(int nSave)
    {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(getFilePath()));
            
            // Check if the line number to remove is valid
            if (nSave > 0 && nSave <= lines.size()) 
            {
            	// Filter the lines to keep            	
                List<String> updatedLines = lines.stream()
                								 .filter(line -> lines.indexOf(line) != nSave - 1)
                								 .collect(Collectors.toList());
                
                // Rewrite the file with updated lines
                Files.write(Paths.get(getFilePath()), updatedLines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
    
    /**
     * This method checks if a save file exists at the specified file path.
     * If the file does not exist, it creates a new file.
     */
    private void checkSave() 
    {
        File file = new File(getFilePath());
        try 
        {
            // Checks if the file exists and if it does not exist, creates it
            if (file.createNewFile()) System.out.println("File created: " + file.getName());
            else System.out.println("The file already exists.");
        } catch (IOException e) 
        {
            System.out.println("An error occurred during the creation of the file.");
            e.printStackTrace();
        }
    }
    
    /**
     * Loads a saved game using the data from the specified line in the saves file,
     * constructs player objects, updates the game panel, and switches to the gamePanel view.
     * 
     * @param matchData line the line number in the saves file containing the saved game data
     */
    private void loadGame(String[] matchData)
    {
        // Initialize collection for opponent players
        List<Player> opponentsCollection = new ArrayList<>();
        
        // Set index for reading player data
        int index = 9;
        
        // Number of opponents
        int numberOpponents = Integer.parseInt(matchData[8]);
        
        // Instantiate the human player
        Player hp = new Player.Builder(matchData[1], matchData[2])
        					  .money(Integer.parseInt(matchData[3]))
        					  .played(Integer.parseInt(matchData[4]))
        					  .won(Integer.parseInt(matchData[5]))
        					  .lost(Integer.parseInt(matchData[6]))
        					  .level(Integer.parseInt(matchData[7]))
        					  .build();
        
        // Instantiate opponent players
        for (int i = 0; i < numberOpponents; i++)
        {
            Player p = new Player.Builder(matchData[index], matchData[index+1])
                                 .money(Integer.parseInt(matchData[index+2]))
                                 .build();
            opponentsCollection.add(p);
            index += 3;
        }
        
        // Update gamePanel with players and other game data
        NewGamePanelController.insertPlayers(gamePanel, hp, opponentsCollection, modelManager);
        System.out.println(modelManager.getHp().hptoString());
        gamePanel.setSaveNumber(Integer.parseInt(matchData[0]));
        gamePanel.removeCards();
    } 
    
    /**
     * Retrieves the saved game data from the specified line in the saves file.
     * 
     * @param line the line number in the saves file containing the saved game data
     * @return an array containing the data for the saved game
     */
    private String[] recoverData(int line) 
    {
        String[] matchData = null;
        int currentLineNumber = 0;
        String lineContent;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) 
        {
            while ((lineContent = reader.readLine()) != null && currentLineNumber < 5 ) 
            {                
                // If the current line is the one we are looking for
                if (currentLineNumber == line) 
                {
                    matchData = lineContent.split(" ");
                    break; // Exit loop once desired line is found
                }
                currentLineNumber++;
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return matchData;
    }  
}
