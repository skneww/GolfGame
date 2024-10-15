package gamestate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.GameWindow;

public class GameOverState extends State {

    private GameWindow gameWindow;
    private JPanel gameOverPanel;
    private JButton menuButton;
    private JButton exitButton;
    private int finalScore;

    public GameOverState(GameWindow gameWindow, int finalScore) {
        this.gameWindow = gameWindow;
        this.finalScore = finalScore;
    }

    @Override
    public void init() {
        gameOverPanel = new JPanel();
        gameOverPanel.setOpaque(false);
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));

        JLabel gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel scoreLabel = new JLabel("Final Score: " + finalScore);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        menuButton = new JButton("Main Menu");
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameWindow.changeState(new MenuState(gameWindow));
            }
        });

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gameOverPanel.add(Box.createVerticalGlue());
        gameOverPanel.add(gameOverLabel);
        gameOverPanel.add(Box.createVerticalStrut(10));
        gameOverPanel.add(scoreLabel);
        gameOverPanel.add(Box.createVerticalStrut(20));
        gameOverPanel.add(menuButton);
        gameOverPanel.add(Box.createVerticalStrut(10));
        gameOverPanel.add(exitButton);
        gameOverPanel.add(Box.createVerticalGlue());

        gameWindow.setLayout(new BorderLayout());
        gameWindow.add(gameOverPanel, BorderLayout.CENTER);
    }
    @Override
    public void update() {
        // No updates in game over state
    }

    @Override
    public void render(Graphics2D g2d) {
        // Display final score
        g2d.setColor(gameWindow.getBackground());
        g2d.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        g2d.setColor(java.awt.Color.WHITE);
        g2d.drawString("Game Over!", GameWindow.WIDTH / 2 - 40, GameWindow.HEIGHT / 2 - 60);
        g2d.drawString("Final Score: " + finalScore, GameWindow.WIDTH / 2 - 50, GameWindow.HEIGHT / 2 - 30);
    }
}
