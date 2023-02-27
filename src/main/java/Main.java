import Graphic.VideoGenerator;
import Logic.Rule.HexRule;
import Logic.Rule.SquareRule;
import Logic.RuleGenerator.HexRuleGenerator;
import Logic.RuleGenerator.SquareRulesGenerator;
import Threads.ImageParallelWorker;
import Utils.Settings;
import Windows.GridFrame;

public class Main {
    public static void main(String[] args) {
        // generates explanation of HexAnt
        VideoGenerator videoGenerator = new VideoGenerator();
        videoGenerator.generateHexAntExplanation();

        // generates explanation of HexAnt
//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateSquareAntExplanation();

//        // Runs one rule based on setting realtime
//        new GridFrame();

//        Settings.generateHexagonalGridSettingsImages();
//        ImageParallelWorker imageParallelWorker = new ImageParallelWorker();
//        SquareRulesGenerator squareRulesGenerator = new SquareRulesGenerator(Settings.RULES_LENGTH);
//        HexRuleGenerator hexRuleGenerator = new HexRuleGenerator(Settings.RULES_LENGTH);
//        imageParallelWorker.drawAllRulesInParallel(hexRuleGenerator);

//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateInterestingHex(HexRule.getInteresting());
    }
}
