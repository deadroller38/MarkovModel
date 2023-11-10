
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        StringBuilder ret = new StringBuilder();
        for (String word : myWords) {
            ret.append(word);
            ret.append(" ");
        }
        return ret.toString().trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) {
            return false;
        }
        for (int i = 0; i<myWords.length; i++) {
            if (!myWords[i].equals(other.wordAt(i))) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {    
        String[] shifted = new String[myWords.length];
        for (int i = 0; i<shifted.length-1; i++) {
            shifted[i] = myWords[i+1];
        }
        shifted[myWords.length-1] = word;
        WordGram out = new WordGram(shifted, 0, myWords.length);
        return out;
    }

    public int hashCode() {
        String words = toString();
        return words.hashCode();
    }
    
}