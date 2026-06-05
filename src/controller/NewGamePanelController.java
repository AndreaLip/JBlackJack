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
import java.util.Random;
import javax.swing.JButton;
import model.ModelManager;
import model.Player;
import view.GamePanel;
import view.NewGamePanel;

/**
 * Controller class for managing actions and interactions with the NewGamePanel.
 */
public class NewGamePanelController extends BaseController
{
    /**
     * Singleton instance of NewGamePanelController.
     */
    private static NewGamePanelController instance;

    /**
     * Instance of ModelManager for accessing application data.
     */
    private ModelManager modelManager = ModelManager.getInstance();

    /**
     * Instance of NewGamePanel for accessing and managing UI components.
     */
    private NewGamePanel newGamePanel = NewGamePanel.getInstance();

    /**
     * Instance of GamePanel for managing the game display.
     */
    private GamePanel gamePanel = GamePanel.getInstance();

    /**
     * Name of the player.
     */
    private String playerName;

    /**
     * Name of the selected player avatar.
     */
    private String playerImageName;

    /**
     * Amount of money for the player.
     */
    private Integer amountBox;

    /**
     * Number of opponents chosen.
     */
    private Integer opponentsBox;

    /**
     * Private constructor to initialize NewGamePanelController.
     * 
     * Creates action listeners for avatar buttons and sets up action listeners for
     * the back button and start game button on the NewGamepanel.
     */
    private NewGamePanelController()
    {
        // Create action listeners for avatar buttons
        addAvatarButtonListeners();
        
        // Assign ActionListener to backButton
        newGamePanel.getBackButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Show the menu panel
                panelManager.showPanel("menuPanel");
                // Reset fields of the newGamePanel
                newGamePanel.resetFields();
            }
        });

        // Assign ActionListener to startGameButton
        newGamePanel.getStartGameButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Retrieve user input from New Game panel fields
                playerName = newGamePanel.getNameField().getText();
                amountBox = (Integer) newGamePanel.getAmountBox().getSelectedItem();
                opponentsBox = (Integer) newGamePanel.getOpponentsBox().getSelectedItem();
                if (newGamePanel.getPlayerImageButton() != null)
                    playerImageName = (String) newGamePanel.getPlayerImageButton().getClientProperty("avatarName");

                // Check if all required fields are filled
                if (playerName != null && !playerName.isEmpty() && amountBox != null && opponentsBox != null && playerImageName != null)
                {
                    // Check if the number of saves is less than 5 before creating a new game
                    if (numberOfSaves() >= 5)
                    {
                        newGamePanel.getErrorLabel().setText("Saves full, delete a save first");
                        newGamePanel.getErrorLabel().setVisible(true);
                    }
                    else
                    {
                        // Create the game
                        createTheGame(opponentsBox);
                        // Switch panel and reset newGamePanel fields
                        panelManager.showPanel("gamePanel");
                        newGamePanel.resetFields();
                    }
                }
                else
                {
                    // Display error message if any required fields are empty or invalid
                    if (playerName == null || playerName.isEmpty() || playerImageName == null || amountBox == null || opponentsBox == null)
                    {
                        newGamePanel.getErrorLabel().setText("Fill in all the fields correctly and press the start button!");
                        newGamePanel.getErrorLabel().setVisible(true);
                    }
                }
            }
        });
    }

    /**
     * Returns the singleton instance of NewGamePanelController.
     *
     * @return The instance of NewGamePanelController.
     */
    public static NewGamePanelController getInstance()
    {
        if (instance == null) instance = new NewGamePanelController();
        return instance;
    }

    /**
     * Method to create a new game with specified number of opponents.
     *
     * @param opponents Number of opponents in the game.
     */
    private void createTheGame(Integer opponents)
    {
        // Create instance of human player
        Player hp = new Player.Builder(playerName, playerImageName).money(amountBox).build();
        // Retrieve the set of keys (avatar names) and remove the selected one by human player
        List<String> remainingAvatars = new ArrayList<>(newGamePanel.getAvatarButtons().keySet());
        remainingAvatars.remove(playerImageName);
        // Create a collection for opponents
        List<Player> opponentsCollection = new ArrayList<>();
        // Create opponents
        for (int i = 0; i < opponents; i++)
        {
            // Player number needed for name
            int numberPlayer = i + 1;
            createOpponent(numberPlayer, opponentsCollection, remainingAvatars);
        }
        // Update gamePanel with players
        insertPlayers(gamePanel, hp, opponentsCollection, modelManager);
        // Save game state and update save number
        gamePanel.setSaveNumber(createSaveGame(gamePanel));
    }

    /**
     * Method to insert players into the GamePanel and ModelManager.
     *
     * @param gp GamePanel instance to update.
     * @param hp Human player instance.
     * @param oc Opponents collection.
     * @param mm ModelManager instance to update.
     */
    public static void insertPlayers(GamePanel gp, Player hp, List<Player> oc, ModelManager mm)
    {
        // Add players to model manager
        mm.setHp(hp);
        mm.setOpponents(oc);
        // Set human player in gamePanel
        gp.setHumanPlayer(hp);
        // Set opponents in gamePanel
        gp.setOpponents(oc);
        // Set dealer
        Player dealer = new Player.Builder("dealer", "dealer").deck().build();
        mm.setDealer(dealer);
        gp.setDealer(dealer);
        // Set only necessary players visible
        gp.setPlayersInvisible(oc.size());
        // Update gp fields
        gp.setAvatarAndLabels();
        // Update initial bets
        gp.setAction2(gp.setBets());
        // Update counters on the bottom left
        gp.setCounters();
        // Play game music
        AudioManager.play("game song.wav", true);
    }

    /**
     * Method to create an opponent player.
     *
     * @param numberPlayer        Player number.
     * @param opponentsCollection Collection of opponents.
     * @param remainingAvatars    Remaining avatar names.
     */
    private void createOpponent(int numberPlayer, List<Player> opponentsCollection, List<String> remainingAvatars)
    {
        // Generate random number for avatar image
        Random random = new Random();
        int randomNumber = random.nextInt(remainingAvatars.size());
        // Retrieve a random avatar image
        String imageName = remainingAvatars.get(randomNumber);
        Player p = new Player.Builder("player" + numberPlayer, imageName).money(amountBox).build();
        // Remove image from remaining list
        remainingAvatars.remove(imageName);
        // Add player to collection
        opponentsCollection.add(p);
    }

    /**
     * Method to create a save game.
     *
     * @param gp GamePanel instance.
     * @return The save number created.
     */
    private int createSaveGame(GamePanel gp)
    {
        int saveNum = numberOfSaves() + 1;
        // Add save number
        String line = saveNum + " ";
        // Append player data to line
        line += gp.getHumanPlayer().hptoString() + " " + gp.getOpponents().size();
        // Add players' data to line
        for (Player p : gp.getOpponents()) line += " " + p.toString();
        // Write data to text file for save
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), true)))
        {
            writer.write(line);
            writer.newLine(); // Add new line after string
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveNum;
    }

    /**
     * Method to count the number of saves.
     *
     * @return The number of save games present.
     */
    public static int numberOfSaves()
    {
        // Number of existing save games
        int numberOfLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath())))
        {
            while (reader.readLine() != null) numberOfLines++;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return numberOfLines;
    }

    /**
     * Method to add ActionListener to all avatar buttons.
     */
    private void addAvatarButtonListeners()
    {
        for (JButton avatarButton : newGamePanel.getAvatarButtons().values())
        {
            avatarButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    // Perform actions when an avatar button is pressed
                    newGamePanel.setPlayerImageButton((JButton) e.getSource());
                }
            });
        }
    }
}