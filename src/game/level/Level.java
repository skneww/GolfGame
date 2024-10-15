package level;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import entity.Ball;
import entity.Hole;
import obstacle.Obstacle;

public class Level {

    private int levelNumber;
    private Ball startBall;
    private Hole hole;
    private List<Obstacle> obstacles;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        obstacles = new ArrayList<>();
    }
    
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getLevelNumber () {
        return levelNumber;
    }

    public void setStartBall(Ball ball) {
        this.startBall = ball;
    }

    public Ball getStartBall() {
        return startBall;
    }

    public void setHole(Hole hole) {
        this.hole = hole;
    }

    public Hole getHole() {
        return hole;
    }

    public void render(Graphics2D g2d) {
        //Draw obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g2d);
        }
    }
}