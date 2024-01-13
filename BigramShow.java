import java.io.IOException;

public class BigramShow {
    public MyHashTable hashTable;
    public String filePath;

    public BigramShow(String filePath, int tableSize) {
        this.filePath = filePath;
        this.hashTable = new MyHashTable(tableSize);
    }

    public void processFile() throws IOException {
        String content = ReadFile.readFile(filePath);
        String[] words = content.split("\\s+"); // cutTheWord

        // Identify and store bigrams
        for (int i = 0; i < words.length - 1; i++) {
            String bigram = words[i] + " " + words[i + 1];
            hashTable.insert(bigram);
        }
    }

    public void displayHashTable() {
        for (int i = 0; i < hashTable.size; i++) {
            MyLinkedObject slot = hashTable.table[i];
            System.out.print("Slot " + i + ": ");
            while (slot != null) {
                System.out.print("[" + slot.word + " -> " + slot.count + "] ");
                slot = slot.next;
            }
            System.out.println();
        }
    }
}
