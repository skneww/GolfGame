import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Hole {
    
    private double x;
    private double y;
    private final double radius = 13;

    public Hole(int level) {
        if (level == 1) {
            this.x = 200;
            this.y = 300;
        } else if (level == 2) {
            this.x = 600;
            this.y = 400;
        } else if (level == 3) {
            this.x = 200;
            this.y = 100;
        } else {
            this.x = 500;
            this.y = 700;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fill(new Ellipse2D.Double(x - radius, y - radius, radius * 2 , radius * 2));
    }
}


