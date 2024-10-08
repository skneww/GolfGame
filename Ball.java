import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ball {
  private double x,y;
  private double Xvelocity = 0;
  private double Yvelocity = 0;
  private final double radius = 10;
  private final double friction = 1;  //poate 0.99?
  private final double gravity = 0.5;    


  public Ball(double x, double y){
    this.x = x;
    this.y = y;
  }

  public void update() {
    x = x + Xvelocity; //adauga cat tragi de mouse velocitate
    y = y + Yvelocity; //adauga cat tragi de mouse velocitate
    Yvelocity += gravity;
    Xvelocity *= friction;
    Yvelocity *= friction;
    if (x - radius < 0 || x + radius > 800) { 
      Xvelocity = -Xvelocity;
  }
  if (y - radius < 0 || y + radius > 600) {  
    Yvelocity = -Yvelocity; 
  }
    
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(Color.WHITE);
    g2d.fill(new Ellipse2D.Double(x - radius, y - radius, radius * 2 , radius * 2));
  }

  public double getX() { return x; }
  public double getY() { return y; }
  public void setXY(double x, double y) { this.x = x; this.y = y; }
  public void setVelocity(double Xvelocity, double Yvelocity) {
    this.Xvelocity = Xvelocity;
    this.Yvelocity = Yvelocity;
}
}

