package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Hole {
    private double x;  // Position of the hole
    private double y;  // Added y position
    private static final double radius = 13; // Changed to constant, only one instance

    /**
     * Constructs a Hole at the specified position.
     *
     * @param x the x-coordinate of the hole
     * @param y the y-coordinate of the hole
     */
    public Hole(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if the ball is in the hole.
     *
     * @param ball the Ball to check
     * @return true if the ball is in the hole, false otherwise
     */
    public boolean isBallInHole(Ball ball) {
        double ballX = ball.getX();
        double ballY = ball.getY();
        double ballRadius = ball.getRadius();

        double distance = Math.sqrt(Math.pow(ballX - x, 2) + Math.pow(ballY - y, 2));
        return distance <= (radius + ballRadius);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;  // Return radius as an integer
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fill(new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2));
    }
}
