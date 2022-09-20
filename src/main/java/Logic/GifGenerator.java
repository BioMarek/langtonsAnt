package Logic;

import Utils.Settings;
import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GifGenerator {

    /**
     * Creates images of rule according to Settings and from these images creates gif.
     */
    public void createGif() {
        try (FileOutputStream outputStream = new FileOutputStream(Settings.GIF_BASE_PATH + Settings.RULE + ".gif")) {
            GifEncoder encoder = new GifEncoder(outputStream, Settings.GIF_WIDTH, Settings.GIF_HEIGHT, 1);
            ImageOptions options = new ImageOptions();
            options.setDelay(Settings.GIF_DELAY, TimeUnit.MILLISECONDS);

            List<BufferedImage> bufferedImages = createImages();

            for (int i = 0; i < bufferedImages.size(); i++) {
                System.out.println("rendering image " + i);
                encoder.addImage(convertImageToArray(bufferedImages.get(i)), options);
            }
            encoder.finishEncoding();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<BufferedImage> createImages() {
        Ant ant = new Ant(Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE, Settings.MAX_MOVES, Settings.RULE);
        List<BufferedImage> result = new ArrayList<>();
        int count = 0;

        while (!ant.stopped) {
            System.out.println("creating image " + count++);
            ant.nextMoves();
            BufferedImage bImg = new BufferedImage(Settings.SIZE_IN_PIXELS + Settings.SIZE_IN_PIXELS / 3, Settings.SIZE_IN_PIXELS, BufferedImage.TYPE_INT_RGB);
            ant.drawPresentation(bImg.createGraphics());
            result.add(bImg);
        }
        return result;
    }

    /**
     * Convert BufferedImage into RGB pixel array
     */
    private int[][] convertImageToArray(BufferedImage bufferedImage) {
        int[][] rgbArray = new int[bufferedImage.getHeight()][bufferedImage.getWidth()];
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                rgbArray[i][j] = bufferedImage.getRGB(j, i);
            }
        }
        return rgbArray;
    }
}
