package Graphic;

import java.awt.Graphics2D;

public interface AntVisualization {
    default void drawImage(Graphics2D graphics){}
    void drawPresentation(Graphics2D graphics);

    void createNextFrame();

    boolean stopped();
}
