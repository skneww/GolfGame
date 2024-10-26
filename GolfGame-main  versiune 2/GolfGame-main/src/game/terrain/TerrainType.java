package terrain;

import java.awt.Color;

public enum TerrainType {
    GRASS(0.99, new Color(34, 139, 34)),
    SAND(0.90,new Color(194, 178, 128)),
    WATER(0.80, new Color(30, 144, 255));

    private final double friction;
    private final Color color;

    /**
    * Constructs a TerrainType with specified friction and color.
    *
    * @param friction the friction coefficient of the terrain
    * @param color the color representing the terrain
    */
    TerrainType(double friction, Color color) {
        this.friction = friction;
        this.color = color;
    }

    public double getFriction() {
        return friction;
    }

    public Color getColor() {
        return color;
    }
}
