import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ball {
  private double x,y;
  private double velocityX = 0;
  private double velocityY = 0;
  private final double radius = 9f;
  private final double friction = 0.99;  //chiar 0.99  
  private final double restitution = 0.8; //slow down after collision

  public Ball(double x, double y){
    this.x = x;
    this.y = y;
  }

  public void update() {
    x += velocityX; //adauga cat tragi de mouse velocitate
    y += velocityY; //adauga cat tragi de mouse velocitate

    velocityX *= friction;
    velocityY *= friction;

    if (x - radius < 0) {
      x = radius;
      velocityX = -velocityX * restitution;
    } else if (x + radius > GameWindow.WIDTH) {
      x = GameWindow.WIDTH - radius;
      velocityX = -velocityX * restitution;
    }

    if (y - radius < 0) {
      y = radius;
      velocityY = -velocityY * restitution;
    } else if (y + radius > GameWindow.HEIGHT) {
      y = GameWindow.HEIGHT - radius;
      velocityY = -velocityY * restitution;
    }
    
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(Color.WHITE);
    g2d.fill(new Ellipse2D.Double(x - radius, y - radius, radius * 2 , radius * 2));
  }

  public double getX() { return x; }
  public double getY() { return y; }
  public void setXY(double x, double y) { this.x = x; this.y = y; }
  public void setVelocity(double velocityX, double velocityY) {
    this.velocityX = velocityX;
    this.velocityY = velocityY;
}
}

