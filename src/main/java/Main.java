import Logic.VideoGenerator;
import Utils.Util;

public class Main {
    public static void main(String[] args) {
//        new GridFrame();  // Runs one rule based on setting realtime

//        ImageGenerator imageGenerator = new ImageGenerator();
//        imageGenerator.drawAllRules();

//        ImageParallelWorker imageParallelWorker = new ImageParallelWorker();
//        imageParallelWorker.drawAllRulesInParallel();

        VideoGenerator videoGenerator = new VideoGenerator();
        videoGenerator.generateInteresting(Util.getInteresting());
    }
}
