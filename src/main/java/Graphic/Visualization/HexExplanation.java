package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Utils.Colors;

import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HexExplanation implements AntVisualization {
    private static final int HEXAGON_SIZE = 60;
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
        graphics.setStroke(new BasicStroke(2f));
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
        graphics.drawLine(x - 50, y - 100, x + 250, y - 100);
        graphics.drawLine(x - 50, y, x + 250, y);
        graphics.drawLine(x - 50, y + 100, x + 250, y + 100);
        graphics.drawLine(x - 50, y + 200, x + 250, y + 200);

        graphics.drawLine(x + 50, y - 200, x + 50, y + 300);
        graphics.drawLine(x + 150, y - 200, x + 150, y + 300);
    }

    public void drawHexGrid(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        drawHexagon(x, y + 50, HEXAGON_SIZE);
        drawHexagon(x + 103, y + 50, HEXAGON_SIZE);
        drawHexagon(x - 103, y + 50, HEXAGON_SIZE);
        drawHexagon(x + 51, y + 140, HEXAGON_SIZE);
        drawHexagon(x - 52, y + 140, HEXAGON_SIZE);
        drawHexagon(x + 51, y - 40, HEXAGON_SIZE);
        drawHexagon(x - 52, y - 40, HEXAGON_SIZE);

        // top lines
        graphics.drawLine(x + 51, y - 100, x + 51, y - 150);
        graphics.drawLine(x - 52, y - 100, x - 52, y - 150);

        // bottom lines
        graphics.drawLine(x + 51, y + 200, x + 51, y + 250);
        graphics.drawLine(x - 52, y + 200, x - 52, y + 250);

        // top right lines
        Point p = getPoint(2, x + 101, y - 70, HEXAGON_SIZE);
        graphics.drawLine(x + 101, y - 70, p.x, p.y);
        p = getPoint(2, x + 155, y + 20, HEXAGON_SIZE);
        graphics.drawLine(x + 155, y + 20, p.x, p.y);

        // bottom left lines
        p = getPoint(5, x - 155, y + 80, HEXAGON_SIZE);
        graphics.drawLine(x - 155, y + 80, p.x, p.y);
        p = getPoint(5, x - 106, y + 170, HEXAGON_SIZE);
        graphics.drawLine(x - 106, y + 170, p.x, p.y);

        // top right lines
        p = getPoint(4, x - 155, y + 20, HEXAGON_SIZE);
        graphics.drawLine(x - 155, y + 20, p.x, p.y);
        p = getPoint(4, x - 105, y - 71, HEXAGON_SIZE);
        graphics.drawLine(x - 105, y - 71, p.x, p.y);

        // bottom right lines
        p = getPoint(1, x + 155, y + 80, HEXAGON_SIZE);
        graphics.drawLine(x + 155, y + 80, p.x, p.y);
        p = getPoint(1, x + 101, y + 170, HEXAGON_SIZE);
        graphics.drawLine(x + 101, y + 170, p.x, p.y);
    }

    public void drawHexagon(int column, int row, int size) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (column + size * Math.sin(i * 2 * Math.PI / 6)),
                    (int) (row + size * Math.cos(i * 2 * Math.PI / 6)));
        graphics.drawPolygon(p);
    }

    public Point getPoint(int i, int column, int row, int size) {
        Point point = new Point();
        point.x = (int) (column + size * Math.sin(i * 2 * Math.PI / 6));
        point.y = (int) (row + size * Math.cos(i * 2 * Math.PI / 6));
        return point;
    }
}
