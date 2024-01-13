import java.io.IOException;

public class Unigramanalys {
        public static String resultforunigram() throws IOException {
            String filePath = "news.txt";
            int tableSize = 400;

            // UseUnigramShowToWorkWithFiles
            UnigramShow unigramShow = new UnigramShow(filePath, tableSize);
            unigramShow.processFile();

            // Create a HashTableStatistics object
            HashTableStatistics stats = new HashTableStatistics(unigramShow.hashTable);

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




