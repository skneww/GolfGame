// TerrainArea.java
package terrain;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import terrain.TerrainType;

public class TerrainArea {
    private Rectangle area;
    private TerrainType terrainType;

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

    public void draw(Graphics2D g2d) {
        g2d.setColor(terrainType.getColor());
        g2d.fill(area);
    }
}
