package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Utils.Colors;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;

import static Utils.Util.hexagonalPolygon;

public class HexExplanation implements AntVisualization {
    private static final int HEXAGON_SIZE = 60;
    private static final double rotateAngle = Math.toRadians(3);
    private Graphics2D graphics;
    private BufferedImage antImage = null;
    private final Background background;
    private int currentCycle = 0;
    private double currentAngle = Math.toRadians(90);
    private double startX = 470;
    private double startY = 500;
    private float alpha = 1f;

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
        this.graphics.setStroke(new BasicStroke(2f));
        drawSquareGrid(400, 500);
        drawHexGrid(1350, 500);

        squareExplanationGraphicSequence();
    }

    @Override
    public void createNextFrame() {
        currentCycle++;
    }

    @Override
    public boolean stopped() {
        return false;
    }

    public void squareExplanationGraphicSequence() {
        if (currentCycle < 60) {
            drawSquareAntMove(0, -7, -2);
        }
        if (currentCycle == 60) {
            alpha = 1f;
            currentAngle = Math.toRadians(90);
            startX = 470;
            startY = 500;
        }
        if (currentCycle > 60)
            drawDirectionInfo("L", 485, 470);
        if (currentCycle > 60 && currentCycle < 120) {
            drawSquareAntMove(60, 7, 2);
        }
        if (currentCycle == 120) {
            alpha = 1f;
        }
        if (currentCycle > 120) {
            drawDirectionInfo("R", 485, 670);
        }
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
        graphics.drawPolygon(hexagonalPolygon(column, row, size));
    }

    public Point getPoint(int i, int column, int row, int size) {
        Point point = new Point();
        point.x = (int) (column + size * Math.sin(i * 2 * Math.PI / 6));
        point.y = (int) (row + size * Math.cos(i * 2 * Math.PI / 6));
        return point;
    }

    private void drawSquareAntMove(int startCycle, int YAxisShift, int rotationShift) {
        double locationX = antImage.getWidth() / 2.0;
        double locationY = antImage.getHeight() / 2.0;

        AffineTransform tx = AffineTransform.getRotateInstance(currentAngle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);

        graphics.setComposite(ac);
        graphics.drawImage(antImage, op, (int) startX, (int) startY);

        if (currentCycle <= 15 + startCycle) {
            currentAngle += (rotateAngle * rotationShift);
        }
        if (currentCycle > 15 + startCycle && currentCycle < 30 + startCycle) {
            startY += YAxisShift;
        }
        if (currentCycle > 30 + startCycle && currentCycle < 60 + startCycle) {
            alpha -= 0.05f;
            alpha = Math.max(alpha, 0);
        }
    }

    public void drawDirectionInfo(String direction, int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.drawString(getAttributedString(direction).getIterator(), x, y);
    }

    private AttributedString getAttributedString(String direction) {
        AttributedString attributedString = new AttributedString(direction);
        attributedString.addAttribute(TextAttribute.SIZE, 50);
        if (direction.length() > 1)
            attributedString.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 1, 2);
        return attributedString;
    }
}
