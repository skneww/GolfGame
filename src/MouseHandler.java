import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    protected double pressX;
    protected double pressY;
    private Ball golfBall;
    public boolean isDragging = false;
    public double currentMouseX;
    public double currentMouseY;

    public MouseHandler(Ball ball) {
        this.golfBall = ball;
    }

    @Override 
    public void mousePressed(MouseEvent e) {
        pressX = e.getX();
        pressY = e.getY();
        isDragging = true;
        currentMouseX = e.getX();
        currentMouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        double releaseX = e.getX();
        double releaseY = e.getY();

        //Calculate velocity
        double velocityX = (pressX - releaseX) * 0.1;
        double velocityY = (pressY - releaseY) * 0.1;

        golfBall.setVelocity(velocityX, velocityY);
        isDragging = false;
    }

    @Override 
    public void mouseDragged(MouseEvent e) {
        currentMouseX = e.getX();
        currentMouseY = e.getY();
    }
}