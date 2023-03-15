package Graphic;

import Graphic.Visualization.AntExplanation;
import Graphic.Visualization.AntGraphicFour;
import Graphic.Visualization.AntGraphicSingle;
import Graphic.Visualization.HexExplanation;
import Graphic.Visualization.HexGraphicSingle;
import Logic.Ant.HexAnt;
import Logic.Ant.SquareAnt;
import Logic.Rule.HexRule;
import Logic.Rule.Rule;
import Logic.Rule.SquareRule;
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
    private String ruleName;

    public VideoGenerator() {
        bImg = new BufferedImage(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Generates *.mp4 for interesting {@link SquareRule} which are passed as argument.
     *
     * @param interesting list of {@link SquareRule} for which we want to create videos
     */
    public void generateInterestingSquare(List<SquareRule> interesting) {
        for (SquareRule squareRule : interesting) {
            System.out.println("working on " + squareRule.rule);
            SquareAnt squareAnt = new SquareAnt(Settings.SQUARE_RULE);
            squareAnt.allMoves(); // calculates number of moves in total

            Settings.SKIP = squareAnt.steps / Settings.VIDEO_NUM_IMAGES;
            System.out.println("max steps: " + squareAnt.steps + " skip: " + Settings.SKIP);

            squareAnt = new SquareAnt(Settings.SQUARE_RULE);
            antVisualization = new AntGraphicSingle();
            ((AntGraphicSingle) antVisualization).squareAnt = squareAnt;
            createMP4();
        }
    }

    /**
     * Generates *.mp4 for interesting {@link HexRule}s which are passed as argument.
     *
     * @param interesting {@link Rule} from which videos will be encoded
     */
    public void generateInterestingHex(List<HexRule> interesting) {
        for (HexRule hexRule : interesting) {
            System.out.println("working on " + hexRule.rule);

            HexAnt hexAnt = new HexAnt(hexRule);
            hexRule.setShifts(hexAnt);
            hexAnt.allMoves(); // calculates number of moves in total

            Settings.SKIP = hexAnt.steps / Settings.VIDEO_NUM_IMAGES;
            System.out.println("max steps: " + hexAnt.steps + " skip: " + Settings.SKIP);

            hexAnt = new HexAnt(hexRule);
            hexRule.setShifts(hexAnt);
            antVisualization = new HexGraphicSingle();
            ((HexGraphicSingle) antVisualization).ant = hexAnt;
            ruleName = hexAnt.rule.toString();
            createMP4();
        }
    }

    /**
     * Generates *.mp4 for interesting {@link SquareRule}s containing four ants which are passed as argument.
     *
     * @param interesting {@link Rule} from which videos will be encoded
     */
    public void generateInteresting(Set<List<SquareRule>> interesting) {
        for (List<SquareRule> squareRules : interesting) {
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

    /**
     * Generates animation of explanation for {@link SquareAnt}.
     */
    public void generateSquareAntExplanation() {
        Settings.showExplanationFirstPartSettings();
        SquareAnt squareAnt = new SquareAnt(Settings.SQUARE_RULE);
        antVisualization = new AntExplanation(squareAnt);
        createMP4();
    }

    /**
     * Generates animation of explanation for {@link HexAnt}.
     */
    public void generateHexAntExplanation() {
        antVisualization = new HexExplanation();
        createMP4();
    }

    /**
     * Generates *.mp4 from current {@link AntVisualization}.
     */
    private void createMP4() {
        ImageIterator imageIterator = new ImageIterator(antVisualization);
        try {
            SequenceEncoder encoder = new SequenceEncoder(NIOUtils.writableChannel(new File(Settings.VIDEO_BASE_PATH + ruleName + ".mp4")),
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

    /**
     * Generates single frames of video used for gifs or debugging.
     *
     * @return list of frames as {@link BufferedImage}s
     */
    private List<BufferedImage> createFrames() {
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
        try (FileOutputStream outputStream = new FileOutputStream(Settings.VIDEO_BASE_PATH + ruleName + ".gif")) {
            GifEncoder encoder = new GifEncoder(outputStream, 1413, 1080, 1);
            ImageOptions options = new ImageOptions();
            options.setDelay(35, TimeUnit.MILLISECONDS);

            List<BufferedImage> bufferedImages = createFrames();

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
        new File("gifs/" + Settings.SQUARE_RULE).mkdirs();
        List<BufferedImage> bufferedImages = createFrames();

        for (int i = 0; i < bufferedImages.size(); i++) {
            try {
                ImageIO.write(bufferedImages.get(i), "png", new File("movies/" + Settings.SQUARE_RULE + "/" + String.format("%03d", i) + "_" + Settings.SQUARE_RULE + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
