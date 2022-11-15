import Graphic.VideoGenerator;
import Utils.Rule;
import Windows.GridFrame;

public class Main {
    public static void main(String[] args) {
//        Settings.showExplanationSecondPartSettings();
//        new GridFrame();  // Runs one rule based on setting realtime

//        ImageGenerator imageGenerator = new ImageGenerator();
//        imageGenerator.drawAllRules();

//        ImageParallelWorker imageParallelWorker = new ImageParallelWorker();
//        imageParallelWorker.drawAllRulesInParallel();

        VideoGenerator videoGenerator = new VideoGenerator();
        videoGenerator.generateInteresting(Rule.getDifferentTestRules());

//        videoGenerator.createMP4();
    }
}
