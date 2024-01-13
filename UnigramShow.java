import java.io.IOException;

public class UnigramShow {
    public MyHashTable hashTable;
    public String filePath;

    public UnigramShow(String filePath,int tableSize) {
        this.filePath = filePath;
        this.hashTable = new MyHashTable(tableSize); // Assuming a default size for the table
    }

    public void processFile() throws IOException {
        String content = ReadFile.readFile(filePath);
        String[] words = content.split("\\s+"); // Splitting into words

        // Identifying and storing unigrams
        for (String word : words) {
            hashTable.insert(word);
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
