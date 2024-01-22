package com.mycompany.tictactoegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener {

    private JFrame frame;
    
    private JPanel headerPanel;
    private JLabel headerLabel;
    
    private JPanel buttonPanel;
    private JButton[] buttons = new JButton[9];
    
    private JLabel playerOneScoreLabel;
    private int playerOneScore;
    
    private JLabel playerTwoScoreLabel;
    private int playerTwoScore;
    
    private JButton newGameButton;
    private JButton exitGameButton;
    
    private Random random = new Random();
    private boolean isPlayerOneTurn;
    
    public TicTacToe() {
        
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(500, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(51, 51, 51));
        
        headerLabel = new JLabel("Tic-Tac-Toe");
        headerLabel.setFont(new Font("Verdana", Font.PLAIN, 40));
        headerLabel.setForeground(new Color(245, 245, 245));
        
        headerPanel = new JPanel();
        headerPanel.setBounds(45, 20, 400, 80);
        headerPanel.setBackground(new Color(30,30,30));
        headerPanel.add(headerLabel);
        
        buttonPanel = new JPanel();
        buttonPanel.setBounds(45, 120, 400, 380);
        buttonPanel.setLayout(new GridLayout(3, 3, 5, 5));
        buttonPanel.setBackground(new Color(51, 51, 51));
        
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 100));
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setFocusable(false);
            buttons[i].setEnabled(false);
            buttons[i].addActionListener(this);
            
            buttonPanel.add(buttons[i]);
        }
        
        playerOneScoreLabel = new JLabel("Player One Score(X): " + playerOneScore);
        playerOneScoreLabel.setBounds(45, 510, 170, 30);
        playerOneScoreLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        playerOneScoreLabel.setForeground(Color.WHITE);
        playerOneScoreLabel.setBackground(new Color(30, 30, 30));
        playerOneScoreLabel.setOpaque(true);
        
        playerTwoScoreLabel = new JLabel("Player Two Score(O): " + playerTwoScore);
        playerTwoScoreLabel.setBounds(275, 510, 170, 30);
        playerTwoScoreLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        playerTwoScoreLabel.setForeground(Color.WHITE);
        playerTwoScoreLabel.setBackground(new Color(30, 30, 30));
        playerTwoScoreLabel.setOpaque(true);
        
        newGameButton = new JButton("START GAME");
        newGameButton.setBounds(45, 560, 150, 30);
        newGameButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(this);
        
        exitGameButton = new JButton("EXIT GAME");
        exitGameButton.setBounds(295, 560, 150, 30);
        exitGameButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        exitGameButton.setFocusable(false);
        exitGameButton.addActionListener(this);
        
        frame.add(headerPanel);
        frame.add(buttonPanel);
        frame.add(playerOneScoreLabel);
        frame.add(playerTwoScoreLabel);
        frame.add(newGameButton);
        frame.add(exitGameButton);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == newGameButton) {
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setText("");
                buttons[i].setBackground(Color.LIGHT_GRAY);
                buttons[i].setEnabled(true);
            }
            
            firstTurn();
            newGameButton.setText("Play Again");
            
        }
        
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                if (isPlayerOneTurn) {
                    if (buttons[i].getText().isEmpty()) {
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("X");
                        
                        isPlayerOneTurn = false;
                        headerLabel.setText("Player Two Turns");
                        checkWinner();
                    }
                }
                else {
                    if (buttons[i].getText().isEmpty()) {
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("O");
                        
                        isPlayerOneTurn = true;
                        headerLabel.setText("Player One Turns");
                        checkWinner();
                    }
                }
            }
        }
        
        if (e.getSource() == exitGameButton) {
            System.exit(0);
        }
    }
    
    private void firstTurn() {
        
        if (random.nextInt(2) == 0) {
            isPlayerOneTurn = true;
            headerLabel.setText("Player One Turns");
        }
        else {
            isPlayerOneTurn = false;
            headerLabel.setText("Player Two Turns");
        }
    }
    
    private void checkWinner() {
        
        //Check if Tie Game
        boolean isTieGame = true;
        for (JButton button: buttons) {
            if (button.getText().isEmpty()) {
                isTieGame = false;
                break;
            }
        }
        
        if (isTieGame) {
            headerLabel.setText("Tie Game!");
        }
        
        //Check if Player One Wins
        if (buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X")) {
            playerOneScore++;
            playerOneWins(0, 1, 2);
        }
        
        if (buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X")) {
            playerOneScore++;
            playerOneWins(3, 4, 5);
        }
        
        if (buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X")) {
            playerOneScore++;
            playerOneWins(6, 7, 8);
        }
        
        if (buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X")) {
            playerOneScore++;
            playerOneWins(0, 3, 6);
        }
        
        if (buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X")) {
            playerOneScore++;
            playerOneWins(1, 4, 7);
        }
        
        if (buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("X")) {
            playerOneScore++;
            playerOneWins(2, 5, 8);
        }
        
        if (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) {
            playerOneScore++;
            playerOneWins(0, 4, 8);
        }
        
        if (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X")) {
            playerOneScore++;
            playerOneWins(2, 4, 6);
        }
        
        //Check if Player Two Wins
        if (buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O")) {
            playerTwoScore++;
            playerTwoWins(0, 1, 2);
        }
        
        if (buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O")) {
            playerTwoScore++;
            playerTwoWins(3, 4, 5);
        }
        
        if (buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O")) {
            playerTwoScore++;
            playerTwoWins(6, 7, 8);
        }
        
        if (buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O")) {
            playerTwoScore++;
            playerTwoWins(0, 3, 6);
        }
        
        if (buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O")) {
            playerTwoScore++;
            playerTwoWins(1, 4, 7);
        }
        
        if (buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("O")) {
            playerTwoScore++;
            playerTwoWins(2, 5, 8);
        }
        
        if (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) {
            playerTwoScore++;
            playerTwoWins(0, 4, 8);
        }
        
        if (buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O")) {
            playerTwoScore++;
            playerTwoWins(2, 4, 6);
        }
        
    }
    
    private void playerOneWins(int x, int y, int z) {
        buttons[x].setBackground(Color.RED);
        buttons[y].setBackground(Color.RED);
        buttons[z].setBackground(Color.RED);
        
        headerLabel.setText("Player One Wins!");
        playerOneScoreLabel.setText("Player One Score(X): " + playerOneScore);
    }
    
    private void playerTwoWins(int x, int y, int z) {
        buttons[x].setBackground(Color.BLUE);
        buttons[y].setBackground(Color.BLUE);
        buttons[z].setBackground(Color.BLUE);
        
        headerLabel.setText("Player Two Wins!");
        playerTwoScoreLabel.setText("Player Two Score(O): " + playerTwoScore);
    }
 }
