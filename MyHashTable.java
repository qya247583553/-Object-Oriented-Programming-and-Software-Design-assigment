public class MyHashTable {
        int size; // theSizeOfTheHashTable
        MyLinkedObject[] table; // anArrayOfStoredLinkedLists


        public MyHashTable(int size) {
            this.size = size;
            this.table = new MyLinkedObject[size];
        }
        public void insert(String word) {
            // useTheHashFunctionToCalculateTheHashValue
            int hash = computeHash(word);

            // If there is no linked list at that location, a new linked list is created
            if (table[hash] == null) {
                table[hash] = new MyLinkedObject(word);
            } else {
                //Otherwise, the word is inserted into the linked list
                table[hash].setWord(word);
            }
        }
        // The hash value is calculated based on the hash function
        private int computeHash(String word) {
            MyHashFunction hashFunction = new FirstLetterHashFunction(size, word);
            return hashFunction.computeHash();
        }




    }
