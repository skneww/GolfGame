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
        level1.setStartBall(new Ball(50,550));
        level1.setHole(new Hole(750,50));

        //Border
        level1.addObstacle(new Obstacle(0, 0, 800, 10));  // Top border
        level1.addObstacle(new Obstacle(0, 590, 800, 10)); // Bottom border
        level1.addObstacle(new Obstacle(0, 0, 10, 600));  // Left border
        level1.addObstacle(new Obstacle(790, 0, 10, 600)); // Right border

        level1.addObstacle(new Obstacle(300, 200, 200, 20)); //First middle Obstacle
        level1.addObstacle(new Obstacle(300, 200, 20, 200)); //Second middle Obstacle

        levels.add(level1);

        // Level 2
        Level level2 = new Level(2);
        level2.setStartBall(new Ball(50, 550));
        level2.setHole(new Hole(750, 50));

        //Border
        level2.addObstacle(new Obstacle(0, 0, 800, 10));  // Top border
        level2.addObstacle(new Obstacle(0, 590, 800, 10)); // Bottom border
        level2.addObstacle(new Obstacle(0, 0, 10, 600));  // Left border
        level2.addObstacle(new Obstacle(790, 0, 10, 600)); // Right border

        level2.addObstacle(new Obstacle(400, 150, 150, 20));
        level2.addObstacle(new Obstacle(550, 150, 20, 300));
        level2.addObstacle(new Obstacle(550, 450, 150, 20));
        
        levels.add(level2);

        // Level 3
        Level level3 = new Level(3);
        level3.setStartBall(new Ball(400, 550));
        level3.setHole(new Hole(400, 50));

        //Border
        level3.addObstacle(new Obstacle(0, 0, 800, 10));  // Top border
        level3.addObstacle(new Obstacle(0, 590, 800, 10)); // Bottom border
        level3.addObstacle(new Obstacle(0, 0, 10, 600));  // Left border
        level3.addObstacle(new Obstacle(790, 0, 10, 600)); // Right border

        level3.addObstacle(new Obstacle(450, 250, 20, 150));  
        level3.addObstacle(new Obstacle(300, 400, 170, 20));
        
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
