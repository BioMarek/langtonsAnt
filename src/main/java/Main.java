import Logic.ImageGenerator;
import Threads.AntParallelWorker;
import Utils.Settings;

public class Main {
    public static void main(String[] args) {
//        new GridFrame();  // Runs one rule based on setting realtime

//        ImageGenerator imageGenerator = new ImageGenerator();
//        imageGenerator.drawAllRules();

        AntParallelWorker antParallelWorker = new AntParallelWorker();
        antParallelWorker.drawAllRulesInParallel();
    }
}
