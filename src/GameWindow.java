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
    //Frames and time of loop
    private int FPS = 60;
    private long TargetTime = 1000 / FPS;

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
        // * Game Loop * //
        double startTime = System.nanoTime();
        while (running) {
            double current = System.nanoTime();
            double elapsed = current - startTime;
            processInput();
            updateGame();
            renderGame();
            
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
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