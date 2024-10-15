package level;

import java.util.ArrayList;
import java.util.List;

import entity.Ball;
import entity.Hole;
import obstacle.Obstacle;
public class LevelManager {

    private List<Level> levels;
    private int currentLevelIndex;

    public LevelManager() {
        levels = new ArrayList<>();
        initializeLevels();
        currentLevelIndex = 0;
    }

    private void initializeLevels() {
        // Level 1
        Level level1 = new Level(1);
        level1.addObstacle(new Obstacle(200, 100, 50, 400));
        level1.addObstacle(new Obstacle(550, 100, 50, 400));
        level1.addObstacle(new Obstacle(370, 200, 50, 350));
        level1.setStartBall(new Ball(50, 50));
        level1.setHole(new Hole(750, 300));
        levels.add(level1);

        // Level 2
        Level level2 = new Level(2);
        level2.addObstacle(new Obstacle(200, 50, 200, 200, true));
        level2.addObstacle(new Obstacle(600, 400, 30, 60, true));
        level2.addObstacle(new Obstacle(300, 300, 50, 50, true));
        level2.setStartBall(new Ball(50, 300));
        level2.setHole(new Hole(750, 300));
        levels.add(level2);

        // Level 3
        Level level3 = new Level(3);
        level3.addObstacle(new Obstacle(300, 300, 50, 400));
        level3.setStartBall(new Ball(50, 550));
        level3.setHole(new Hole(200, 100));
        levels.add(level3);
    }

    public Level getCurrentLevel() {
        if (currentLevelIndex >= 0 && currentLevelIndex < levels.size()) {
            return levels.get(currentLevelIndex);
        } else {
            return null;
        }
    }

    public int getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    public Level getLevel(int index) {
        if (index >= 0 && index < levels.size()) {
            return levels.get(index);
        } else {
            return null;
        }
    }

    public void nextLevel() {
        currentLevelIndex++;
    }
}
