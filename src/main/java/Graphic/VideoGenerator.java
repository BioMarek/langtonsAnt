package Graphic;

import Logic.Ant;
import Utils.Rule;
import Utils.Settings;
import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.common.Codec;
import org.jcodec.common.Format;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Rational;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class VideoGenerator {
    private Ant ant;
    private AntVisualization antVisualization;
    private final BufferedImage bImg;

    public VideoGenerator() {
        bImg = new BufferedImage(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Generates *.mp4 for interesting rules which are passed as argument.
     *
     * @param interesting list of {@link Rule} for which we want to create videos
     */
    public void generateInteresting(List<Rule> interesting) {
        for (Rule rule : interesting) {
            System.out.println("working on " + rule.rule);
            rule.setVariables();
            this.ant = new Ant(Settings.RULE);
            ant.allMoves(); // calculates number of moves in total

            Settings.SKIP = ant.steps / Settings.VIDEO_NUM_IMAGES;
            System.out.println("max steps: " + ant.steps + " skip: " + Settings.SKIP);

            this.ant = new Ant(Settings.RULE);
            antVisualization = new AntGraphicSingle(ant);
            createMP4();
        }
    }

    public void generateExplanation() {
        ant = new Ant(Settings.RULE);
        antVisualization = new AntExplanation(ant);
        createMP4();
    }

    private void createMP4() {
        ImageIterator imageIterator = new ImageIterator(ant, antVisualization);
        try {
            SequenceEncoder encoder = new SequenceEncoder(NIOUtils.writableChannel(new File(Settings.VIDEO_BASE_PATH + Settings.RULE + ".mp4")),
                    Rational.R(Settings.VIDEO_FPS, 1), Format.MOV, Codec.PNG, null);
            while (imageIterator.hasNext()) {
                encoder.encodeNativeFrame(AWTUtil.fromBufferedImageRGB(imageIterator.next()));
            }
            encoder.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<BufferedImage> createImages() {
        List<BufferedImage> result = new ArrayList<>();

        while (!ant.stopped) {
            antVisualization.createNextFrame();
            antVisualization.drawPresentation(bImg.createGraphics());
            result.add(bImg);
        }
        return result;
    }

    /**
     * Obsolete *.mp4 is used. Creates images of rule according to Settings and from these images creates gif.
     */
    @Deprecated
    public void createGif() {
        try (FileOutputStream outputStream = new FileOutputStream(Settings.VIDEO_BASE_PATH + Settings.RULE + ".gif")) {
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

    /**
     * Obsolete *.mp4 is used. Convert BufferedImage into RGB pixel array
     */
    @Deprecated
    private int[][] convertImageToArray(BufferedImage bufferedImage) {
        int[][] rgbArray = new int[bufferedImage.getHeight()][bufferedImage.getWidth()];
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                rgbArray[i][j] = bufferedImage.getRGB(j, i);
            }
        }
        return rgbArray;
    }

    @Deprecated
    public void saveImages() {
        new File("gifs/" + Settings.RULE).mkdirs();
        List<BufferedImage> bufferedImages = createImages();

        for (int i = 0; i < bufferedImages.size(); i++) {
            try {
                ImageIO.write(bufferedImages.get(i), "png", new File("gifs/" + Settings.RULE + "/" + String.format("%03d", i) + "_" + Settings.RULE + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
