import java.util.*;

public class BigGramModel {
    private Map<String, Map<String, Integer>> biggrams = new HashMap<>();

    private Random random = new Random();


        public void train(List<String> words) {
        String previousWord = null;
        for (String word : words) {
            if (previousWord != null) {
                biggrams.putIfAbsent(previousWord, new HashMap<>());
                Map<String, Integer> counts = biggrams.get(previousWord);
                counts.put(word, counts.getOrDefault(word, 0) + 1);
            }
            previousWord = word;
        }
    }


        public String predictNextWord(String word, Set<String> usedBigrams) {
        if (!biggrams.containsKey(word)) {
            return null;
        }

        Map<String, Integer> candidates = biggrams.get(word);
        int total = candidates.values().stream().mapToInt(Integer::intValue).sum();

        // Generate a random index used to select the next word
        int index = random.nextInt(total);
        int sum = 0;
        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            sum += entry.getValue();
            if (sum > index) {
                String nextWord = entry.getKey();
                String bigram = word + " " + nextWord;

                // Check whether this bigram has been used before, if so, skip it
                if (!usedBigrams.contains(bigram)) {
                    usedBigrams.add(bigram);
                    return nextWord;
                }
            }
        }

        return null;
    }
}


