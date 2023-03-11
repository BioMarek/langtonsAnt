package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Utils.Colors;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;

import static Utils.Util.hexagonalPolygon;

/**
 * Used for explaining allowed directions for {@link Logic.Ant.SquareAnt} and {@link Logic.Ant.HexAnt}.
 */
public class HexExplanation implements AntVisualization {
    private static final int HEXAGON_SIZE = 60;
    private static final double ROTATE_ANGLE = Math.toRadians(3);
    private final Background background;
    private Graphics2D graphics;
    private BufferedImage antImage = null;
    private int currentCycle = 0;
    private double currentAngle = Math.toRadians(90);
    private double startX = 470;
    private double startY = 500;
    private float alpha = 1f;
    private boolean sopped = false;
    private int imageCount = 0;

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
        if (currentCycle > 150)
            drawHexGrid(1350, 500);

        squareExplanationGraphicSequence();
        hexExplanationGraphicSequence();
    }

    @Override
    public void createNextFrame() {
        System.out.println("creating frame " + imageCount++);
        if (currentCycle++ > 480)
            sopped = true;
    }

    @Override
    public boolean stopped() {
        return sopped;
    }

    private void squareExplanationGraphicSequence() {
        singleAntMove(0, 470, 500, 0, -7, -2, "L", 485, 470);
        singleAntMove(45, 470, 500, 0, 7, 2, "R", 485, 670);

        if (currentCycle > 120) {
            drawRedCross(360, 510);
            drawRedCross(560, 510);
        }
    }

    private void hexExplanationGraphicSequence() {
        singleAntMove(180, 1320, 500, 3, -6, -1.2d, "L1", 1385, 475);
        singleAntMove(225, 1320, 500, -3, -6, -2.7d, "L2", 1282, 475);
        singleAntMove(270, 1320, 500, 3, 6, 1.2d, "R1", 1385, 653);
        singleAntMove(315, 1320, 500, -3, 6, 2.7d, "R2", 1282, 653);
        singleAntMove(360, 1320, 500, 7, 0, 0d, "N", 1437, 568);
        singleAntMove(405, 1320, 500, -7, 0, 4d, "U", 1229, 568);
    }

    private void singleAntMove(int cycle, int xReset, int yReset, int xAxisShift, int yAxisShift, double rotationalShift, String letter, int letterX, int letterY) {
        antMoveReset(cycle, xReset, yReset);
        if (currentCycle > cycle && currentCycle <= cycle + 45)
            drawAntMove(cycle, xAxisShift, yAxisShift, rotationalShift);
        if (currentCycle > cycle + 45)
            drawDirectionInfo(letter, letterX, letterY);
    }

    private void antMoveReset(int cycle, int xReset, int yReset) {
        if (currentCycle == cycle + 1) {
            startX = xReset;
            startY = yReset;
            alpha = 1f;
            currentAngle = Math.toRadians(90);
        }
    }

    private void drawSquareGrid(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.drawLine(x - 50, y - 100, x + 250, y - 100);
        graphics.drawLine(x - 50, y, x + 250, y);
        graphics.drawLine(x - 50, y + 100, x + 250, y + 100);
        graphics.drawLine(x - 50, y + 200, x + 250, y + 200);

        graphics.drawLine(x + 50, y - 200, x + 50, y + 300);
        graphics.drawLine(x + 150, y - 200, x + 150, y + 300);
    }

    private void drawHexGrid(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        drawHexagon(x, y + 50);
        drawHexagon(x + 103, y + 50);
        drawHexagon(x - 103, y + 50);
        drawHexagon(x + 51, y + 140);
        drawHexagon(x - 52, y + 140);
        drawHexagon(x + 51, y - 40);
        drawHexagon(x - 52, y - 40);

        // top lines
        graphics.drawLine(x + 51, y - 100, x + 51, y - 150);
        graphics.drawLine(x - 52, y - 100, x - 52, y - 150);

        // bottom lines
        graphics.drawLine(x + 51, y + 200, x + 51, y + 250);
        graphics.drawLine(x - 52, y + 200, x - 52, y + 250);

        // top right lines
        diagonalHexagonLine(2, x + 101, y - 70);
        diagonalHexagonLine(2, x + 155, y + 20);

        // bottom left lines
        diagonalHexagonLine(5, x - 155, y + 80);
        diagonalHexagonLine(5, x - 106, y + 170);

        // top right lines
        diagonalHexagonLine(4, x - 155, y + 20);
        diagonalHexagonLine(4, x - 105, y - 71);

        // bottom right lines
        diagonalHexagonLine(1, x + 155, y + 80);
        diagonalHexagonLine(1, x + 101, y + 170);
    }

    private void drawRedCross(int x, int y) {
        graphics.setColor(Colors.RED.getColor());
        graphics.drawLine(x, y, x + 80, y + 80);
        graphics.drawLine(x, y + 80, x + 80, y);
    }

    private void diagonalHexagonLine(int hexagonVertex, int x, int y) {
        graphics.drawLine(x, y,
                (int) (x + HEXAGON_SIZE * Math.sin(hexagonVertex * 2 * Math.PI / 6)),
                (int) (y + HEXAGON_SIZE * Math.cos(hexagonVertex * 2 * Math.PI / 6)));
    }

    private void drawHexagon(int column, int row) {
        graphics.drawPolygon(hexagonalPolygon(column, row, HEXAGON_SIZE));
    }

    private void drawAntMove(int startCycle, int xAxisShift, int yAxisShift, double rotationShift) {
        drawAntMoveSetup();

        if (currentCycle <= 15 + startCycle) {
            currentAngle += (ROTATE_ANGLE * rotationShift);
        }
        if (currentCycle > 15 + startCycle && currentCycle <= 30 + startCycle) {
            startX += xAxisShift;
            startY += yAxisShift;
        }
        if (currentCycle > 30 + startCycle && currentCycle <= 45 + startCycle) {
            alpha -= 0.05f;
            alpha = Math.max(alpha, 0);
        }
    }

    private void drawAntMoveSetup() {
        AffineTransform tx = AffineTransform.getRotateInstance(currentAngle, antImage.getWidth() / 2.0, antImage.getHeight() / 2.0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);

        graphics.setComposite(ac);
        graphics.drawImage(antImage, op, (int) startX, (int) startY);
    }

    private void drawDirectionInfo(String direction, int x, int y) {
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
