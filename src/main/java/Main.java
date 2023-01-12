import Logic.HexRuleGenerator;
import Logic.SquareRulesGenerator;
import Threads.HexImageParallelWorker;
import Utils.Settings;
//import Threads.ImageParallelWorker;

public class Main {
    public static void main(String[] args) {
//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateExplanation();

//        new GridFrame();  // Runs one rule based on setting realtime

        HexImageParallelWorker imageParallelWorker = new HexImageParallelWorker();
        SquareRulesGenerator squareRulesGenerator = new SquareRulesGenerator(Settings.RULES_LENGTH);
        HexRuleGenerator hexRuleGenerator = new HexRuleGenerator(Settings.RULES_LENGTH);
        imageParallelWorker.drawAllRulesInParallel(hexRuleGenerator);

//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateInteresting(Rule.getHighways());

//        HexImageParallelWorker hexImageParallelWorker = new HexImageParallelWorker();
//        hexImageParallelWorker.drawAllRulesInParallel();
    }
}
