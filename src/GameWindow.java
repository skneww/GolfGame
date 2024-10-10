import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
        //Initialize GolfBall
        golfBall = new Ball(WIDTH/2, HEIGHT/2);
        //Start MouseListener and Handler
        MouseHandler mouseHandler = new MouseHandler(golfBall);
        this.addMouseListener(mouseHandler);

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
        final double TARGETTIME = 1e9 / FPS;
        double nextTime = System.nanoTime() + TARGETTIME;

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

                nextTime += TARGETTIME;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processInput() {
        //!Unused yet!//
    }

    private void updateGame() {
        golfBall.update();
    }

    private void renderGame() {
        repaint();
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        golfBall.draw((Graphics2D) g);
    }
}