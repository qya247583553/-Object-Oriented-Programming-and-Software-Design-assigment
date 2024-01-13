import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class MyLanguageModel {
    public static void main(String[] args) {
        // createAMainWindow
        JFrame frame = new JFrame("MyLanguageeModel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Use BorderLayout
        frame.setSize(800, 600);

        // The top panel, which contains the first and second row components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton chooseFileButton = new JButton("Choose File");
        JTextField filePathField = new JTextField(20);
        JButton UnigramsTableButton = new JButton("Unigrams Hashtable");
        JButton bigramsTableButton = new JButton("Bigrams Hashtable");
        JButton trigramsTableButton = new JButton("Trigrams Hashtable");
        firstRowPanel.add(chooseFileButton);
        firstRowPanel.add(filePathField);
        firstRowPanel.add(UnigramsTableButton);
        firstRowPanel.add(bigramsTableButton);
        firstRowPanel.add(trigramsTableButton);

        JPanel secondRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField inputField = new JTextField(20);
        JComboBox<Integer> sizeComboBox = new JComboBox<>();
        for (int i = 1; i <= 20; i++) {
            sizeComboBox.addItem(i);
        }
        JButton UnigramsPredictButton = new JButton("Unigrams Predict");
        JButton bigramsPredictButton = new JButton("Bigrams Predict");
        JButton trigramsPredictButton = new JButton("Trigrams Predict");
        secondRowPanel.add(inputField);
        secondRowPanel.add(sizeComboBox);
        secondRowPanel.add(UnigramsPredictButton);
        secondRowPanel.add(bigramsPredictButton);
        secondRowPanel.add(trigramsPredictButton);

        // Add the first and second row panels to the top panel
        topPanel.add(firstRowPanel);
        topPanel.add(secondRowPanel);

        // Add the top panel to the north side of the frame (above)
        frame.add(topPanel, BorderLayout.NORTH);

        // theMiddleDisplayWindowPanel
        JPanel displayPanel = new JPanel(new GridLayout(4, 1));
        JTextArea UnigramsDisplayArea = new JTextArea();
        UnigramsDisplayArea.setEditable(false);
        JTextArea bigramsDisplayArea = new JTextArea();
        bigramsDisplayArea.setEditable(false);
        JTextArea trigramsDisplayArea = new JTextArea();
        trigramsDisplayArea.setEditable(false);
        JTextArea predictionsDisplayArea = new JTextArea();
        predictionsDisplayArea.setEditable(false);
        displayPanel.add(new JScrollPane(UnigramsDisplayArea));
        displayPanel.add(new JScrollPane(bigramsDisplayArea));
        displayPanel.add(new JScrollPane(trigramsDisplayArea));
        displayPanel.add(new JScrollPane(predictionsDisplayArea));

        //Add the Showcase panel to the middle area of the frame (below)
        frame.add(displayPanel, BorderLayout.CENTER);

        // analyzeButtonAtTheBottom
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton analyseButton = new JButton("analysis");
        bottomPanel.add(analyseButton);

        // Add the bottom analysis button to the south side of the frame (bottom)
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // displayTheWindow
        frame.setVisible(true);


        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    filePathField.setText(file.getAbsolutePath());
                }
            }
        });
        UnigramsTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathField.getText();
                if (!filePath.isEmpty()) {
                    try {
                        // createAnInstanceOfUnigramShow
                        UnigramShow unigramShow = new UnigramShow(filePath, 400); // letSAssumeThatTheHashTableSizeIs400
                        unigramShow.processFile();

                        // Redirect the System.out output to ByteArrayOutputStream
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        PrintStream ps = new PrintStream(baos);
                        PrintStream old = System.out;
                        System.setOut(ps);

                        // displaysAHashTable
                        unigramShow.displayHashTable();

                        // Restore System.out output and set the text to the interface
                        System.out.flush();
                        System.setOut(old);

                        UnigramsDisplayArea.setText(baos.toString());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error processing file: " + ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a file first.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        bigramsTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathField.getText();
                if (!filePath.isEmpty()) {
                    try {
                        // Assuming the table size is predefined, for example, 100
                        BigramShow bigramShow = new BigramShow(filePath, 400);
                        bigramShow.processFile();

                        // Now display the bigrams in bigramsDisplayArea
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        PrintStream ps = new PrintStream(baos);
                        PrintStream old = System.out;
                        System.setOut(ps);

                        bigramShow.displayHashTable();

                        System.out.flush();
                        System.setOut(old);

                        bigramsDisplayArea.setText(baos.toString());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error reading file: " + ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a file first.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        trigramsTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathField.getText();
                if (!filePath.isEmpty()) {
                    try {
                        // Assuming the table size is predefined, for example, 400
                        TrigramShow trigramShow = new TrigramShow(filePath, 400);
                        trigramShow.processFile();

                        // Redirect the output from System.out to the trigramsDisplayArea
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        PrintStream ps = new PrintStream(baos);
                        PrintStream old = System.out;
                        System.setOut(ps);

                        trigramShow.displayHashTable();

                        System.out.flush();
                        System.setOut(old);

                        trigramsDisplayArea.setText(baos.toString());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error reading file: " + ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a file first.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        UnigramsPredictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startWords = inputField.getText();
                int maxLength = (Integer) sizeComboBox.getSelectedItem();

                if (!startWords.isEmpty() && maxLength > 0) {
                    try {

                        // Initialize BigramPredictor with the selected file path and a predefined table size
                        String filePath = filePathField.getText();
                        UnigramPredictor UnigramPredictor = new UnigramPredictor(filePath, 400);
                        UnigramPredictor.trainModel();

                        // Generate text
                        List<String> generatedText = UnigramPredictor.generateText(startWords, maxLength);

                        // Display generated text in predictionsDisplayArea
                        predictionsDisplayArea.setText(String.join(" ", generatedText));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error processing file: " + ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter starting words and select a maximum length.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        bigramsPredictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startWords = inputField.getText();
                int maxLength = (Integer) sizeComboBox.getSelectedItem();

                if (!startWords.isEmpty() && maxLength > 0) {
                    try {
                        // Initialize BigramPredictor with the selected file path and a predefined table size
                        String filePath = filePathField.getText();
                        BigramPredictor bigramPredictor = new BigramPredictor(filePath, 400);
                        bigramPredictor.trainModel();

                        // Generate text
                        List<String> generatedText = bigramPredictor.generateText(startWords, maxLength);

                        // Display generated text in predictionsDisplayArea
                        predictionsDisplayArea.setText(String.join(" ", generatedText));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error processing file: " + ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter starting words and select a maximum length.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        trigramsPredictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startWords = inputField.getText();
                int maxLength = (Integer) sizeComboBox.getSelectedItem();

                if (!startWords.isEmpty() && maxLength > 0) {
                    try {
                        // Initialize BigramPredictor with the selected file path and a predefined table size
                        String filePath = filePathField.getText();
                        TrigramsPredictor trigramPredictor = new TrigramsPredictor(filePath, 400);
                        trigramPredictor.trainModel();

                        // Generate text
                        List<String> generatedText = trigramPredictor.generateText(startWords, maxLength);

                        // Display generated text in predictionsDisplayArea
                        predictionsDisplayArea.setText(String.join(" ", generatedText));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error processing file: " + ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter starting words and select a maximum length.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        analyseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // createANewWindow
                JFrame analysisFrame = new JFrame("Class Analysis");
                analysisFrame.setSize(800, 600);
                analysisFrame.setLayout(new BorderLayout());

                //createATextAreaToShowTheClassStructure
                JTextArea analysisArea = new JTextArea();
                analysisArea.setEditable(false);
                analysisArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // setMonospacedFontsToMaintainFormatting

                // setTheTextContent
                String analysisContent = null;
                try {
                    analysisContent = "THIS IS FOR UNIGRAM ANALYSIS:\n"+Unigramanalys.resultforunigram()+"\nTHIS IS FOR BIGRAM ANALYSIS:\n"+Bigramanalys.resultforbigram()+"\nTHIS IS FOR TRIGRAM ANALYSIS:\n"+Trigramanalys.resultfortrigram();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // setTheStringToTheTextArea
                analysisArea.setText(analysisContent);

                // addATextAreaToTheWindow
                analysisFrame.add(new JScrollPane(analysisArea), BorderLayout.CENTER);

                // displayTheWindow
                analysisFrame.setVisible(true);
            }
        });






    }
}
