package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Utils.Colors;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HexExplanation implements AntVisualization {
    private Graphics2D graphics;
    private BufferedImage antImage = null;
    private final Background background;

    public HexExplanation() {
        this.background = new Background();

        try {
            File imageFile = new File("ant.png");
            antImage = ImageIO.read(imageFile);
        } catch (IOException ignored) {
        }
    }

    @Override
    public void drawImage(Graphics2D graphics) {
        AntVisualization.super.drawImage(graphics);
    }

    @Override
    public void drawPresentation(Graphics2D graphics) {
        this.graphics = graphics;
        background.graphics = graphics;
        background.setBackground(true);
        drawSquareGrid(400, 500);
        drawHexGrid(1400, 500);
    }

    @Override
    public void createNextFrame() {

    }

    @Override
    public boolean stopped() {
        return false;
    }

    public void drawSquareGrid(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.drawLine(x - 50, y, x + 250, y);
        graphics.drawLine(x - 50, y + 100, x + 250, y + 100);
        graphics.drawLine(x + 50, y - 100, x + 50, y + 200);
        graphics.drawLine(x + 150, y - 100, x + 150, y + 200);
    }

    public void drawHexGrid(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        drawHexagon(x, y + 50, 60);
        drawHexagon(x + 103, y + 50, 60);
        drawHexagon(x - 103, y + 50, 60);
        drawHexagon(x + 51, y + 140, 60);
        drawHexagon(x - 52, y + 140, 60);
        drawHexagon(x + 51, y - 40, 60);
        drawHexagon(x - 52, y - 40, 60);
    }

    public void drawHexagon(int column, int row, int size) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (column + size * Math.sin(i * 2 * Math.PI / 6)),
                    (int) (row + size * Math.cos(i * 2 * Math.PI / 6)));
        graphics.drawPolygon(p);
    }
}
