import java.io.IOException;

public class BigramShowTest {
    public static void main(String[] args) {
        try {
            BigramShow bigramShow = new BigramShow("ss.txt", 100); // sampleFilePathAndHashTableSize
            bigramShow.processFile();

            // showsTheStructureOfAHashTable
            bigramShow.displayHashTable();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
