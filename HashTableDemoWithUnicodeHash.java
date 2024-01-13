import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HashTableDemoWithUnicodeHash {
    public static void main(String[] args) {
        int hashtableSize = 100; // setTheSizeOfTheHashTable
        String filePath = "news.txt"; //replaceWithYourFilePath
        List<String>[] hashTable = new ArrayList[hashtableSize]; // createAHashTable

        // initializeTheHashTable
        for (int i = 0; i < hashtableSize; i++) {
            hashTable[i] = new ArrayList<>();
        }

        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNext()) {
                String word = scanner.next();
                FirstLetterUnicodeHashFunction hashFunction = new FirstLetterUnicodeHashFunction(hashtableSize, word);
                int hashIndex = hashFunction.computeHash();
                hashTable[hashIndex].add(word); // addWordsToTheHashTable
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        // printTheHashTable
        printHashTable(hashTable);
    }

    // howToPrintAHashTable
    private static void printHashTable(List<String>[] hashTable) {
        // Create an ArrayList to store the chain length
        ArrayList<Integer> chainLengths = new ArrayList<>();
        for (int i = 0; i < hashTable.length; i++) {
            //Calculate the length of the linked list for each hash bucket
            int chainLength = hashTable[i].size();
            //addTheLengthOfTheLinkedListToTheList
            chainLengths.add(chainLength);
            // Print the index of each hash bucket and the corresponding linked list length
            System.out.println("Hash " + i + ": Length = " + chainLength);
            System.out.println("Hash " + i + ": " + hashTable[i]);
        }
        // printAListOfTheEntireChainLengths
        System.out.println("Chain Lengths: " + chainLengths);
    }
}
