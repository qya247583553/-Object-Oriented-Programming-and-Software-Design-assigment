import java.util.*;

public class WordPredictorforuni {
    private UniGramModel uniGramModel;

    public WordPredictorforuni(UniGramModel uniGramModel) {
        this.uniGramModel = uniGramModel;
    }

    public List<String> predictSequence(int maxLength) {
        List<String> result = new ArrayList<>();

        while (result.size() < maxLength) {
            String nextWord = uniGramModel.predictNextWord();
            if (nextWord == null) {
                break;
            }
            result.add(nextWord);
        }

        return result;
    }
}
