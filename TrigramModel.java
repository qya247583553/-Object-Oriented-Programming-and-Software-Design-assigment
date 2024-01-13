import java.util.*;

public class TrigramModel {
    private Map<String, Map<String, Integer>> trigrams = new HashMap<>();
    private Random random = new Random();

    public void train(List<String> words) {
        for (int i = 0; i < words.size() - 2; i++) {
            String key = words.get(i) + " " + words.get(i + 1);
            String value = words.get(i + 2);

            trigrams.putIfAbsent(key, new HashMap<>());
            Map<String, Integer> counts = trigrams.get(key);
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
    }

public String predictNextWord(String firstWord, String secondWord, Set<String> usedTrigrams) {
    String key = firstWord + " " + secondWord;
    if (!trigrams.containsKey(key)) {
        return null;
    }

    Map<String, Integer> candidates = trigrams.get(key);
    int total = candidates.values().stream().mapToInt(Integer::intValue).sum();

    int index = random.nextInt(total);
    int sum = 0;
    for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
        sum += entry.getValue();
        if (sum > index) {
            String nextWord = entry.getKey();
            String trigram = key + " " + nextWord;

            if (!usedTrigrams.contains(trigram)) {
                usedTrigrams.add(trigram);
                return nextWord;
            }
        }
    }

    return null;
}
}
