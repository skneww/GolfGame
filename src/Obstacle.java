import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Polygon;
public class Obstacle {
    enum ShapeType {
        RECTANGLE,TRIANGLE
    }
    enum TriangleOrientation{
        UP, DOWN, LEFT, RIGHT;
    }
    private int x,y,width,height;
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
        } else{
            g2d.fill(new Rectangle.Double(x , y , width , height));
        }
      }
      public Rectangle getBounds() {
        if (isTriangle) {
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
            return checkCircleTriangleCollision(golfBall, triangle);
        } else {
            return checkCircleRectangleCollision(golfBall, getBounds());
        }
    }
    
    private boolean checkCircleTriangleCollision(Ball golfBall, Polygon triangle) {
        Rectangle ballBounds = golfBall.getBounds();  
        return triangle.intersects(ballBounds);
    }
    private boolean checkCircleRectangleCollision(Ball ball, Rectangle rect) {
        Rectangle ballBounds = ball.getBounds();  
        return rect.intersects(ballBounds); 
    }
    
}  
