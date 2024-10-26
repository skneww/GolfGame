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

    /**
    * Constructs a LevelManager and initializes the levels.
    */
    public LevelManager() {
        levels = new ArrayList<>();
        initializeLevels();
        currentLevelIndex = 0;
    }

    /**
    * Initializes the levels by creating Level objects and adding them to the list.
    */
    private void initializeLevels() {
        // Level 1
        Level level1 = new Level(1);
        level1.setStartBall(new Ball(50,50));
        level1.setHole(new Hole(750,50));
        level1.setPar(3);

        addBorders(level1);
        
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

        levels.add(level1);

        // Level 2
        Level level2 = new Level(2);
        level2.setStartBall(new Ball(150, 450));
        level2.setHole(new Hole(650, 150));
        level2.setPar(4);


        addBorders(level2);

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

        level3.addObstacle(new Obstacle(450, 400, 20, 30));  
        level3.addObstacle(new Obstacle(450, 0, 20, 150)); 
        level3.addObstacle(new Obstacle(280, 400, 180, 20));
        level3.addObstacle(new Obstacle(260, 490, 20, 300));
        level3.addObstacle(new Obstacle(260, 0, 20, 90));
        level3.addObstacle(new Obstacle(260, 400, 20, 30));
        level3.addObstacle(new Obstacle(260, 150, 20, 260));

        level3.addTerrainArea(new TerrainArea(230, 430, 80, 60, TerrainType.SAND));
        level3.addTerrainArea(new TerrainArea(470, 400, 400, 60, TerrainType.WATER));

        try {
            Image level3background = ImageIO.read(getClass().getResource("/resources/backgrounds/GolfBackground.png"));
            level3.setImage(level3background);
        } catch (IOException e) {
            e.printStackTrace();
        }

        levels.add(level3);

        Level level4 = new Level(4);
        level4.setStartBall(new Ball(150, 460)); 
        level4.setHole(new Hole(750, 50));   
        level4.setPar(6);                       

        addBorders(level4);

        level4.addObstacle(new Obstacle(150, 530, 500, 40));
        level4.addObstacle(new Obstacle(150, 210, 500, 40));
        level4.addObstacle(new Obstacle(110, 490, 40, 40));
        level4.addObstacle(new Obstacle(110, 250, 40, 120));
        level4.addObstacle(new Obstacle(70, 370, 40, 120));
        level4.addObstacle(new Obstacle(650, 410, 40, 120));
        //level4.addObstacle(new Obstacle(690, 370, 40, 40));
        level4.addObstacle(new Obstacle(650, 250, 40, 120));
        level4.addObstacle(new Obstacle(550, 300, 60, 60));
        level4.addObstacle(new Obstacle(250, 350, 40, 100));
        level4.addObstacle(new Obstacle(190, 410, 100, 40));
        

        level4.addTerrainArea(new TerrainArea(650, 370, 40, 40, TerrainType.SAND));
        //level4.addTerrainArea(new TerrainArea(500, 400, 150, 60, TerrainType.SAND));
        level4.addTerrainArea(new TerrainArea(0, 560, 800, 40, TerrainType.WATER));

        try {
            Image level4background = ImageIO.read(getClass().getResource("/resources/backgrounds/GolfBackground.png"));
            level4.setImage(level4background);
        } catch (IOException e) {
            e.printStackTrace();
        }

        levels.add(level4);

    }

    /**
    * Adds border obstacles to a level.
    *
    * @param level the Level to add borders to
    */
    private void addBorders(Level level) {
        level.addObstacle(new Obstacle(0, 0, 800, 10));  // Top border
        level.addObstacle(new Obstacle(0, 590, 800, 10)); // Bottom border
        level.addObstacle(new Obstacle(0, 0, 10, 600));  // Left border
        level.addObstacle(new Obstacle(790, 0, 10, 600)); // Right border
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
