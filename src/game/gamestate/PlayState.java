package gamestate;

import java.awt.Graphics2D;
import java.awt.Color;
import main.GameWindow;
import entity.Ball;
import entity.Hole;
import level.Level;
import level.LevelManager;
import input.MouseHandler;

public class PlayState extends State {

    private GameWindow gameWindow;
    private LevelManager levelManager;
    private Ball golfBall;
    private Hole hole;
    private MouseHandler mouseHandler;

    private int score = 0;

    public PlayState(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        levelManager = new LevelManager();
        loadLevel(0);
    }

    private void loadLevel(int index) {
        Level level = levelManager.getLevel(index);
        if (level != null) {
            golfBall = level.getStartBall();
            hole = level.getHole();
            mouseHandler = new MouseHandler(golfBall, this);
            gameWindow.addMouseListener(mouseHandler);
            gameWindow.addMouseMotionListener(mouseHandler);
        } else {
            gameWindow.changeState(new GameOverState(gameWindow, score));
        }
    }

    @Override
    public void update() {
        if (golfBall != null) {
            golfBall.update(levelManager.getCurrentLevel().getObstacles());
        }
        if (hole != null && golfBall != null && hole.isBallInHole(golfBall)) {
            levelManager.nextLevel();
            loadLevel(levelManager.getCurrentLevelIndex());
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(gameWindow.getBackground());
        g2d.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        levelManager.getCurrentLevel().render(g2d);
        if (golfBall != null) {
            golfBall.draw(g2d);
        }
        if (hole != null) {
            hole.draw(g2d);
        }
        if (mouseHandler != null && mouseHandler.isDragging()) {
            mouseHandler.draw(g2d);
        }

        // Draw score
        g2d.setColor(Color.WHITE);
        g2d.drawString("Strokes: " + score, 10, 20);
        g2d.drawString("Level: " + (levelManager.getCurrentLevelIndex() + 1), 10, 40);
    }

    public void incrementScore() {
        score++;
    }
}
