package level;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import entity.Ball;
import entity.Hole;
import obstacle.Obstacle;
import terrain.TerrainArea;

public class Level {

    private int levelNumber;
    private int par;
    private Ball startBall;
    private Hole hole;
    private List<Obstacle> obstacles;
    private List<TerrainArea> terrainAreas;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        obstacles = new ArrayList<>();
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getPar() {
        return par;
    }

    public void addTerrainArea(TerrainArea terrainArea) {
        terrainAreas.add(terrainArea);
    }

    public List<TerrainArea> getTerrainAreas() {
        return terrainAreas;
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
        //Draw terrain first
        for (TerrainArea terrainArea : terrainAreas) {
            terrainArea.draw(g2d);
        }
        //Draw obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g2d);
        }
    }
}