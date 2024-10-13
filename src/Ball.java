import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.util.List;

public class Ball {
    private double x, y;
    private double Xvelocity = 0;
    private double Yvelocity = 0;
    private final double radius = 10;
    private final double friction = 0.99;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update(List<Obstacle> obstacles) {  
        x += Xvelocity;
        y += Yvelocity; 
        Xvelocity *= friction;
        Yvelocity *= friction;
    
        
        for (Obstacle obstacle : obstacles) { 
            if (obstacle.checkCollision(this)) {  
                handleCollision(obstacle);  
            }
        }
    
        
        if (x - radius < 0 || x + radius > 800) {
            Xvelocity = -Xvelocity;  
            x = Math.max(radius, Math.min(x, 800 - radius)); 
        }
    
        if (y - radius < 0 || y + radius > 600) {
            Yvelocity = -Yvelocity;  
            y = Math.max(radius, Math.min(y, 600 - radius));  
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
    private void handleCollision(Obstacle obstacle) {
        Xvelocity = -Xvelocity;
        Yvelocity = -Yvelocity;
    }

    // Return the bounding box of the ball
    <publicgle> Rectangle getBounds() {
        return new Rectangle((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }
}
