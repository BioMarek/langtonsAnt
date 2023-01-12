package Graphic;

import Graphic.Visualization.AntExplanation;
import Graphic.Visualization.AntGraphicFour;
import Graphic.Visualization.AntGraphicSingle;
import Logic.SquareAnt;
import Utils.SquareRule;
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
     * @param interesting list of {@link SquareRule} for which we want to create videos
     */
    public void generateInteresting(List<SquareRule> interesting) {
        for (SquareRule squareRule : interesting) {
            System.out.println("working on " + squareRule.rule);
            squareRule.setVariables();
            SquareAnt squareAnt = new SquareAnt(Settings.SquareRULE);
            squareAnt.allMoves(); // calculates number of moves in total

            Settings.SKIP = squareAnt.steps / Settings.VIDEO_NUM_IMAGES;
            System.out.println("max steps: " + squareAnt.steps + " skip: " + Settings.SKIP);

            squareAnt = new SquareAnt(Settings.SquareRULE);
            antVisualization = new AntGraphicSingle(squareAnt);
            createMP4();
        }
    }

    public void generateInteresting(Set<List<SquareRule>> interesting) {
        for (List<SquareRule> squareRules : interesting) {
            squareRules.get(0).setVariables();
            SquareAnt squareAnt = new SquareAnt(squareRules.get(0));
            squareAnt.allMoves(); // calculates number of moves in total
            int ant0 = squareAnt.steps / Settings.VIDEO_NUM_IMAGES;

            squareAnt = new SquareAnt(squareRules.get(1));
            squareAnt.allMoves();
            int ant1 = squareAnt.steps / Settings.VIDEO_NUM_IMAGES;

            squareAnt = new SquareAnt(squareRules.get(2));
            squareAnt.allMoves();
            int ant2 = squareAnt.steps / Settings.VIDEO_NUM_IMAGES;

            squareAnt = new SquareAnt(squareRules.get(3));
            squareAnt.allMoves();
            int ant3 = squareAnt.steps / Settings.VIDEO_NUM_IMAGES;

            Settings.SKIP = Math.max(Math.max(ant0, ant1), Math.max(ant2, ant3));

            System.out.println("max steps: " + squareAnt.steps + " skip: " + Settings.SKIP);

            antVisualization = new AntGraphicFour(new SquareAnt(squareRules.get(0)), new SquareAnt(squareRules.get(1)), new SquareAnt(squareRules.get(2)), new SquareAnt(squareRules.get(3)));
            createMP4();
        }
    }

    public void generateExplanation() {
        Settings.showExplanationFirstPartSettings();
        SquareAnt squareAnt = new SquareAnt(Settings.SquareRULE);
        antVisualization = new AntExplanation(squareAnt);
        createMP4();
    }

    private void createMP4() {
        ImageIterator imageIterator = new ImageIterator(antVisualization);
        try {
            SequenceEncoder encoder = new SequenceEncoder(NIOUtils.writableChannel(new File(Settings.VIDEO_BASE_PATH + Settings.SquareRULE + ".mp4")),
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
        try (FileOutputStream outputStream = new FileOutputStream(Settings.VIDEO_BASE_PATH + Settings.SquareRULE + ".gif")) {
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
        new File("gifs/" + Settings.SquareRULE).mkdirs();
        List<BufferedImage> bufferedImages = createImages();

        for (int i = 0; i < bufferedImages.size(); i++) {
            try {
                ImageIO.write(bufferedImages.get(i), "png", new File("gifs/" + Settings.SquareRULE + "/" + String.format("%03d", i) + "_" + Settings.SquareRULE + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
