import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {

public static String readFile(String filePath) throws IOException {
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.toLowerCase(); // convertToLowercase
            line = replaceNumbersWithWords(line); // replaceNumbersWithWords
            line = removeUnwantedCharacters(line); // removeIllegalCharacters
            content.append(line).append("\n");
        }
    }
    return content.toString();
}
    public static String replaceNumbersWithWords(String line) {
        String[] numberWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < numberWords.length; i++) {
            line = line.replaceAll("\\b" + i + "\\b", numberWords[i]);
        }
        return line;


    }
    public static String removeUnwantedCharacters(String line) {
        // Use regular expressions to remove all characters except lowercase letters, periods, and apostrophes
        return line.replaceAll("[^a-z.'\\s]", "");
    }
}
