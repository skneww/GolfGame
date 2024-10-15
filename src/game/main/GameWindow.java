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

    public GameWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GREEN);
        
        window = new JFrame("Golf Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        initGame();

        startGame();
    }

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

    public void changeState(State newState) {
        if (currentState != null) {
            removeMouseListener(currentState);
            removeMouseMotionListener(currentState);
        }
        currentState = newState;
        if (currentState != null) {
            addMouseListener(currentState);
            addMouseMotionListener(currentState);
        }
    }

    private synchronized void startGame() {
        if (running) {
            return;
        }
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

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
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }
        stopGame();
    }

    private void updateGame() {
        if (currentState != null) {
            currentState.update();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentState != null) {
            currentState.render((Graphics2D) g);
        }
    }
}
