import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class GameWindow extends JPanel 
    implements Runnable, MouseListener {
    //Window Size
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    //Game Loop Variables
    private Thread gameThread;
    private boolean running = false;
    Ball golfBall;

    public GameWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.ORANGE);
        this.setDoubleBuffered(true);
        startGame();
    }

    private void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //Frames and time of loop
        final double FPS = 60.0;
        final double TargetTime = 1e9 / FPS;

        long startTime = System.nanoTime();
        double elapsed = 0;

        // * Game Loop * //
        while (running) {
            long currentTime = System.nanoTime();
            elapsed += (currentTime - startTime) / TargetTime;
            startTime = currentTime;

            if (elapsed > 1) {
                processInput();
                updateGame();
                renderGame();
                elapsed--;
                System.out.println("elapsed : " + elapsed + "start Time: " + startTime + "current Time: " + currentTime);
            }
            
        }
    }

    private void processInput() {

    }

    private void updateGame() {
        
    }

    private void renderGame() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean ballPlaced = false;
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (!ballPlaced) {
            golfBall = new Ball(mouseX,mouseY);
        } else {
            golfBall.setXY(mouseX, mouseY);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    
}