package level;

import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;


import entity.Ball;
import entity.Hole;
import obstacle.Obstacle;
import terrain.*;
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
        level1.setStartBall(new Ball(50,50));
        level1.setHole(new Hole(750,50));
        level1.setPar(3);

        //Border
        level1.addObstacle(new Obstacle(0, 0, 800, 10));  // Top border
        level1.addObstacle(new Obstacle(0, 590, 800, 10)); // Bottom border
        level1.addObstacle(new Obstacle(0, 0, 10, 600));  // Left border
        level1.addObstacle(new Obstacle(790, 0, 10, 600)); // Right border

        level1.addObstacle(new Obstacle(150, 0, 40, 400));
        level1.addObstacle(new Obstacle(350, 200, 40, 500));
        level1.addObstacle(new Obstacle(550, 0, 40, 350));

        TerrainArea sandTrap1 = new TerrainArea(150, 300, 150, 100, TerrainType.SAND);
        level1.addTerrainArea(sandTrap1);

        try {
            Image level1background = ImageIO.read(getClass().getResource("/resources/backgrounds/GolfBackground.png"));
            level1.setImage(level1background);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //levels.add(level1);

        // Level 2
        Level level2 = new Level(2);
        level2.setStartBall(new Ball(50, 550));
        level2.setHole(new Hole(750, 50));
        level2.setPar(4);

        //Border
        level2.addObstacle(new Obstacle(0, 0, 800, 10));  // Top border
        level2.addObstacle(new Obstacle(0, 590, 800, 10)); // Bottom border
        level2.addObstacle(new Obstacle(0, 0, 10, 600));  // Left border
        level2.addObstacle(new Obstacle(790, 0, 10, 600)); // Right border

        level2.addObstacle(new Obstacle(175,500, 450, 30));
        level2.addObstacle(new Obstacle(75,470, 100, 30));
        level2.addObstacle(new Obstacle(625,470, 100, 30));
        level2.addObstacle(new Obstacle(45,440, 30, 30));
        level2.addObstacle(new Obstacle(725,440, 30, 30));
        level2.addObstacle(new Obstacle(75,340, 30, 100));
        level2.addObstacle(new Obstacle(695,340, 30, 100));
        level2.addObstacle(new Obstacle(105,110, 30, 230));
        level2.addObstacle(new Obstacle(665,110, 30, 230));
        level2.addObstacle(new Obstacle(135,80, 30, 30));
        level2.addObstacle(new Obstacle(635,80, 30, 30));
        level2.addObstacle(new Obstacle(165,110, 30, 30));
        level2.addObstacle(new Obstacle(605,110, 30, 30));
        level2.addObstacle(new Obstacle(195,140, 30, 30));
        level2.addObstacle(new Obstacle(575,140, 30, 30));
        level2.addObstacle(new Obstacle(225,170, 350, 30));
        level2.addObstacle(new Obstacle(200, 250, 50, 150));
        level2.addObstacle(new Obstacle(400, 250, 50, 150));

        try {
            Image level2background = ImageIO.read(getClass().getResource("/resources/backgrounds/GolfBackground.png"));
            level2.setImage(level2background);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        levels.add(level2);

        // Level 3
        Level level3 = new Level(3);
        level3.setStartBall(new Ball(400, 550));
        level3.setHole(new Hole(400, 50));
        level3.setPar(5);

        //Border
        level3.addObstacle(new Obstacle(0, 0, 800, 10));  // Top border
        level3.addObstacle(new Obstacle(0, 590, 800, 10)); // Bottom border
        level3.addObstacle(new Obstacle(0, 0, 10, 600));  // Left border
        level3.addObstacle(new Obstacle(790, 0, 10, 600)); // Right border

        level3.addObstacle(new Obstacle(450, 250, 20, 150));  
        level3.addObstacle(new Obstacle(300, 400, 170, 20));

        try {
            Image level3background = ImageIO.read(getClass().getResource("/resources/backgrounds/GolfBackground.png"));
            level3.setImage(level3background);
        } catch (IOException e) {
            e.printStackTrace();
        }

        levels.add(level3);

        Level level4 = new Level(4);
        level4.setStartBall(new Ball(50, 300));
        level4.setHole(new Hole(750, 300));
        level4.setPar(4);

        //Border
        level4.addObstacle(new Obstacle(0, 0, 800, 10));  // Top border
        level4.addObstacle(new Obstacle(0, 590, 800, 10)); // Bottom border
        level4.addObstacle(new Obstacle(0, 0, 10, 600));  // Left border
        level4.addObstacle(new Obstacle(790, 0, 10, 600)); // Right border

        //Obstacles
        level4.addObstacle(new Obstacle(200, 100, 20, 400));
        level4.addObstacle(new Obstacle(400, 100, 20, 400));
        level4.addObstacle(new Obstacle(600, 100, 20, 400));

        //Terrain
        TerrainArea sandTrap4 = new TerrainArea(300, 250, 100, 100, TerrainType.SAND);
        TerrainArea waterTrap4 = new TerrainArea(300, 350, 100, 100, TerrainType.WATER);
        level4.addTerrainArea(sandTrap4);
        level4.addTerrainArea(waterTrap4);

        try {
            Image level4background = ImageIO.read(getClass().getResource("/resources/backgrounds/GolfBackground.png"));
            level4.setImage(level4background);
        } catch (IOException e) {
            e.printStackTrace();
        }

        levels.add(level4);
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
