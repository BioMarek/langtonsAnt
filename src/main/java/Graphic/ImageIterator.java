package Graphic;

import Utils.Settings;

import java.awt.image.BufferedImage;
import java.util.Iterator;

public class ImageIterator implements Iterator<BufferedImage> {
    private final AntVisualization antVisualization;
    private BufferedImage bImg;

    public ImageIterator(AntVisualization antVisualization) {
        this.antVisualization = antVisualization;
    }

    @Override
    public boolean hasNext() {
        return !antVisualization.stopped();
    }

    @Override
    public BufferedImage next() {
        antVisualization.createNextFrame();
        return createBufferedImage();
    }

    public BufferedImage last() {
        return bImg;
    }

    private BufferedImage createBufferedImage() {
        bImg = new BufferedImage(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_RGB);
        antVisualization.drawPresentation(bImg.createGraphics());
        return bImg;
    }
}
