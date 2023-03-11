import Graphic.VideoGenerator;
import Logic.Rule.HexRule;
import Logic.Rule.SquareRule;

public class Main {
    public static void main(String[] args) {

        /** Generates explanation for Ant on Hexagonal grid. */
//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateHexAntExplanation();


        /** Generates explanation of SquareAnt, it is from two parts. */
//        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateSquareAntExplanation();


        /** Runs one rule either square or hex based on setting realtime. Go to {@link Gui.GridPanel} and uncomment
         * correct constructor */
//        new GridFrame();


        /** Generates images of given length either all or limited number of randomly selected.*/
//        Settings.generateHexagonalGridSettingsImages();
//        ImageParallelWorker imageParallelWorker = new ImageParallelWorker();
////        SquareRulesGenerator squareRulesGenerator = new SquareRulesGenerator(Settings.RULES_LENGTH);
//        HexRuleGenerator hexRuleGenerator = new HexRuleGenerator(Settings.RULES_LENGTH);
//        imageParallelWorker.drawAllRulesInParallel(hexRuleGenerator);


        /** Generates videos of rules described in {@link HexRule} or {@link SquareRule} */
        VideoGenerator videoGenerator = new VideoGenerator();
//        videoGenerator.generateInterestingSquare(SquareRule.getInteresting());
        videoGenerator.generateInterestingHex(HexRule.getInteresting());
    }
}
