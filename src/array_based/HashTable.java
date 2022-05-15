package array_based;


// imports
import exceptions.ElementNotFoundException;


public class HashTable {
    // attributes
    private int[] hashTable;
    private int size = 0;
    private double loadFactor;
    private Stack hashTableSizes;

    private final double A = (Math.sqrt(5) - 1) / 2;
    private boolean linearProbing = true;
    private boolean quadraticProbing = false;
    private boolean doubleProbing = false;

    private enum specialValues {
        NO_NUM(Integer.MIN_VALUE),
        DELETED_VAL(Integer.MAX_VALUE);

        private final int id;
        specialValues(int id) { this.id = id; }
        public int getValue() { return id; }
    }


    // constructor
    public HashTable() {
        hashTableSizes = new Stack(15);
        hashTableSizes.add(13);
        this.hashTable = new int[13];
        clearArr(this.hashTable);
    }

    public HashTable(String probeType) {
        this();
        if (probeType.equals("Quadratic")) {
            linearProbing = false;
            quadraticProbing = true;
        }
        else if (probeType.equals("Double")) {
            linearProbing = false;
            doubleProbing = true;
        }
    }

    public HashTable(int[] arr, String probeType) {
        this(probeType);
        if (this.linearProbing) {
            for (int num: arr)
                linearProbing(this.hashTable, num);
        }
        else if (this.quadraticProbing) {
            for (int num: arr)
                quadraticProbing(this.hashTable, num);
        }
        else {
            for (int num: arr)
                doubleHashProbing(this.hashTable, num);
        }
    }


    // Getters
    public int getSize() {
        return this.size;
    }


    // Helper
    private void clearArr(int[] arr) {
        for (int index = 0; index < arr.length; index++)
            arr[index] = specialValues.NO_NUM.getValue();
    }

    private int getInsertIndex(int[] arr, int num) {
        int insertIndex;
        if (linearProbing)
            insertIndex = linearProbing(arr, num);
        else if (quadraticProbing)
            insertIndex = quadraticProbing(arr, num);
        else 
            insertIndex = doubleHashProbing(arr, num);
        return insertIndex;
    }

    private int divisionHashFunction(int[] arr, int key) {
        return (key % arr.length);
    }

    private int multiplicationHashFunction(int[] arr, int key) {
        return (int) Math.floor(arr.length * ((key * A) % 1.0));
    }

    private int rollingHashFunction(int[] arr, String str) {
        long insertIndex = 0;
        for(int index = 1; index <= str.length(); index++)
            insertIndex += Math.pow(str.charAt(index), index);
        insertIndex %= arr.length;
        return (int) insertIndex;
    }

    private void updateLoadFactor() {
        this.loadFactor = ((double) this.size / hashTable.length);
    }

    private boolean validInsertIndex(int[] arr, int insertIndex) {
        return ((insertIndex >= 0) && ((arr[insertIndex] == specialValues.NO_NUM.getValue()) || (arr[insertIndex] == specialValues.DELETED_VAL.getValue())));
    }

    private int linearProbing(int[] arr, int num) {
        int insertIndex;
        for (int index = 0; index < arr.length; index++) {
            insertIndex = divisionHashFunction(arr, num + index);
            if (validInsertIndex(arr, insertIndex))
                return insertIndex;
        }
        return -1;
    }

    private int quadraticProbing(int[] arr, int num) {
        int insertIndex;
        for (int index = 0; index < arr.length; index++) {
            insertIndex = divisionHashFunction(arr, num + (index * index));
            if (validInsertIndex(arr, insertIndex))
                return insertIndex;
        }
        return -1;
    }

    private int doubleHashProbing(int[] arr, int num) {
        int insertIndex;
        for (int index = 0; index < arr.length; index++) {
            insertIndex = (divisionHashFunction(arr, num) + (index * multiplicationHashFunction(arr, num))) % arr.length;
            if (validInsertIndex(arr, insertIndex))
                return insertIndex;
        }
        return -1;
    }

    private boolean isPrime(int num) {
        if (num <= 1)
            return false;
        for (int i = 2; (i * i) < num; i++) {
            if ((num % i) == 0)
                return false;
        }
        return true;
    }

    private int getNextPrimeSize() {
        int minNewInd = this.hashTableSizes.peek() * 2;
        while (!isPrime(minNewInd)) minNewInd++;
        return minNewInd;
    }

    private void rehashArr(int[] newArr) {
        for (int element: this.hashTable) {
            if ((element != specialValues.NO_NUM.getValue()) && (element != specialValues.DELETED_VAL.getValue())) {
                int insertIndex = getInsertIndex(newArr, element);
                if (insertIndex == -1) {
                    System.out.println("making bigger");
                    doubleArr();
                    break;
                }
                newArr[insertIndex] = element;
            }
        }
    }

    private void doubleArr() {
        int newSize = getNextPrimeSize();
        hashTableSizes.add(newSize);
        int[] newHashTable = new int[newSize];
        clearArr(newHashTable);
        rehashArr(newHashTable);
        this.hashTable = newHashTable;
    }

    private void halfArr() {
        System.out.println(this.hashTableSizes);
        if (this.hashTableSizes.getSize() > 1) this.hashTableSizes.pop();
        int newSize = this.hashTableSizes.peek();
        if (this.size < newSize) {
            int[] newHashTable = new int[newSize];
            clearArr(newHashTable);
            rehashArr(newHashTable);
            this.hashTable = newHashTable;
        }
    }

    private int findIndex(int num) throws ElementNotFoundException {
        int atIndex;
        for (int i = 0; i < this.hashTable.length; i++) {
            if (linearProbing)
                atIndex = divisionHashFunction(this.hashTable, num + i);
            else if (quadraticProbing)
                atIndex = divisionHashFunction(this.hashTable, num + (i * i));
            else
                atIndex = (divisionHashFunction(this.hashTable, num) + (i * multiplicationHashFunction(this.hashTable, num))) % this.hashTable.length;

            if (hashTable[atIndex] == num) return atIndex;
            else if (hashTable[atIndex] == specialValues.NO_NUM.getValue()) break;
        }
        throw new ElementNotFoundException();
    }

    // ADT
    public void add(int num) {
        if (!inTable(num)) {
            int insertIndex = getInsertIndex(this.hashTable, num);
            while (insertIndex == -1) {
                doubleArr();
                insertIndex = getInsertIndex(this.hashTable, num);
            }
            hashTable[insertIndex] = num;
            this.size++;
            updateLoadFactor();
            if (this.loadFactor >= .5) doubleArr();
        }
    }

    public int delete(int num) throws ElementNotFoundException {
        int atIndex = findIndex(num);
        int atIndexVal = this.hashTable[atIndex];
        this.hashTable[atIndex] = specialValues.DELETED_VAL.getValue();
        this.size--;
        updateLoadFactor();
        if (this.loadFactor <= .125) halfArr();
        return atIndexVal;
    }

    public boolean inTable(int num) {
        try {
            findIndex(num);
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }
    

    // Default Methods
    public String toString() {
        String toPrint = "Hash Table: ";
        for (int index = 0; index < hashTable.length; index++) {
            if ((this.hashTable[index] == Integer.MIN_VALUE) || (this.hashTable[index] == Integer.MAX_VALUE)) {
                toPrint += "  --  ";
            }
            else {
                toPrint += "  " + this.hashTable[index] + "  ";
            }
        }
        return toPrint;
    }
}