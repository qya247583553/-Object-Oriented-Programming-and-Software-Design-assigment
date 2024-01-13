import java.io.IOException;

public class Bigramanalys {
    public static String resultforbigram() throws IOException {
        String filePath = "news.txt";
        int tableSize = 400; // theSizeOfTheHashTable

        // Use UnigramShow to work with files
        BigramShow bigramShow = new BigramShow(filePath, tableSize);
        bigramShow.processFile();

        // Create a HashTableStatistics object
        HashTableStatistics stats = new HashTableStatistics(bigramShow.hashTable);

        // Get statistics
        int totalWordCount = stats.getTotalWordCount();
        int uniqueWordCount = stats.getUniqueWordCount();
        double averageListLength = stats.getAverageListLength();
        double standardDeviation = stats.getStandardDeviation();

        return "Total Word Count: " + totalWordCount + "\n" +
                "Unique Word Count: " + uniqueWordCount + "\n" +
                "Average List Length: " + averageListLength + "\n" +
                "Standard Deviation of List Length: " + standardDeviation;

    }
    }
