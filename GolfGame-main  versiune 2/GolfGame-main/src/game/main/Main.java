package main;

import javax.swing.SwingUtilities;

public class Main {
    /**
     * The main method that starts the application.
     * It uses SwingUtilities.invokeLater to ensure that the GUI is created on the Event Dispatch Thread.
     *
     * @param args 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameWindow();
        });
    }
}