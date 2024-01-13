//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//public class UnigramPredictor {
//    private UniGramModel unigramModel;
//    private BigramShow bigramShow;
//
//    public UnigramPredictor(String filePath, int tableSize) {
//        this.unigramModel = new UniGramModel();
//        this.bigramShow = new BigramShow(filePath, tableSize);
//    }
//
//    public void trainModel() throws IOException {
//        bigramShow.processFile();
//
//        List<String> wordsForTraining = new ArrayList<>();
//        for (MyLinkedObject slot : bigramShow.hashTable.table) {
//            while (slot != null) {
//                wordsForTraining.add(slot.word);
//                slot = slot.next;
//            }
//        }
//
//        unigramModel.train(wordsForTraining);
//    }
//
//    public List<String> generateText(String startWords, int maxLength) {
//        List<String> generatedText = new ArrayList<>(Arrays.asList(startWords.split("\\s+")));
//        while (generatedText.size() < maxLength) {
//            String nextWord = unigramModel.predictNextWord();
//            if (nextWord == null) {
//                break;
//            }
//            generatedText.add(nextWord);
//        }
//        return generatedText;
//    }
//}
//

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UnigramPredictor {
    private UniGramModel unigramModel;
    private UnigramShow unigramShow;

    public UnigramPredictor(String filePath, int tableSize) {
        this.unigramModel = new UniGramModel();
        this.unigramShow = new UnigramShow(filePath, tableSize);
    }

    public void trainModel() throws IOException {
        unigramShow.processFile();

        // Use Unigram Show's hash table to train the model
        List<String> wordsForTraining = new ArrayList<>();
        for (MyLinkedObject slot : unigramShow.hashTable.table) {
            while (slot != null) {
                wordsForTraining.add(slot.word);
                slot = slot.next;
            }
        }

        unigramModel.train(wordsForTraining);
    }

    public List<String> generateText(String startWords, int maxLength) {

        List<String> generatedText = new ArrayList<>(Arrays.asList(startWords.split("\\s+")));
        while (generatedText.size() < maxLength) {
            String nextWord = unigramModel.predictNextWord();
            if (nextWord == null) {
                break;
            }
            generatedText.add(nextWord);
        }
        return generatedText;
//
    }
}

