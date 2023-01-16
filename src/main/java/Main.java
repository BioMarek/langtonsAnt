import Graphic.VideoGenerator;
import Logic.HexRuleGenerator;
import Logic.SquareRulesGenerator;
import Threads.ImageParallelWorker;
import Utils.Settings;
import Utils.SquareRule;
import Windows.GridFrame;

public class Main {
    public static void main(String[] args) {
//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateExplanation();

//        new GridFrame();  // Runs one rule based on setting realtime

        Settings.generateHexagonalGridSettingsImages();
        ImageParallelWorker imageParallelWorker = new ImageParallelWorker();
        SquareRulesGenerator squareRulesGenerator = new SquareRulesGenerator(Settings.RULES_LENGTH);
        HexRuleGenerator hexRuleGenerator = new HexRuleGenerator(Settings.RULES_LENGTH);
        imageParallelWorker.drawAllRulesInParallel(hexRuleGenerator);

//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateInteresting(SquareRule.getHighways());
    }
}
