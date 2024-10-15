package gamestate;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.GameWindow;

public class SettingsState extends State {

    private GameWindow gameWindow;
    private JPanel settingsPanel;
    private JButton backButton;

    public SettingsState(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        initComponents();
    }

    private void initComponents() {
        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(GameWindow.WIDTH / 2 - 75, GameWindow.HEIGHT / 2 + 50, 150, 40);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameWindow.changeState(new MenuState(gameWindow));
            }
        });

        settingsPanel.add(backButton);
        settingsPanel.setOpaque(false);
        settingsPanel.setBounds(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        gameWindow.setLayout(null);
        gameWindow.add(settingsPanel);
    }

    @Override
    public void update() {
        // No game logic to update in settings
    }

    @Override
    public void render(Graphics2D g2d) {
        // Background and components are handled by Swing components
    }
}
