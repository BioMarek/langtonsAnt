package Graphic;

import java.awt.Graphics2D;

public interface AntVisualization {
    void drawPresentation(Graphics2D graphics);

    void createNextFrame();

    boolean stopped();
}
