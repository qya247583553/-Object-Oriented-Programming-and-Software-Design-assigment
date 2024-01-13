public class MyLinkedObject {
    public String word;
    public int count;
    public MyLinkedObject next;

    public MyLinkedObject(String w) {
        this.word = w;
        this.count = 1; //
        this.next = null; //
    }
    public void setWord(String w) {
        // Check if w is equal to the current word
        if (w.equals(word)) {
            count++; // Increment count if w is equal to word
        } else if (next == null) {
            // If the next object does not exist, create a new object for w
            next = new MyLinkedObject(w);
        } else if (w.compareTo(next.word) < 0) {
            // Create a new object for w and insert it between this and the next objects
            MyLinkedObject newObject = new MyLinkedObject(w);
            newObject.next = next;
            this.next = newObject;
        } else {
            // Pass on to the next object using recursion
            next.setWord(w);
        }
    }
}


