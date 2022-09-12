import Threads.AntThreadWorker;

import java.util.ArrayList;
import java.util.List;

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

        List<List<String>> items = new ArrayList<>();
        items.add(List.of("hello", "how", "are", "you"));
        items.add(List.of("thanks", "i", "am", "fine"));
        AntThreadWorker antThreadWorker = new AntThreadWorker(items);
        antThreadWorker.doWork();
    }
}
