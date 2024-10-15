package gamestate;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.GameWindow;
import sound.Sound;

public class SettingsState extends State {

    private GameWindow gameWindow;
    private JPanel settingsPanel;
    private JButton backButton;
    private JSlider volumeSlider;

    public SettingsState(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    public void init() {
        settingsPanel = new JPanel();
        settingsPanel.setOpaque(false);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        JLabel volumeLabel = new JLabel("Volume");
        volumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        volumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        volumeSlider.setMaximumSize(new Dimension(200, 50));
        volumeSlider.addChangeListener(e -> {
            int volume = volumeSlider.getValue();
            Sound.setMasterVolume(volume / 100.0f);
        });

        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(volumeLabel);
        settingsPanel.add(volumeSlider);

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameWindow.changeState(new MenuState(gameWindow));
            }
        });



        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(backButton);
        settingsPanel.add(Box.createVerticalGlue());

        gameWindow.setLayout(new BorderLayout());
        gameWindow.add(settingsPanel, BorderLayout.CENTER);
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
