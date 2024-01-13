import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class HashTableDemo {
    public static void main(String[] args) throws IOException {
        int hashtableSize = 100; // 哈希表大小
        LinkedList<String>[] hashTable = new LinkedList[hashtableSize]; // 创建哈希表
        for (int i = 0; i < hashtableSize; i++) {
            hashTable[i] = new LinkedList<>();
        }

        // 读取文件并使用哈希函数
        BufferedReader reader = new BufferedReader(new FileReader("news.txt"));
        String word;
        while ((word = reader.readLine()) != null) {
            MyHashFunction hashFunction = new FirstLetterHashFunction(hashtableSize, word);
            int hashValue = hashFunction.computeHash();
            hashTable[hashValue].add(word);
        }
        reader.close();

        // 打印链表长度
        for (int i = 0; i < hashtableSize; i++) {
            System.out.println("Index " + i + ": " + hashTable[i].size());
        }
    }
}
