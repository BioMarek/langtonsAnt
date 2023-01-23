package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Graphic.Components.HexLegend;
import Logic.Ant.Ant;
import Utils.Colors;
import Utils.Settings;
import Utils.Util;

import java.awt.Graphics2D;
import java.awt.Polygon;

public class HexGraphicSingle implements AntVisualization {
    private Graphics2D graphics;
    public Ant ant;
    private final HexLegend hexLegend;
    private final Background background;
    private int imageCount = 0;
    private final int hexWidth;
    private final int hexHeight;
    private final int hexPositionShift;

    public HexGraphicSingle() {
        this.hexLegend = new HexLegend();
        this.background = new Background();

        hexWidth = Util.getHexWidth();
        hexHeight = Util.getHexHeight();
        hexPositionShift = Util.getHexPositionShift();
    }

    /**
     * Draws simple image of end result that can be saved as file.
     */
    public void drawImage(Graphics2D graphics) {
        this.graphics = graphics;
        background.graphics = graphics;
        background.setBackground(false);
        drawGrid();
    }

    @Override
    public void createNextFrame() {
        System.out.println("creating frame " + imageCount++);
        ant.nextMoves();
    }

    /**
     * Draws image that can be presented as frame of animation. Contains info on the right side of image.
     */
    @Override
    public void drawPresentation(Graphics2D graphics) {
        this.graphics = graphics;
        hexLegend.graphics = graphics;
        background.graphics = graphics;
        background.setBackground(true);
        hexLegend.drawLegend(ant);
        drawGrid();
    }

    @Override
    public boolean stopped() {
        return ant.stopped;
    }

    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void drawGrid() {
        for (int row = 0; row < ant.gridRows; row++) {
            for (int column = 0; column < ant.gridColumns; column++) {
                if (ant.grid[row][column] == -1)
                    continue;
                Colors.setColor(graphics, ant.grid[row][column]);
                if (row % 2 == 0)
                    drawHexagon(column * hexWidth, row * hexHeight, Settings.HEX_SIDE_LEN);
                else
                    drawHexagon(column * hexWidth + hexPositionShift, row * hexHeight, Settings.HEX_SIDE_LEN);
            }
        }
    }

    public void drawHexagon(int column, int row, int size) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (column + size * Math.sin(i * 2 * Math.PI / 6)),
                    (int) (row + size * Math.cos(i * 2 * Math.PI / 6)));
        graphics.fillPolygon(p);
    }
}