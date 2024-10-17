package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.util.List;
import obstacle.Obstacle;
import main.GameWindow;
import terrain.*;


public class Ball {
    private double x, y;
    private double xVelocity = 0;
    private double Yvelocity = 0;
    private final double radius = 10;
    private final double DEFAULT_FRICTION = 0.99;
    private final double STOP_THRESHOLD = 0.1;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update(List<Obstacle> obstacles, List<TerrainArea> terrainAreas) {  
        x += xVelocity;
        y += Yvelocity;

        double currentFriction = getCurrentFriction(terrainAreas);
        xVelocity *= currentFriction;
        Yvelocity *= currentFriction;
    
        //Stop the ball if it's moving very slowly
        if (Math.abs(xVelocity) < 0.1) xVelocity = 0;
        if (Math.abs(Yvelocity) < 0.1) Yvelocity = 0;

        //Wall colisions
        if (x - radius < 0) {
            x =  radius;
            xVelocity = -xVelocity;
        }
        if (x + radius > GameWindow.WIDTH) {
            x = GameWindow.WIDTH - radius;
            xVelocity = -xVelocity;
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

    private double getCurrentFriction(List<TerrainArea> terrainAreas) {
        for (TerrainArea terrainArea : terrainAreas) {
            if (terrainArea.getArea().contains(x,y)) {
                return terrainArea.getTerrainType().getFriction();
            }
        }
        return DEFAULT_FRICTION;
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

    public double getRadius() {
        return radius;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVelocity(double xVelocity, double Yvelocity) {
        this.xVelocity = xVelocity;
        this.Yvelocity = Yvelocity;
    }

    public boolean isMoving() {
        return Math.sqrt(xVelocity * xVelocity + Yvelocity * Yvelocity) > STOP_THRESHOLD;
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
                distanceX = xVelocity;
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
            double dotProduct = xVelocity * nX + Yvelocity * nY;
            xVelocity -= 2 * dotProduct * nX;
            Yvelocity -= 2 * dotProduct * nY;

            //DEFAULT_FRICTION
            xVelocity *= DEFAULT_FRICTION;
            Yvelocity *= DEFAULT_FRICTION;
        }
    }

    // Return the bounding box of the ball
    public Rectangle getBounds() {
        return new Rectangle((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }
}
