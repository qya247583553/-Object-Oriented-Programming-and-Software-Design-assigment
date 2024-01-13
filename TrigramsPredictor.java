import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrigramsPredictor {
    private TrigramModel trigramModel; //
    private WordPredictorfortrig wordPredictor;
    private TrigramShow trigramShow;

    public TrigramsPredictor(String filePath, int tableSize) {
        this.trigramModel = new TrigramModel();
        this.wordPredictor = new WordPredictorfortrig(trigramModel);
        this.trigramShow = new TrigramShow(filePath, tableSize);
    }

    public void trainModel() throws IOException {
        trigramShow.processFile(); // Process the file to fill the hash table with trigrams

        // Extract trigrams from the hash table
        List<String> trigramsForTraining = new ArrayList<>();
        for (MyLinkedObject slot : trigramShow.hashTable.table) {
            while (slot != null) {
                String[] words = slot.word.split("\\s+");
                if (words.length == 3) {
                    trigramsForTraining.add(words[0]);
                    trigramsForTraining.add(words[1]);
                    trigramsForTraining.add(words[2]);
                }
                slot = slot.next;
            }
        }

        // Train the TrigramModel with the extracted trigrams
        trigramModel.train(trigramsForTraining);
    }



    public List<String> generateText(String startWords, int maxLength) {
        return wordPredictor.predictSequence(startWords, maxLength);
    }


}
