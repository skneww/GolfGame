import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseHandler extends MouseAdapter {
  
  private double pressX, pressY;
  private Ball golfBall;

  public MouseHandler(Ball ball) {
    this.golfBall = ball;
  }

  @Override 
  public void mousePressed(MouseEvent e) {
    pressX = e.getX();
    pressY = e.getY();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    double releaseX = e.getX();
    double releaseY = e.getY();

    //Calculate velocity
    double velocityX = (pressX - releaseX) * 0.1;
    double velocityY = (pressY - releaseY) * 0.1;

    golfBall.setVelocity(velocityX, velocityY);
  }
}