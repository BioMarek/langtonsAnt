package Graphic;

import Graphic.Visualization.AntExplanation;
import Graphic.Visualization.AntGraphicFour;
import Graphic.Visualization.AntGraphicSingle;
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
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class VideoGenerator {
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
            Ant ant = new Ant(Settings.RULE);
            ant.allMoves(); // calculates number of moves in total

            Settings.SKIP = ant.steps / Settings.VIDEO_NUM_IMAGES;
            System.out.println("max steps: " + ant.steps + " skip: " + Settings.SKIP);

            ant = new Ant(Settings.RULE);
            antVisualization = new AntGraphicSingle(ant);
            createMP4();
        }
    }

    public void generateInteresting(Set<List<Rule>> interesting) {
        for (List<Rule> rules : interesting) {
            rules.get(0).setVariables();
            Ant ant = new Ant(rules.get(0).rule);
            ant.allMoves(); // calculates number of moves in total
            int ant0 = ant.steps / Settings.VIDEO_NUM_IMAGES;

            ant = new Ant(rules.get(1).rule);
            ant.allMoves();
            int ant1 = ant.steps / Settings.VIDEO_NUM_IMAGES;

            ant = new Ant(rules.get(2).rule);
            ant.allMoves();
            int ant2 = ant.steps / Settings.VIDEO_NUM_IMAGES;

            ant = new Ant(rules.get(3).rule);
            ant.allMoves();
            int ant3 = ant.steps / Settings.VIDEO_NUM_IMAGES;

            Settings.SKIP = Math.max(Math.max(ant0, ant1), Math.max(ant2, ant3));

            System.out.println("max steps: " + ant.steps + " skip: " + Settings.SKIP);

            Settings.RULE = rules.get(0).rule + "_" + rules.get(1).rule + "_" + rules.get(2).rule + "_" + rules.get(3).rule;
            antVisualization = new AntGraphicFour(new Ant(rules.get(0).rule), new Ant(rules.get(1).rule), new Ant(rules.get(2).rule), new Ant(rules.get(3).rule));
            createMP4();
        }
    }

    public void generateExplanation() {
        Settings.showExplanationFirstPartSettings();
        Ant ant = new Ant(Settings.RULE);
        antVisualization = new AntExplanation(ant);
        createMP4();
    }

    private void createMP4() {
        ImageIterator imageIterator = new ImageIterator(antVisualization);
        try {
            SequenceEncoder encoder = new SequenceEncoder(NIOUtils.writableChannel(new File(Settings.VIDEO_BASE_PATH + Settings.RULE + ".mp4")),
                    Rational.R(Settings.VIDEO_FPS, 1), Format.MOV, Codec.PNG, null);
            while (imageIterator.hasNext()) {
                encoder.encodeNativeFrame(AWTUtil.fromBufferedImageRGB(imageIterator.next()));
            }
            for (int i = 0; i < Settings.VIDEO_REPEAT_LAST_FRAME; i++) {
                encoder.encodeNativeFrame(AWTUtil.fromBufferedImageRGB(imageIterator.last()));
            }
            encoder.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<BufferedImage> createImages() {
        List<BufferedImage> result = new ArrayList<>();

        while (!antVisualization.stopped()) {
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
            GifEncoder encoder = new GifEncoder(outputStream, 1413, 1080, 1);
            ImageOptions options = new ImageOptions();
            options.setDelay(35, TimeUnit.MILLISECONDS);

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
