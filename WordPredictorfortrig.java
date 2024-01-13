import java.util.*;

public class WordPredictorfortrig {
    private TrigramModel trigramModel;

    public WordPredictorfortrig(TrigramModel trigramModel) {
        this.trigramModel = trigramModel;
    }


public List<String> predictSequence(String startWords, int maxLength) {
    // splitTheInputStringIntoAListOfWords
    List<String> result = new ArrayList<>(Arrays.asList(startWords.split("\\s+")));
    Set<String> usedTrigrams = new HashSet<>();

    // We need to preserve the size of the original word list so that the maximum length is calculated correctly
    int originalWordsCount = result.size();

    while (result.size() < maxLength + originalWordsCount) {
        int size = result.size();
        if (size < 2) {
            break;
        }
        String firstWord = result.get(size - 2);
        String secondWord = result.get(size - 1);
        String nextWord = trigramModel.predictNextWord(firstWord, secondWord, usedTrigrams);
        if (nextWord == null) {
            break;
        }
        result.add(nextWord);
    }

    return result;
}
}
