public abstract class MyHashFunction {
    protected int HashtableSize;
    protected String word; // A new word attribute has been added
    public MyHashFunction(int m, String word) {
        this.HashtableSize = m;
        this.word = word; // Set the value of word in the constructor
    }
    public abstract int computeHash();

}
class FirstLetterHashFunction extends MyHashFunction {
    public FirstLetterHashFunction(int m, String word) {
        super(m, word);
    }

    @Override

    public int computeHash() {
        // Use Java's built-in hashCode method to calculate the hash value
        int hash = word.hashCode();

        // Adjust the hash value to fit the size of the hash table (using modulus operations)
        hash = Math.abs(hash) % HashtableSize;

        return hash;
    }

}


