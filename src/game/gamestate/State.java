package gamestate;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;

public abstract class State extends MouseAdapter {
    /**
    * Initializes the state. Called when the state becomes active.
    */
    public abstract void init();

    /**
    * Updates the game logic specific to the state.
    */
    public abstract void update();

    /**
    * Renders the state onto the screen.
    *
    * @param g2d the Graphics2D context to draw on
    */
    public abstract void render(Graphics2D g2d);
}
