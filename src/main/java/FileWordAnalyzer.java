import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWordAnalyzer {
    private FilePartReader currentPartReader;

    public FileWordAnalyzer(FilePartReader partReader) {
        this.currentPartReader = partReader;
    }

    public ArrayList<String> getWordsOrderedAlphabetically() throws IOException {
        String data = getCurrentPartReader().readLines();
        ArrayList<String> result = new ArrayList<>();
        String[] arr;
        arr = data.trim().split(" ");
        Arrays.sort(arr);
        for (String word : arr) {
            result.add(word);
        }
        return result;
    }

    public FilePartReader getCurrentPartReader() {
        return currentPartReader;
    }

    public ArrayList<String> getWordsContainingSubstring(String subWord) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        String data = this.currentPartReader.readLines();
        String[] arr;
        arr = data.split(" ");
        for (String word : arr) {
            if (word.contains(subWord)) {
                result.add(word);
            }
        }
        return result;
    }

    public ArrayList<String> getStringsWhichPalindromes() throws IOException {
        ArrayList<String> result = new ArrayList<>();
        String data = this.currentPartReader.readLines();
        String[] arr;
        arr = data.split(" ");
        for (String word : arr) {
            if (word.equals(new StringBuilder(word).reverse().toString())) {
                result.add(word);
                System.out.print(word + " ");
            }
        }
        return result;
    }
}
