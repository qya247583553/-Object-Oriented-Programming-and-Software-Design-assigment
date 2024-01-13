import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileHashApp {
    private JFrame frame;

    private JTextArea textArea; // The area where the content of the text file is displayed
    private JTextArea hashResultArea;


    private JButton openFileButton; //openFileButton
    private JButton hashButton;

    private JButton showSortedWordsButton; // Button to show sorted words

    private JSplitPane splitPane;


    public FileHashApp() {
        // initializeTheWindow
        frame = new JFrame("textFileViewerWithHashTableDisplay");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);


        // theTextFileDisplaySectionOnTheLeft
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane fileScrollPane = new JScrollPane(textArea);

//            frame.setLayout(new BorderLayout()); // Use Border Layout

        // theRightHashTableShowsSections
        hashResultArea = new JTextArea();
        hashResultArea.setEditable(false);
        JScrollPane hashScrollPane = new JScrollPane(hashResultArea);
//            frame.add(hashScrollPane, BorderLayout.CENTER); // placeTheScrollingPanelInTheCenter

        // separatePanes
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fileScrollPane, hashScrollPane);
        splitPane.setDividerLocation(400);
        frame.add(splitPane, BorderLayout.CENTER);

        // openFileButton
        openFileButton = new JButton("selectAndDisplayTheTextFile");
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        String filePath = fileChooser.getSelectedFile().getPath();
                        String fileContent = readFile(filePath);
                        textArea.setText(fileContent);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "unableToOpenFile", "mistake", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        // bottomControlPanel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(openFileButton);

        // Show Sorted Words Button
        showSortedWordsButton = new JButton("Displays sorted words and the number of occurrences");
        showSortedWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSortedWords();
            }
        });
        controlPanel.add(showSortedWordsButton);

        // hashTableButton
        hashButton = new JButton("createAHashTable");
        hashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHashTable();
            }
        });

        controlPanel.add(hashButton);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        // New button to show word count statistics
        JButton showWordStatsButton = new JButton("Analysis");
        showWordStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWordStats();
            }
        });
        controlPanel.add(showWordStatsButton);


    }



    // Step 2: Implement the ActionListener Method
    private void showWordStats() {
        MyHashTable myHashTable = createHashTable();

        int totalWordCount = 0;  // totalNumberOfWords
        Set<String> uniqueWords = new HashSet<>();  //usedToStoreUniqueWords

        // The newly added variables are used to calculate the mean and standard deviation of the length of the linked list
        int totalListLength = 0;  // theSumOfTheLengthsOfAllLinkedLists
        int nonEmptyLists = 0;    // theNumberOfNonEmptyLinkedLists

        for (int i = 0; i < myHashTable.size; i++) {
            MyLinkedObject current = myHashTable.table[i];
            while (current != null) {
                uniqueWords.add(current.word);
                totalWordCount += current.count;
                current = current.next;
            }
        }

        // Traversing the hash table a second time, the mean and standard deviation of the linked list length are calculated
        for (int i = 0; i < myHashTable.size; i++) {
            MyLinkedObject current = myHashTable.table[i];
            int listLength = 0;
            while (current != null) {
                listLength++;
                current = current.next;
            }
            if (listLength > 0) {
                totalListLength += listLength;
                nonEmptyLists++;
            }
        }

        double averageListLength = nonEmptyLists == 0 ? 0 : (double) totalListLength / nonEmptyLists;

        // calculateTheStandardDeviation
        double sumOfSquaredDifferences = 0;
        for (int i = 0; i < myHashTable.size; i++) {
            MyLinkedObject current = myHashTable.table[i];
            int listLength = 0;
            while (current != null) {
                listLength++;
                current = current.next;
            }
            if (listLength > 0) {
                sumOfSquaredDifferences += Math.pow(listLength - averageListLength, 2);
            }
        }
        double standardDeviation = Math.sqrt(sumOfSquaredDifferences / nonEmptyLists);


        //createANewWindowToDisplayTheStatistics
        JFrame statsFrame = new JFrame("wordStatistics");
        statsFrame.setSize(300, 200);
        JTextArea statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setText("totalWordCount: " + totalWordCount +
                "\nuniqueWords: " + uniqueWords.size()+"\naverageListLength: " + averageListLength +
                "\nstandardDeviation: " + standardDeviation);
        statsFrame.add(new JScrollPane(statsArea));
        statsFrame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileHashApp();
            }
        });
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                line = replaceNumbersWithWords(line);
                line = removeUnwantedCharacters(line);
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    private static String replaceNumbersWithWords(String line) {
        String[] numberWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < numberWords.length; i++) {
            line = line.replaceAll("\\b" + i + "\\b", numberWords[i]);
        }
        return line;


    }
    private static String removeUnwantedCharacters(String line) {
        // Use regular expressions to remove all characters except lowercase letters, periods, and apostrophes
        return line.replaceAll("[^a-z.'\\s]", "");
    }
    public MyHashTable createHashTable() {
        MyHashTable myHashTable = new MyHashTable(400);

        String text = textArea.getText();
        text = text.toLowerCase(); // convertToLowercase
        text = replaceNumbersWithWords(text); // replaceNumbersWithWords
        text = removeUnwantedCharacters(text); // removeIllegalCharacters

        // splitTextIntoWords
        String[] words = text.split("\\s+");

        // insertTheWordIntoTheHashTable
        for (String word : words) {
            if (!word.isEmpty()) {
                myHashTable.insert(word);
            }
        }

        // updateTheHashTableDisplay
        StringBuilder hashDisplay = new StringBuilder();
        for (int i = 0; i < myHashTable.size; i++) {
            MyLinkedObject current = myHashTable.table[i];
            hashDisplay.append("Hash ").append(i).append(": ");
            while (current != null) {
                hashDisplay.append(current.word).append("(").append(current.count).append(") -> ");
                current = current.next;
            }
            hashDisplay.append("null\n");
        }
        hashResultArea.setText(hashDisplay.toString());

        return myHashTable;
    }




    private void showSortedWords() {
        MyHashTable myHashTable = new MyHashTable(400);


        // getTheTextAndProcessIt
        String text = textArea.getText();
        String[] words = text.split(" ");
        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (!word.isEmpty()) {
                myHashTable.insert(word);
            }
        }
        JFrame sortedWordsFrame = new JFrame("sortWordsAndOccurrences");
        sortedWordsFrame.setSize(400, 300);
        sortedWordsFrame.setLayout(new BorderLayout());

        // Use a TreeMap to sort words alphabetically
        TreeMap<String, Integer> sortedWords = new TreeMap<>();
        for (MyLinkedObject obj : myHashTable.table) {
            while (obj != null) {
                sortedWords.put(obj.word, obj.count);
                obj = obj.next;
            }

        }
        Object[][] tableData = new Object[sortedWords.size()][2];
        int i = 0;
        for (Map.Entry<String, Integer> entry : sortedWords.entrySet()) {
            tableData[i][0] = entry.getKey(); // Word
            tableData[i][1] = entry.getValue(); // Count
            i++;
        }


        // Create Buttons for sorting
        JButton sortAlphabeticallyButton = new JButton("Sort Alphabetically");
        JButton sortByCountButton = new JButton("Sort By Count");



        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sortAlphabeticallyButton);
        buttonPanel.add(sortByCountButton);

        sortedWordsFrame.setLayout(new BorderLayout());
        sortedWordsFrame.add(buttonPanel, BorderLayout.NORTH);
        // Column names
        String[] columns = {"Word", "Count"};
        DefaultTableModel model = new DefaultTableModel(tableData, columns);
        JTable table = new JTable(model);
        // Create JTable

        sortAlphabeticallyButton.addActionListener(e -> sortTable(table, 0, true));
        sortByCountButton.addActionListener(e -> sortTable(table, 1, false));


        JScrollPane scrollPane = new JScrollPane(table);
        sortedWordsFrame.add(scrollPane, BorderLayout.CENTER);
        sortedWordsFrame.setVisible(true);
    }

    private void sortTable(JTable table, int column, boolean isAlphabetical) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

        Comparator<?> comparator = isAlphabetical
                ? Comparator.naturalOrder()
                : Comparator.comparingInt(o -> Integer.parseInt(o.toString()));

        sorter.setComparator(column, comparator);
        table.setRowSorter(sorter);
        sorter.toggleSortOrder(column);
    }

}

