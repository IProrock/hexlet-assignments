package exercise;

import java.util.Arrays;

// BEGIN
public class ReversedSequence implements CharSequence {

    private char[] charArray = new char[1];
    private int length;

    public ReversedSequence(String str) {
        this.length = str.length();
        this.charArray = new char[this.length];

        for (int i = 0; i < this.length; i++) {
            this.charArray[i] = str.charAt(this.length - 1 - i);
        }
    }


    public String toString() {
        String s = new String(charArray);
        return s;
    }

    public char charAt(int i) {

        return charArray[i];
    }

    public int length() {
        return length;
    }

    public CharSequence subSequence(int start, int finish) {
        char[] result = new char[finish - start];
        System.arraycopy(this.charArray, start, result, 0, finish - start);
        CharSequence res = new String(result);
        return res;
    }
}
// END
