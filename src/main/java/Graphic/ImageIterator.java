package Graphic;

import Logic.Ant;
import Utils.Settings;

import java.awt.image.BufferedImage;
import java.util.Iterator;

public class ImageIterator implements Iterator<BufferedImage> {
    private final Ant ant;
    private final AntVisualization antVisualization;

    public ImageIterator(Ant ant, AntVisualization antVisualization) {
        this.ant = ant;
        this.antVisualization = antVisualization;
    }

    @Override
    public boolean hasNext() {
        return !ant.stopped;
    }

    @Override
    public BufferedImage next() {
        antVisualization.createNextFrame();
        return createBufferedImage();
    }

    private BufferedImage createBufferedImage() {
        BufferedImage bImg = new BufferedImage(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_RGB);
        antVisualization.drawPresentation(bImg.createGraphics());
        return bImg;
    }
}
