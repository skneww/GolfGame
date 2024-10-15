package gamestate;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import main.GameWindow;

public class MenuState extends State {

    private GameWindow gameWindow;
    private JPanel menuPanel;
    private JButton playButton;
    private JButton settingsButton;
    private JButton exitButton;

    //constructor
    public MenuState(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        initComponents(); //render interface
    }
    
    private void initComponents() {
        menuPanel = new JPanel();
        menuPanel.setLayout(null);

        playButton = new JButton("Play");
        playButton.setBounds(GameWindow.WIDTH / 2 - 75, GameWindow.HEIGHT / 2 - 50, 150, 40);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameWindow.changeState(new PlayState(gameWindow));
            }
        });

        settingsButton = new JButton("Settings");
        playButton.setBounds(GameWindow.WIDTH / 2 - 75, GameWindow.HEIGHT / 2, 150, 40);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameWindow.changeState(new SettingsState(gameWindow));
            }
        });

        exitButton = new JButton("Exit");
        playButton.setBounds(GameWindow.WIDTH / 2 - 75, GameWindow.HEIGHT / 2 + 50, 150, 40);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameWindow.changeState(new PlayState(gameWindow));
            }
        });

        menuPanel.add(playButton);
        menuPanel.add(settingsButton);
        menuPanel.add(exitButton);
        menuPanel.setOpaque(false);
        menuPanel.setBounds(0,0, GameWindow.WIDTH, GameWindow.HEIGHT);
        
        gameWindow.setLayout(null);
        gameWindow.add(menuPanel);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(gameWindow.getBackground());
        g2d.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
    }

    
}
