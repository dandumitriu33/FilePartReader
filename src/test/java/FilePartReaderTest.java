import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class FilePartReaderTest {

    @Test
    @DisplayName("filePath is String")
    void setupFilePathIsString() {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        Assertions.assertTrue(testReader.getFilePath() instanceof String);
    }

    @Test
    @DisplayName("fromLine is Integer")
    void setupFromLineIsInteger() {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        Assertions.assertTrue(testReader.getFromLine() instanceof Integer);
    }

    @Test
    @DisplayName("toLine is Integer")
    void setupToLineIsInteger() {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        Assertions.assertTrue(testReader.getToLine() instanceof Integer);
    }

    @Test
    @DisplayName("toLine is smaller than fromLine error test")
    void setupToLineSmallerThanFromLine() {
        FilePartReader testReader = new FilePartReader();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testReader.setup("src/main/static/files/sample.txt", 4, 1);
        });
    }

    @Test
    @DisplayName("fromLine is smaller than 1 error test")
    void setupFromLineSmallerThanOne() {
        FilePartReader testReader = new FilePartReader();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testReader.setup("src/main/static/files/sample.txt", -3, 1);
        });
    }

    @Test
    @DisplayName("read() result is a String")
    void readResultIsAString() throws IOException {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        Assertions.assertTrue(testReader.read() instanceof String);
    }

    @Test
    @DisplayName("read() file is not found at path error test")
    void readFileNotFoundAtPathException() throws IOException {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/doesnt/exist/sample.txt", 1, 4);
        Assertions.assertThrows(IOException.class, testReader::read);
    }

    @Test
    @DisplayName("correct read check")
    void read() throws IOException {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        String correctResult = "The quick brown fox\n" +
                "Jumped over the lazy dog\n" +
                "This is the third row\n" +
                "Racecar radar interesting\n" +
                "This is the last line\n";
        Assertions.assertEquals(correctResult, testReader.read());
    }

    @Test
    @DisplayName("readLines reads the file via read()")
    void readLinesReadsTheFileViaRead() throws IOException {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        FilePartReader spy = spy(testReader);

        spy.readLines();
        verify(spy).read();
    }

    @Test
    void readLines() throws IOException {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        String correctResult = "The quick brown fox\n" +
                "Jumped over the lazy dog\n" +
                "This is the third row\n" +
                "Racecar radar interesting\n";
        Assertions.assertEquals(correctResult, testReader.readLines());
    }
}