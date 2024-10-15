package gamestate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.IOException;
import main.GameWindow;

public class MenuState extends State {

    private GameWindow gameWindow;
    private JPanel menuPanel;
    private JButton playButton;
    private JButton settingsButton;
    private JButton exitButton;
    private Image backgroundImage;

    //constructor
    public MenuState(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }
    
    @Override
    public void init() {

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resources/backgrounds/GolfBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        playButton = new JButton("Play");
        settingsButton = new JButton("Settings");
        exitButton = new JButton("Exit");

        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameWindow.changeState(new PlayState(gameWindow));
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameWindow.changeState(new SettingsState(gameWindow));
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(playButton);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(settingsButton);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(exitButton);
        menuPanel.add(Box.createVerticalGlue());

        gameWindow.setLayout(new BorderLayout());
        gameWindow.add(menuPanel, BorderLayout.CENTER);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics2D g2d) {
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, GameWindow.WIDTH, GameWindow.HEIGHT, null);
        } else {
            g2d.setColor(gameWindow.getBackground());
            g2d.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
        }
    }

    
}
