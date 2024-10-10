import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;

public class Ball {
    private double x, y;
    private double Xvelocity = 0;
    private double Yvelocity = 0;
    private final double radius = 10;
    private final double friction = 0.99;
    private final double gravity = 0.5;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        x += Xvelocity; // Update x position based on velocity
        y += Yvelocity; // Update y position based on velocity
        Yvelocity += gravity; // Apply gravity

        // Apply friction
        Xvelocity *= friction;
        Yvelocity *= friction;

        // Bounce off left and right walls
        if (x - radius < 0 || x + radius > 800) {
            Xvelocity = -Xvelocity; // Reverse x velocity
            x = Math.max(radius, Math.min(x, 800 - radius)); // Keep ball within bounds
        }

        // Bounce off top and bottom walls
        if (y - radius < 0 || y + radius > 600) {
            Yvelocity = -Yvelocity; // Reverse y velocity
            y = Math.max(radius, Math.min(y, 600 - radius)); // Keep ball within bounds
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fill(new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getRadius() {
        return (int) radius;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVelocity(double Xvelocity, double Yvelocity) {
        this.Xvelocity = Xvelocity;
        this.Yvelocity = Yvelocity;
    }

    // Return the bounding box of the ball
    public Rectangle getBounds() {
        return new Rectangle((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }
}
