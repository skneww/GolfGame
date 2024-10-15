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
    private double yVelocity = 0;
    private final double radius = 10;
    private final double DEFAULT_FRICTION = 0.99;
    private final double STOP_THRESHOLD = 0.1;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update(List<Obstacle> obstacles, List<TerrainArea> terrainAreas) {  
        int steps = (int) Math.ceil(Math.max(Math.abs(xVelocity), Math.abs(yVelocity) / radius));
        steps = Math.max(1, steps);

        double stepX = xVelocity / steps;
        double stepY = yVelocity / steps;

        for (int i = 0; i < steps; i++ ) {
            x += stepX;
            y += stepY;

            //Wall colisions
            if (x - radius < 0 || x + radius > GameWindow.WIDTH) {
                xVelocity = -xVelocity;
                x = Math.max(radius, Math.min(x, GameWindow.WIDTH - radius));
            }
            if (y - radius < 0 || y + radius > GameWindow.HEIGHT) {
                yVelocity = -yVelocity;
                y = Math.max(radius, Math.min(x, GameWindow.HEIGHT - radius));
            }

            //Obstacle Collision
            for (Obstacle obstacle : obstacles) { 
                if (obstacle.checkCollision(this)) {  
                    handleCollision(obstacle);  
                }
            }
        }

        //Apply Friction
        double currentFriction = getCurrentFriction(terrainAreas);
        xVelocity *= currentFriction;
        yVelocity *= currentFriction;
    
        //Stop the ball if it's moving very slowly
        if (Math.abs(xVelocity) < 0.1) {
            xVelocity = 0;
        }
        if (Math.abs(yVelocity) < 0.1) { 
            yVelocity = 0;
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

    public void setVelocity(double xVelocity, double yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public boolean isMoving() {
        return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity) > STOP_THRESHOLD;
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
                distanceY = yVelocity;
                distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            }

            //normal vector
            double nX = distanceX/distance;
            double nY = distanceY/distance;

            //remove ball from obstacle
            x += nX * overlap;
            y += nY * overlap;

            //reverse velocity
            double dotProduct = xVelocity * nX + yVelocity * nY;
            xVelocity -= 2 * dotProduct * nX;
            yVelocity -= 2 * dotProduct * nY;

            //DEFAULT_FRICTION
            xVelocity *= DEFAULT_FRICTION;
            yVelocity *= DEFAULT_FRICTION;
        }

        xVelocity *= 0.9;
        yVelocity *= 0.9;
    }

    // Return the bounding box of the ball
    public Rectangle getBounds() {
        return new Rectangle((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }
}
