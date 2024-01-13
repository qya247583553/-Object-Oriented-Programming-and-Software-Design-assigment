import java.io.IOException;

public class Trigramanalys {
    public static void main(String[] args) {
        try {
            String filePath = "ss.txt";
            int tableSize = 400; //

            // useUnigramShowToWorkWithFiles
            TrigramShow trigramShow = new TrigramShow(filePath, tableSize);
            trigramShow.processFile();

            // Create a HashTableStatistics object
            HashTableStatistics stats = new HashTableStatistics(trigramShow.hashTable);

            //getStatistics
            int totalWordCount = stats.getTotalWordCount();
            int uniqueWordCount = stats.getUniqueWordCount();
            double averageListLength = stats.getAverageListLength();
            double standardDeviation = stats.getStandardDeviation();

            //displayStatistics
            System.out.println("Total Word Count: " + totalWordCount);
            System.out.println("Unique Word Count: " + uniqueWordCount);
            System.out.println("Average List Length: " + averageListLength);
            System.out.println("Standard Deviation of List Length: " + standardDeviation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String resultfortrigram() throws IOException {
        String filePath = "news.txt";
        int tableSize = 400;

        // useUnigramShowToWorkWithFiles
        TrigramShow trigramShow = new TrigramShow(filePath, tableSize);
        trigramShow.processFile();

        // Create a HashTableStatistics object
        HashTableStatistics stats = new HashTableStatistics(trigramShow.hashTable);

        // getStatistics
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
