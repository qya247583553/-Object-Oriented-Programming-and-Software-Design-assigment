import java.util.*;

public class UniGramModel {
    private Map<String, Integer> unigrams = new HashMap<>();
    private Random random = new Random();

    // Train the model by counting the frequency of each word
    public void train(List<String> words) {
        for (String word : words) {
            unigrams.put(word, unigrams.getOrDefault(word, 0) + 1);
        }
    }

    // Predict the next word based on the unigram model
    public String predictNextWord() {
        int total = unigrams.values().stream().mapToInt(Integer::intValue).sum();

        // Generate a random index to choose the next word
        int index = random.nextInt(total);
        int sum = 0;
        for (Map.Entry<String, Integer> entry : unigrams.entrySet()) {
            sum += entry.getValue();
            if (sum > index) {
                return entry.getKey();
            }
        }

        return null; // Return null if no word is found (should not happen in a well-trained model)
    }
}
