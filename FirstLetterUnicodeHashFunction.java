public class FirstLetterUnicodeHashFunction extends MyHashFunction{
    public FirstLetterUnicodeHashFunction(int m, String word) {
        super(m, word);
    }

    @Override
    public int computeHash() {
        // Check if the word is empty to avoid errors
        if (word == null || word.isEmpty()) {
            return 0;
        }

        // Get the unicode value of the first character of the word
        int unicodeValue = word.codePointAt(0);

        // Calculate the hash by taking the modulo of the unicode value with the hash table size
        return unicodeValue % HashtableSize;
    }
}
