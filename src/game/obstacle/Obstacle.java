package obstacle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Polygon;

import entity.Ball;

public class Obstacle {
    
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isTriangle;

    public Obstacle (int x,int y,int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height; 
        this.isTriangle = false;
    }

    public Obstacle (int x,int y,int width, int height, boolean isTriangle){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height; 
        this.isTriangle = isTriangle;
    }

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

      public Rectangle getBounds() {
        if (isTriangle) {
            //Approximate triangle with bounding rectangle
            return new Rectangle(x, y, width, height);
        } else {
            return new Rectangle(x, y, width, height);
        }
    }
    
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
