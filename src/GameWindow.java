import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.awt.geom.Line2D;

public class GameWindow extends JPanel implements Runnable, MouseListener {
    // Window Size
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    // Game Loop Variables
    private Thread gameThread;
    private boolean running = false;
    private boolean ballPlaced = false;
    private List<Level> levels;
    private int currentLevelIndex;
    private Ball golfBall;
    private Hole hole;
    MouseHandler mouseHandler;

    public GameWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
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
        final double FPS = 60.0;
        final double TargetTime = 1e9 / FPS;

        long startTime = System.nanoTime();
        double elapsed = 0;

        while (running) {
            long currentTime = System.nanoTime();
            elapsed += (currentTime - startTime) / TargetTime;
            startTime = currentTime;

            if (elapsed > 1) {
                processInput();
                updateGame();
                renderGame();
                elapsed--;
            }
        }
    }

    private void processInput() {
        // Handle inputs here (e.g., mouse clicks)
    }

    private void updateGame() {
        if (golfBall != null) {
            golfBall.update();
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
        level1Obstacles.add(new Obstacle(300, 300, 50, 50));
        level1Obstacles.add(new Obstacle(600, 400, 30, 60));
        levels.add(new Level(1, new Ball(50, 550), level1Obstacles));
        
        //Levelul 2
        List<Obstacle> level2Obstacles = new ArrayList<>();
        level2Obstacles.add(new Obstacle(300, 300, 50, 50, true));
        level2Obstacles.add(new Obstacle(600, 400, 30, 60, true));
        levels.add(new Level(2, new Ball(100, 550), level2Obstacles));
        
    }

    private void initializeBall() {
        // Use the ball from the current level
        Level currentLevel = levels.get(currentLevelIndex);
        golfBall = currentLevel.getStartBall();
        golfBall.setVelocity(100, 0);  // Customize velocity for each level if needed
    }

    private void initializeHole() {
        // Initialize the hole for the current level
        hole = new Hole(currentLevelIndex + 1);  // Update the hole position based on the level
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        if (!ballPlaced) {
            golfBall = new Ball(mouseX, mouseY);
            ballPlaced = true;
        } else {
            golfBall.setXY(mouseX, mouseY);
        }
    }

    // Implementing mouse events but leaving them empty for now
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    // Move to the next level
    private void goToNextLevel() {
        currentLevelIndex++;
        if (currentLevelIndex < levels.size()) {
            initializeBall();  // Reset the ball for the new level
            initializeHole();  // Update hole position for the new level
        } else {
            System.out.println("Congratulations! You've completed all levels.");
            running = false;  // End the game
        }
    }
    private void checkCollisions() {
        for (Obstacle obstacle : levels.get(currentLevelIndex).getObstacles()) {
            if (golfBall.getBounds().intersects(obstacle.getBounds())) {
                // Simple collision response: stop the ball
                golfBall.setVelocity(0, 0); // Assuming this stops the ball
                break; // Exit after the first collision
            }
        }
    }
}
