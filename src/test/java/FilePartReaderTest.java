import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FilePartReaderTest {

    @BeforeAll
    static void setup() {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
    }

    @Test
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