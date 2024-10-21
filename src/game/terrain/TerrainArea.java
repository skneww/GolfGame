package terrain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TerrainArea {
    private Rectangle area;
    private TerrainType terrainType;

    /**
    * Constructs a TerrainArea with specified position, size, and type.
    *
    * @param x the x-coordinate of the terrain area
    * @param y the y-coordinate of the terrain area
    * @param width the width of the terrain area
    * @param height the height of the terrain area
    * @param terrainType the type of terrain
    */
    public TerrainArea(int x, int y, int width, int height, TerrainType terrainType) {
        area = new Rectangle(x, y, width, height);
        this.terrainType = terrainType;
    }

    public Rectangle getArea() {
        return area;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    /**
    * Draws the terrain area onto the screen.
    *
    * @param g2d the Graphics2D context to draw on
    */
    public void draw(Graphics2D g2d) {
        Color terrainColor = terrainType.getColor();
        g2d.setColor(terrainColor);
        g2d.fill(area);
    }
}
