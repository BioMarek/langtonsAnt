import Logic.ImageGenerator;
import Logic.RuleProcessorTask;
import Logic.RulesGenerator;
import Utils.Settings;

public class Main {
    public static void main(String[] args) {
//        new GridFrame();

//        ImageGenerator imageGenerator = new ImageGenerator();
//        imageGenerator.drawAllRules();

//        for (int i = 2; i < 10; i++) {
//            Settings.RULES_LENGTH = i;
//            ImageGenerator imageGenerator = new ImageGenerator();
//            imageGenerator.drawAllRules();
//        }

        RuleProcessorTask.doWork();
    }
}
