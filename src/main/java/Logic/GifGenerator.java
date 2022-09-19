package Logic;

import Utils.Settings;
import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GifGenerator {
    // TODO consolidate image creators
    public void saveImageWithoutPanel() {
        Ant ant = new Ant(Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE, Settings.MAX_MOVES, Settings.RULE);
        int count = 0;
        while (!ant.stopped) {
            System.out.println("saving image " + count++);
            ant.nextMoves();
            BufferedImage bImg = new BufferedImage(Settings.SIZE_IN_PIXELS + Settings.SIZE_IN_PIXELS / 3, Settings.SIZE_IN_PIXELS, BufferedImage.TYPE_INT_RGB);
            ant.drawPresentation(bImg.createGraphics());

            try {
                ImageIO.write(bImg, "png", new File(String.format("./gifs/temp/%03d.png", count)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createGif() {
        saveImageWithoutPanel();
        try (FileOutputStream outputStream = new FileOutputStream("./gifs/ " + Settings.RULE + ".gif")) {
            GifEncoder encoder = new GifEncoder(outputStream, Settings.GIF_WIDTH, Settings.GIF_HEIGHT, 1);
            ImageOptions options = new ImageOptions();
            options.setDelay(Settings.GIF_DELAY, TimeUnit.MILLISECONDS);

            List<File> imageFiles = getAllImageFilesFromFolder("./gifs/temp/");

            for (File imageFile : imageFiles) {
                System.out.println("working on file: " + imageFile.getName());
                encoder.addImage(convertImageToArray(imageFile), options);
            }

            encoder.finishEncoding();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<File> getAllImageFilesFromFolder(String path) {
        File directory = new File(path);
        File[] allFiles = directory.listFiles();

        if (allFiles == null || allFiles.length == 0) {
            throw new RuntimeException("No files present in the directory: " + path);
        }

        Arrays.sort(allFiles);
        return List.of(allFiles);
    }

    /**
     * Convert BufferedImage into RGB pixel array
     */
    public int[][] convertImageToArray(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        int[][] rgbArray = new int[bufferedImage.getHeight()][bufferedImage.getWidth()];
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                rgbArray[i][j] = bufferedImage.getRGB(j, i);
            }
        }
        return rgbArray;
    }
}
