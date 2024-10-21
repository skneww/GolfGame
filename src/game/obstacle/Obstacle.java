package obstacle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Polygon;

import entity.Ball;

/**
 * Represents obstacles in the game
 */
public class Obstacle {
    
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isTriangle;

    /**
    * Constructs a rectangular Obstacle.
    *
    * @param x the x-coordinate of the obstacle
    * @param y the y-coordinate of the obstacle
    * @param width the width of the obstacle
    * @param height the height of the obstacle
    */
    public Obstacle(int x,int y,int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height; 
        this.isTriangle = false;
    }

    /**
    * Constructs an Obstacle which is a triangle.
    *
    * @param x the x-coordinate of the obstacle
    * @param y the y-coordinate of the obstacle
    * @param width the width of the obstacle
    * @param height the height of the obstacle
    * @param isTriangle true if the obstacle is a triangle
    * 
    * !Not used!
    */
    public Obstacle(int x,int y,int width, int height, boolean isTriangle){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height; 
        this.isTriangle = isTriangle;
    }

    /**
    * Draws the obstacle on the screen.
    *
    * @param g2d the Graphics2D context to draw on
    */
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY);
        if(isTriangle){
            int[] xPoints = new int[]{x, x + width / 2, x + width };
            int[]  yPoints = new int[]{y + height, y , y + height};
            Polygon triangle = new Polygon(xPoints, yPoints, 3);
            g2d.fillPolygon(triangle); 
        } else {
            g2d.fillRect(x,y,width,height);
        }
      }

    /**
    * Gets the bounding rectangle of the obstacle.
    *
    * @return a Rectangle representing the bounds of the obstacle
    */  
    public Rectangle getBounds() {
        if (isTriangle) {
            //Approximate triangle with bounding rectangle
            return new Rectangle(x, y, width, height);
        } else {
            return new Rectangle(x, y, width, height);
        }
    }

    /**
    * Checks if the ball has collided with the obstacle.
    *
    * @param golfBall the Ball object to check collision against
    * @return true if there is a collision, false otherwise
    */
    public boolean checkCollision(Ball golfBall) {
        if (isTriangle) {
            // Verificăm coliziunea cu triunghiul
            int[] xPoints = { x, x + width / 2, x + width };
            int[] yPoints = { y + height, y, y + height };
            Polygon triangle = new Polygon(xPoints, yPoints, 3);
            return triangle.intersects(golfBall.getBounds());
        } else {
            return checkCircleRectangleCollision(golfBall);
        }
    }

    /**
    * Checks for collision between a circle (ball) and a rectangle (obstacle).
    *
    * @param ball the Ball object to check collision against
    * @return true if there is a collision, false otherwise
    */
    private boolean checkCircleRectangleCollision(Ball ball) {
        double circleDistanceX = Math.abs(ball.getX() - (x + width/2));
        double circleDistanceY = Math.abs(ball.getY() - (y + height/2));

        if (circleDistanceX > (width/2 + ball.getRadius())) {
            return false;
        }
        
        if (circleDistanceY > (height/2 + ball.getRadius())) {
            return false;
        }

        if (circleDistanceX < (width/2)) {
            return true;
        }

        if (circleDistanceY < (height/2)) {
            return true;
        }

        double cornerDistanceSq = Math.pow(circleDistanceX - width/2, 2) + Math.pow(circleDistanceY - height/2, 2);
        
        return (cornerDistanceSq <= Math.pow(ball.getRadius(),2));
    }
    
}  
