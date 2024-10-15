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
    
        //Wall colisions
        if (x - radius < 0) {
            x =  radius;
            Xvelocity = -Xvelocity;
        }
        if (x+radius > GameWindow.WIDTH) {
            x = GameWindow.WIDTH - radius;
            Xvelocity = -Xvelocity;
        }
    
        if (y - radius < 0) {
            y = radius;
            Yvelocity = -Yvelocity;  
        }
        if (y + radius > GameWindow.HEIGHT) {
            y = GameWindow.HEIGHT - radius;
            Yvelocity = -Yvelocity;
        }

        //Obstacle Collision
        for (Obstacle obstacle : obstacles) { 
            if (obstacle.checkCollision(this)) {  
                handleCollision(obstacle);  
            }
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

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private void handleCollision(Obstacle obstacle) {
        Rectangle rect = obstacle.getBounds();

        //closest point on rect to the ball
        double closestX = clamp(x, rect.x, rect.x + rect.width);
        double closestY = clamp(y, rect.y, rect.y + rect.height);

        //circle center to point
        double distanceX = x - closestX;
        double distanceY = y - closestY;

        double distanceSquared = distanceX * distanceX + distanceY * distanceY;

        if (distanceSquared < radius * radius) {

            double distance = Math.sqrt(distanceSquared);
            double overlap = radius - distance;

            //Prevent division by zero
            if (distance == 0) {
                distanceX = Xvelocity;
                distanceY = Yvelocity;
                distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            }

            //normal vector
            double nX = distanceX/distance;
            double nY = distanceY/distance;

            //remove ball from obstacle
            x += nX * overlap;
            y += nY * overlap;

            //reverse velocity
            double dotProduct = Xvelocity * nX + Yvelocity * nY;
            Xvelocity -= 2 * dotProduct * nX;
            Yvelocity -= 2 * dotProduct * nY;

            //friction
            Xvelocity *= friction;
            Yvelocity *= friction;
        }
    }

    // Return the bounding box of the ball
    <publicgle> Rectangle getBounds() {
        return new Rectangle((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }
}
