import Threads.ImageParallelWorker;

public class Main {
    public static void main(String[] args) {
//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateExplanation();

//        new GridFrame();  // Runs one rule based on setting realtime

        ImageParallelWorker imageParallelWorker = new ImageParallelWorker();
        imageParallelWorker.drawAllRulesInParallel();

//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateInteresting(Rule.getHighways());

//        HexImageParallelWorker hexImageParallelWorker = new HexImageParallelWorker();
//        hexImageParallelWorker.drawAllRulesInParallel();
    }
}
