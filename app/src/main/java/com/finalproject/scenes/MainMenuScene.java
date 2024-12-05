package com.finalproject.scenes;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.finalproject.app.GameClient;
import com.finalproject.core.GamePanel;

public class MainMenuScene implements Scene{
    private GameClient client;
    // private JFrame window;
    private JPanel menuPanel;
    private JPanel westPanel;
    private JPanel buttonsPanel;
    private JLabel title;
    private JButton startButton;
    private JButton continueButton;
    private JButton exitButton;
    private ActionListener controller;

    public MainMenuScene(GameClient client, ActionListener controller) {
        this.client = client;
        // this.window = window;
        this.controller = controller;
        menuPanel = new GamePanel(new BorderLayout(), true);
        westPanel = new GamePanel(new BorderLayout(), true);
        buttonsPanel = new GamePanel();

        title = new JLabel("Nightshift");
        //TODO: ADD LOGO INSTEAD OF TEXT
        //TODO: DESIGN MAIN MENU

        startButton = new JButton("Start");
        startButton.addActionListener(e -> startGame());

        continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> continueGame());

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> exitGame());

        buttonsPanel.add(startButton);
        buttonsPanel.add(continueButton);
        buttonsPanel.add(exitButton);

        westPanel.add(title, BorderLayout.NORTH);
        westPanel.add(buttonsPanel, BorderLayout.CENTER);

        menuPanel.add(westPanel, BorderLayout.WEST);

        buttonsPanel.setVisible(true);
        westPanel.setVisible(true);
        menuPanel.setVisible(true);
    } 

    private void startGame() {
        client.setScene(new NightScene(1));
        
        Timer timer = new Timer(3000, e -> client.setScene(new GameScene(controller)));
        timer.start();
    }
    
    private void continueGame() {
    
        //TODO: Add function that updates the night, ai level, etc
    }

    private void exitGame() {
        System.exit(0);
    }

    @Override
    public JPanel getPanel() {
        return menuPanel;
    }

}
