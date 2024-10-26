package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import gamestate.*;

public class GameWindow extends JPanel implements Runnable {

    // Window Size
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    
    // Game Loop Variables
    private Thread gameThread;
    private boolean running = false;

    private State currentState;

    private JFrame window;

    /**
     * Constructs a new GameWindow, setting up the JFrame and starting the game.
     * Initializes the window size, background color, and other initial settings.
     */
    public GameWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GREEN);
        
        window = new JFrame("Golf Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        initGame();

        startGame();
    }

    /**
     * Initializes game components and settings, including input focus, double buffering,
     * the initial game state, and window resizing behavior.
     */
    private void initGame() {
        setFocusable(true);
        requestFocusInWindow();
        setDoubleBuffered(true);
        changeState(new MenuState(this));

        window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                WIDTH = getWidth();
                HEIGHT = getHeight();
            }
        });
    }

    /**
     * Changes the current game state to the specified new state.
     * Removes listeners and components associated with the previous state, adds listeners for the new state,
     * initializes the new state, and refreshes the display.
     *
     * @param newState the new State to switch to
     */
    public void changeState(State newState) {
        if (currentState != null) {
            removeMouseListener(currentState);
            removeMouseMotionListener(currentState);
            removeAll();
        }
        currentState = newState;
        if (currentState != null) {
            addMouseListener(currentState);
            addMouseMotionListener(currentState);
            currentState.init();
        }
        revalidate();
        repaint();
    }

    /**
     * Starts the game loop by creating and starting a new thread if it's not already running.
     * Ensures that the game loop runs in a separate thread to allow for smooth gameplay.
     */
    private synchronized void startGame() {
        if (running) {
            return;
        }
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    /**
     * Stops the game loop if it is currently running.
     * Attempts to join the game thread to ensure it has fully stopped.
     */
    private synchronized void stopGame() {
        if (!running) {
            return;
        }
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main game loop, responsible for updating game logic and rendering at a consistent frame rate.
     * Manages the timing to maintain the target frames per second (FPS).
     */
    @Override
    public void run() {
        final int FPS = 60;
        final long TARGET_TIME = 1000 / FPS;

        long lastTime = System.currentTimeMillis();

        while (running) {
            long startTime = System.currentTimeMillis();
            long elapsedTime = startTime - lastTime;
            if (elapsedTime >= TARGET_TIME) {
                updateGame();
                repaint();
                lastTime = startTime;
            } else {
                try {
                    Thread.sleep(TARGET_TIME - elapsedTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        stopGame();
    }

    /**
     * Updates the game logic by invoking the update method of the current game state.
     */
    private void updateGame() {
        if (currentState != null) {
            currentState.update();
        }
    }

    /**
     * Renders the current game state to the screen using the provided Graphics context.
     * Overrides the JPanel's paintComponent method to include custom rendering.
     *
     * @param g the Graphics context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentState != null) {
            currentState.render((Graphics2D) g);
        }
    }
}
