import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.filePath = "";
        this.fromLine = 0;
        this.toLine = 0;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {
        if (toLine < fromLine) throw new IllegalArgumentException("From Line cannot be after the To Line.");
        if (fromLine < 1) throw new IllegalArgumentException("From Line cannot be before the first line of text (< 1).");
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() throws IOException {
        StringBuilder output = new StringBuilder();
        File source = new File(this.filePath);
        if (!source.exists()) throw new IOException("File not found at given path.");
        Scanner myReader = new Scanner(source);
        while (myReader.hasNextLine()) {
            output.append(myReader.nextLine()).append("\n");
        }
        myReader.close();
        return output.toString();
    }

    public String readLines() throws IOException{
        StringBuilder output = new StringBuilder();
        int lineCounter = 1;
        File source = new File(this.filePath);
        if (!source.exists()) throw new IOException("File not found at given path.");
        Scanner myReader = new Scanner(source);
        while (myReader.hasNextLine()) {
            if (lineCounter >= this.fromLine || lineCounter <= toLine) {
                output.append(myReader.nextLine()).append("\n");
            }
            lineCounter++;
        }
        myReader.close();
        return output.toString();
    }

}
