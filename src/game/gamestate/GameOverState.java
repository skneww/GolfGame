package gamestate;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics2D;
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
        initComponents();
    }

    private void initComponents() {
        gameOverPanel = new JPanel();
        gameOverPanel.setLayout(null);

        menuButton = new JButton("Main Menu");
        menuButton.setBounds(GameWindow.WIDTH / 2 - 75, GameWindow.HEIGHT / 2, 150, 40);
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameWindow.changeState(new MenuState(gameWindow));
            }
        });

        exitButton = new JButton("Exit");
        exitButton.setBounds(GameWindow.WIDTH / 2 - 75, GameWindow.HEIGHT / 2 + 50, 150, 40);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gameOverPanel.add(menuButton);
        gameOverPanel.add(exitButton);
        gameOverPanel.setOpaque(false);
        gameOverPanel.setBounds(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        gameWindow.setLayout(null);
        gameWindow.add(gameOverPanel);
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
