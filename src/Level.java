import java.util.ArrayList;
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
    public int getLevelNumber() {
        return levelNumber;
    }
    
    public Ball getStartBall() {
        return startBall;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public static void main(String[] args) {
        List<Level> levels = new ArrayList<>();

        List<Obstacle> level1obstacles = new ArrayList<>();
        level1obstacles.add(new Obstacle(300, 300, 50, 50));
        level1obstacles.add(new Obstacle(600, 700, 30, 60));
        level1obstacles.add(new Obstacle(421, 555, 69, 90));
        Level level1 = new Level(1, new Ball(50, 550), level1obstacles);
        levels.add(level1);

        List<Obstacle> level2obstacles = new ArrayList<>();
        level2obstacles.add(new Obstacle(300, 300, 50, 50, true));
        level2obstacles.add(new Obstacle(600, 700, 30, 60, true));
        level2obstacles.add(new Obstacle(421, 555, 69, 90, true));
        Level level2 = new Level(2, new Ball(50, 550), level2obstacles);
        levels.add(level2);
    }
}