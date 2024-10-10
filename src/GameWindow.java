import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameWindow extends JPanel 
    implements Runnable {
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
        double nextTime = System.nanoTime() + TargetTime;

        // * Game Loop * //
        while (running) {  
            processInput();

            updateGame();

            renderGame();

            try {
                double remainingTime = nextTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0 ) {
                    remainingTime = 0;
                }
                System.out.println(remainingTime);

                Thread.sleep((long) remainingTime);

                nextTime += TargetTime;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processInput() {

    }

    private void updateGame() {
        
    }

    private void renderGame() {
    }
    
}