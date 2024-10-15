package gamestate;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;

public abstract class State extends MouseAdapter {
    
    public abstract void init();

    public abstract void update();

    public abstract void render(Graphics2D g2d);
}
