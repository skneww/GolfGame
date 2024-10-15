import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.geom.Line2D;

public class GameWindow extends JPanel implements Runnable {

    // Window Size
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    // Game Loop Variables
    private Thread gameThread;
    private boolean running = false;
    private List<Level> levels;
    private int currentLevelIndex;
    private Ball golfBall;
    private Hole hole;
    private MouseHandler mouseHandler;

    public GameWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GREEN);
        setDoubleBuffered(true);
        initializeLevels();
        currentLevelIndex = 0; // Start with the first level (level 1)
        initializeBall();
        initializeHole();
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
        final int FPS = 60;
        final long TARGETTIME = 1000 / FPS;

        while (running) {
            long startTime = System.currentTimeMillis();

            updateGame();
            renderGame();
            
            long elapsedTime = System.currentTimeMillis() - startTime;
            long waitTime = TARGETTIME - elapsedTime;
            try {
                Thread.sleep(Math.max(0,waitTime));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void updateGame() {
        if (golfBall != null) {
            // Actualizează mingea și verifică coliziunea cu obstacolele
            golfBall.update(levels.get(currentLevelIndex).getObstacles());  /// am schimbat pentru a transmite lista de obstacole
        }
        if (hole != null && golfBall != null && isBallInHole(golfBall)) {
            System.out.println("Level completed! Moving to next level...");
            goToNextLevel();
        }
    }
    

    private void renderGame() {
        repaint();
    }

    private void initializeLevels() {
        levels = new ArrayList<>();
        
        //Levelul 1
        List<Obstacle> level1Obstacles = new ArrayList<>();
        level1Obstacles.add(new Obstacle(200, 100, 50, 400));
        level1Obstacles.add(new Obstacle(550, 100, 50, 400));
        level1Obstacles.add(new Obstacle(370, 200, 50, 350));
        levels.add(new Level(1, new Ball(50, 50), level1Obstacles));
        //Levelul 2
        List<Obstacle> level2Obstacles = new ArrayList<>();
        level2Obstacles.add(new Obstacle(200, 50, 200, 200, true));
        level2Obstacles.add(new Obstacle(600, 400, 30, 60, true));
        level2Obstacles.add(new Obstacle(300, 300, 50, 50, true));
        levels.add(new Level(2, new Ball(50, 300), level2Obstacles));


        //Levelul 3
        List<Obstacle> level3obstacles = new ArrayList<>();
        level3obstacles.add(new Obstacle(300, 300, 50, 400));
        Level level3 = new Level(3, new Ball(50, 550), level3obstacles);
        levels.add(level3);
        
    }

    private void initializeBall() {
        // Use the ball from the current level
        Level currentLevel = levels.get(currentLevelIndex);
        golfBall = currentLevel.getStartBall();
        golfBall.setVelocity(0, 0);
        if (mouseHandler != null) {
            mouseHandler.SetGolfBall(golfBall);
        }
    }

    private void initializeHole() {
        hole = new Hole(currentLevelIndex + 1);
    }

    public boolean isBallInHole(Ball ball) {
        int ballX = (int) ball.getX();
        int ballY = (int) ball.getY();
        int ballRadius = ball.getRadius();
    
        // Access hole's x, y, and radius
        int holeX = (int) hole.getX();
        int holeY = (int) hole.getY();
        int holeRadius = hole.getRadius();
    
        // Calculate the distance between the ball's center and the hole's center
        double distance = Math.sqrt(Math.pow(ballX - holeX, 2) + Math.pow(ballY - holeY, 2));
    
        // Check if the ball is inside the hole
        return distance <= (holeRadius + ballRadius);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the current level's obstacles
        Level currentLevel = levels.get(currentLevelIndex); // Get the current level
        for (Obstacle obstacle : currentLevel.getObstacles()) {
            obstacle.draw(g2d); // Assuming Obstacle has a draw method
        }

        // Draw the ball
        if (golfBall != null) {
            golfBall.draw(g2d);
        }

        // Draw the hole
        if (hole != null) {
            hole.draw(g2d);
        }
        if (mouseHandler.isDragging) {
            double deltaX = Math.abs(mouseHandler.pressX - mouseHandler.currentMouseX);
            double deltaY = Math.abs(mouseHandler.pressY - mouseHandler.currentMouseY);
            int colorValue = (int) Math.min(255, Math.max(0, (deltaX + deltaY) / 2));
            g2d.setColor(new Color(colorValue, 0, 255 - colorValue));

            Line2D trajectoryLine = new Line2D.Double(mouseHandler.pressX, mouseHandler.pressY, mouseHandler.currentMouseX, mouseHandler.currentMouseY);
            g2d.draw(trajectoryLine);   
        }
    }

    private void goToNextLevel() {
        currentLevelIndex++;
        if (currentLevelIndex < levels.size()) {
            initializeBall();  
            initializeHole(); 
            mouseHandler = new MouseHandler(golfBall);
             this.addMouseListener(mouseHandler);
             this.addMouseMotionListener(mouseHandler);
        } else {
            System.out.println("Congratulations! You've completed all levels.");
            running = false;  
        }
    }
}
