import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.geom.Line2D;

public class GameWindow extends JPanel 
    implements Runnable {
    //Window Size
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    //Game Loop Variables
    private Thread gameThread;
    private boolean running = false;
    Ball golfBall;
    MouseHandler mouseHandler;

    public GameWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.ORANGE);
        this.setDoubleBuffered(true);
        //Initialize GolfBall
        golfBall = new Ball(WIDTH/2, HEIGHT/2);
        //Start MouseListener and Handler
        mouseHandler = new MouseHandler(golfBall);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);

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
        //Golf Ball
        super.paintComponent(g);
        golfBall.draw((Graphics2D) g);

        //Trajectory Line    
        if (mouseHandler.isDragging) {

            Graphics2D g2d = (Graphics2D) g;

            double deltaX = Math.abs(mouseHandler.pressX - mouseHandler.currentMouseX);
            double deltaY = Math.abs(mouseHandler.pressY - mouseHandler.currentMouseY);
            int colorValue = (int) Math.min(255, Math.max(0, (deltaX + deltaY) / 2));
            g2d.setColor(new Color(colorValue, 0, 255 - colorValue));

            Line2D trajectoryLine = new Line2D.Double(mouseHandler.pressX, mouseHandler.pressY, mouseHandler.currentMouseX, mouseHandler.currentMouseY);
            g2d.draw(trajectoryLine);   
        }
    }
}