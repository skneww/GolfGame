package input;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import entity.Ball;
import gamestate.PlayState;
import main.GameWindow;
import sound.*;

public class MouseHandler extends MouseAdapter {

    protected double pressX;
    protected double pressY;
    private Ball golfBall;
    private PlayState playState;
    public boolean isDragging = false;
    private boolean canDrag = false;
    public double currentMouseX;
    public double currentMouseY;
    private double power;
    

    public MouseHandler(Ball ball, PlayState playState) {
        this.golfBall = ball;
        this.playState = playState;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public void draw(Graphics2D g2d) {
        if (isDragging && !golfBall.isMoving()) {
            //arrow coordinates
            double deltaX = currentMouseX - pressX;
            double deltaY = currentMouseY - pressY;
            double angle = Math.atan2(deltaY, deltaX);
            double arrowLength = power * 2; //can change for scale;

            int startX = (int) pressX;
            int startY = (int) pressY;
            int endX = (int) (pressX - Math.cos(angle) * arrowLength);
            int endY = (int) (pressY - Math.sin(angle) * arrowLength);

            //Draw arrow line
            g2d.setColor(Color.RED);
            g2d.drawLine(startX,startY,endX,endY);

            //Draw arrowhead
            int arrowSize = 10;
            int x1 = (int) (endX + arrowSize * Math.cos(angle + Math.PI / 6));
            int y1 = (int) (endY + arrowSize * Math.sin(angle + Math.PI / 6));
            int x2 = (int) (endX + arrowSize * Math.cos(angle - Math.PI / 6));
            int y2 = (int) (endY + arrowSize * Math.sin(angle - Math.PI / 6));

            g2d.drawLine(endX, endY, x1, y1);
            g2d.drawLine(endX, endY, x2, y2);

            //PowerBar Visualisation
            int barWidth = 200;
            int barHeight = 20;
            int barX = 10;
            int barY = GameWindow.HEIGHT - barHeight - 10;

            //Background of bar
            g2d.setColor(Color.GRAY);
            g2d.fillRect(barX, barY, barWidth, barHeight);

            //Current power level
            g2d.setColor(Color.RED);
            int powerWidth = (int) (barWidth * (power/100));
            g2d.fillRect(barX, barY, powerWidth, barHeight);

            //Border
            g2d.setColor(Color.WHITE);
            g2d.drawRect(barX, barY, barWidth, barHeight);

            //Power Level
            g2d.setColor(Color.WHITE);
            g2d.drawString("Power :" + (int) power + "%" , barX + barWidth + 10, barY + barHeight - 10);
        }
    }
    
    @Override 
    public void mousePressed(MouseEvent e) {
        if (!golfBall.isMoving()) {
            double mx = e.getX();
            double my = e.getY();
            double dx = mx - golfBall.getX();
            double dy = my - golfBall.getY();
            double distance = Math.sqrt(dx*dx + dy*dy);

            if (distance <= golfBall.getRadius()) {
                canDrag = true;
                pressX = e.getX();
                pressY = e.getY();
                isDragging = true;
                currentMouseX = e.getX();
                currentMouseY = e.getY();
            } else {
                canDrag = false;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!golfBall.isMoving() && isDragging && canDrag) {
            double deltaX = pressX - e.getX();
            double deltaY = pressY - e.getY();
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double maxDistance = 200;
            power = Math.min(distance / maxDistance * 100, 100);

            double angle = Math.atan2(deltaY, deltaX);
            double velocity = power * 0.2; //0.2 scaling factor
            double velocityX = Math.cos(angle) * velocity;
            double velocityY = Math.sin(angle) * velocity;

            golfBall.setVelocity(velocityX, velocityY);
            isDragging = false;

            Sound hitSound = new Sound("golfBallHit.wav");
            hitSound.play();

            //Score increment
            if (playState != null) {
                playState.incrementScore();
            }
        }
    }

    @Override 
    public void mouseDragged(MouseEvent e) {
        if (isDragging && !golfBall.isMoving() && canDrag) {
            currentMouseX = e.getX();
            currentMouseY = e.getY();

            double deltaX = pressX - currentMouseX;
            double deltaY = pressY - currentMouseY;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double maxDistance = 200;
            power = Math.min(distance / maxDistance * 100 , 100);
        }
    }
}