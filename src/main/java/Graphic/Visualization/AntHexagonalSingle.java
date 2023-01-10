package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Graphic.Components.HexLegend;
import Logic.HexAnt;
import Utils.Colors;
import Utils.Settings;

import java.awt.Graphics2D;
import java.awt.Polygon;

public class AntHexagonalSingle implements AntVisualization {
    private Graphics2D graphics;
    private final HexAnt ant;
    private final HexLegend hexLegend;
    private final Background background;
    private int imageCount = 0;

    public AntHexagonalSingle(HexAnt ant) {
        this.ant = ant;
        this.hexLegend = new HexLegend();
        this.background = new Background();
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
        int centeringRow = 90; //    if grid is 100:   0, 200: 45, 300: 90
        int centeringColumn = 60; // if grid is 100: -40, 200: 10, 300: 80
        int hexagonWidth = (int) (Settings.HEX_SIDE_LEN * 1.7); // 17 when hexagon is 10
        int hexagonHeight = (int) (Settings.HEX_SIDE_LEN * 1.5); // 15 when hexagon is 10
        int HexagonPositionShift = hexagonWidth / 2; // 8 when hexagon is 10, odd row is shift by half of hexagon size
//        int borderPadding = Settings.IMAGE_PADDING / Settings.SIZE_OF_SQUARE;
        for (int row = 0; row < ant.gridRows; row++) {
            for (int column = 0; column < ant.gridColumns; column++) {
                if (ant.grid[row][column] == -1)
                    continue;
                Colors.setColor(graphics, ant.grid[row][column]);
                if (row % 2 == 0)
                    drawHexagon((column - centeringColumn) * hexagonWidth, (row - centeringRow) * hexagonHeight, Settings.HEX_SIDE_LEN);
                else
                    drawHexagon((column - centeringColumn) * hexagonWidth + HexagonPositionShift, (row - centeringRow) * hexagonHeight, Settings.HEX_SIDE_LEN);
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
