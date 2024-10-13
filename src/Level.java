import java.util.List;

public class Level {
    private int levelNumber;
    private Ball startBall;
    private List<Obstacle> obstacles;

    public Level(int levelNumber, Ball startBall, List<Obstacle> obstacles) {
        this.levelNumber = levelNumber;
        this.startBall = startBall;
        this.obstacles = obstacles;
    }
    
    public Ball getStartBall() {
        return startBall;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
    public int getLevelNumber() {
        return levelNumber;
    }

}