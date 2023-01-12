import Logic.HexRuleGenerator;
import Logic.SquareRulesGenerator;
import Threads.ImageParallelWorker;
import Utils.Settings;

public class Main {
    public static void main(String[] args) {
//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateExplanation();

//        new GridFrame();  // Runs one rule based on setting realtime

        ImageParallelWorker imageParallelWorker = new ImageParallelWorker();
        SquareRulesGenerator squareRulesGenerator = new SquareRulesGenerator(Settings.RULES_LENGTH);
        HexRuleGenerator hexRuleGenerator = new HexRuleGenerator(Settings.RULES_LENGTH);
        imageParallelWorker.drawAllRulesInParallel(squareRulesGenerator);

//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateInteresting(Rule.getHighways());
    }
}
