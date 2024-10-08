import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ball {
  private double x,y;
  private final double radius = 10;

  public Ball(double x, double y){
    this.x = x;
    this.y = y;
  }

  public void update() {
    
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(Color.WHITE);
    g2d.fill(new Ellipse2D.Double(x - radius, y - radius, radius * 2 , radius * 2));
  }

  public double getX() { return x; }
  public double getY() { return y; }
  public void setXY(double x, double y) { this.x = x; this.y = y; }
}
