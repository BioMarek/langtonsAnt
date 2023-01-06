package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Graphic.Components.Legend;
import Logic.Ant;
import Utils.Colors;
import Utils.Settings;

import java.awt.*;

public class AntHexagonalSingle implements AntVisualization {
    private Graphics2D graphics;
    private final Ant ant;
    private final Legend legend;
    private final Background background;
    private int imageCount = 0;

    public AntHexagonalSingle(Ant ant) {
        this.ant = ant;
        this.legend = new Legend();
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
        legend.graphics = graphics;
        background.graphics = graphics;
        background.setBackground(true);
        legend.drawLegend(ant);
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
        int borderPadding = Settings.IMAGE_PADDING / Settings.SIZE_OF_SQUARE;
        for (int row = 0; row < ant.gridRows - borderPadding; row++) {
            for (int column = 0; column < ant.gridColumns - borderPadding; column++) {
                if (ant.grid[row + borderPadding / 2][column + borderPadding / 2] == -1)
                    continue;
                Colors.setColor(graphics, ant.grid[row + borderPadding / 2][column + borderPadding / 2]);
                drawHexagon(column + 100, row + 100, 10);
            }
        }
    }

    public void drawHexagon(int column, int row, int size){
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (column + size * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (row + size * Math.sin(i * 2 * Math.PI / 6)));
        graphics.fillPolygon(p);
    }
}
