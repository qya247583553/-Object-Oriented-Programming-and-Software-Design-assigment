import java.io.IOException;

public class TrigramShow {
    public MyHashTable hashTable;
    public String filePath;

    public TrigramShow(String filePath, int tableSize) {
        this.filePath = filePath;
        this.hashTable = new MyHashTable(tableSize);
    }

    public void processFile() throws IOException {
        String content = ReadFile.readFile(filePath);
        String[] words = content.split("\\s+"); //cutTheWord

        // store trigrams
        for (int i = 0; i < words.length - 2; i++) {
            String trigram = words[i] + " " + words[i + 1] + " " + words[i + 2];
            hashTable.insert(trigram);
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
