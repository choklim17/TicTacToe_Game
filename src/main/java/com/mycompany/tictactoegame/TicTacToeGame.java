package com.mycompany.tictactoegame;

import javax.swing.SwingUtilities;

public class TicTacToeGame {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}
