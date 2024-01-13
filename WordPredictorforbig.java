import java.util.*;

public class WordPredictorforbig {
    private BigGramModel BigGramModel;

    public WordPredictorforbig(BigGramModel BigGramModel) {
        this.BigGramModel = BigGramModel;
    }

    public List<String> predictSequence(String startWords, int maxLength) {
        List<String> result = new ArrayList<>(Arrays.asList(startWords.split("\\s+")));
        Set<String> usedBigrams = new HashSet<>();

        while (result.size() < maxLength) {
            String lastWord = result.get(result.size() - 1);
            String nextWord = BigGramModel.predictNextWord(lastWord, usedBigrams);
            if (nextWord == null) {
                break;
            }
            result.add(nextWord);
        }

        return result;
    }
}
