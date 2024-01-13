public class HashTableStatistics {
    private MyHashTable hashTable;

    public HashTableStatistics(MyHashTable hashTable) {
        this.hashTable = hashTable;
    }

    public int getTotalWordCount() {
        int total = 0;
        for (MyLinkedObject slot : hashTable.table) {
            while (slot != null) {
                total += slot.count;
                slot = slot.next;
            }
        }
        return total;
    }

    public int getUniqueWordCount() {
        int uniqueCount = 0;
        for (MyLinkedObject slot : hashTable.table) {
            while (slot != null) {
                uniqueCount++; // eachNonEmptyNodeIsCounted
                slot = slot.next;
            }
        }
        return uniqueCount;
    }

    public double getAverageListLength() {
        double totalLength = 0;
        for (MyLinkedObject slot : hashTable.table) {
            while (slot != null) {
                totalLength++;
                slot = slot.next;
            }
        }
        return totalLength / hashTable.size;
    }

    public double getStandardDeviation() {
        double average = getAverageListLength();
        double sum = 0;
        for (MyLinkedObject slot : hashTable.table) {
            int length = 0;
            while (slot != null) {
                length++;
                slot = slot.next;
            }
            sum += Math.pow(length - average, 2);
        }
        return Math.sqrt(sum / hashTable.size);
    }
}

