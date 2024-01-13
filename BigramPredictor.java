import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BigramPredictor {
    private BigGramModel bigGramModel;
    private WordPredictorforbig wordPredictor;
    private BigramShow bigramShow;

    public BigramPredictor(String filePath, int tableSize) {
        this.bigGramModel = new BigGramModel();
        this.wordPredictor = new WordPredictorforbig(bigGramModel);
        this.bigramShow = new BigramShow(filePath, tableSize);
    }

    public void trainModel() throws IOException {
        bigramShow.processFile(); // Process the file to fill the hash table with bigrams

        // Extract bigrams from the hash table
        List<String> bigramsForTraining = new ArrayList<>();
        for (MyLinkedObject slot : bigramShow.hashTable.table) {
            while (slot != null) {
                String[] words = slot.word.split("\\s+");
                if (words.length == 2) {
                    bigramsForTraining.add(words[0]);
                    bigramsForTraining.add(words[1]);
                }
                slot = slot.next;
            }
        }

        // Train the BigGramModel with the extracted bigrams
        bigGramModel.train(bigramsForTraining);
    }

    public List<String> generateText(String startWords, int maxLength) {
        return wordPredictor.predictSequence(startWords, maxLength);
    }


}
