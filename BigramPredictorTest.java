import java.io.IOException;
import java.util.List;

public class BigramPredictorTest {
    public static void main(String[] args) {
        try {
            BigramPredictor predictor = new BigramPredictor("news.txt", 400);
            predictor.trainModel();
            List<String> generatedText = predictor.generateText("you have ", 20);
            System.out.println(String.join(" ", generatedText));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
