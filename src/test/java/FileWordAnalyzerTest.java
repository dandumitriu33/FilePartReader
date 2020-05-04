import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class FileWordAnalyzerTest {


    @Test
    @DisplayName("calls readLines()")
    void analyzerUsesReadLines() throws IOException {
        FilePartReader testReader =  new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        FileWordAnalyzer testAnalyzer = new FileWordAnalyzer(testReader);

        FileWordAnalyzer spy = spy(testAnalyzer);

        spy.getWordsOrderedAlphabetically();
        verify(spy).getCurrentPartReader().readLines();
        //TODO ask how this is done

    }

    @Test
    @DisplayName("returns alphabetical ArrayList")
    void getWordsOrderedAlphabetically() throws IOException {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        FileWordAnalyzer testAnalyzer = new FileWordAnalyzer(testReader);
        String correctString = "The brown dog\n" +
                "This fox\n" +
                "Jumped interesting is lazy over quick radar row\n" +
                "Racecar the the third ";
        StringBuilder sb = new StringBuilder();
        for (String s : testAnalyzer.getWordsOrderedAlphabetically())
        {
            sb.append(s);
            sb.append(" ");
        }
        Assertions.assertEquals(sb.toString(), correctString);
    }


    @Test
    void getWordsContainingSubstring() throws IOException {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        FileWordAnalyzer testAnalyzer = new FileWordAnalyzer(testReader);
        String correctString = "the the third ";
        StringBuilder sb = new StringBuilder();
        for (String s : testAnalyzer.getWordsContainingSubstring("th"))
        {
            sb.append(s);
            sb.append(" ");
        }
        Assertions.assertEquals(sb.toString(), correctString);
    }

    @Test
    void getStringsWhichPalindromes() throws IOException {
        FilePartReader testReader = new FilePartReader();
        testReader.setup("src/main/static/files/sample.txt", 1, 4);
        FileWordAnalyzer testAnalyzer = new FileWordAnalyzer(testReader);
        String correctString = "radar ";
        StringBuilder sb = new StringBuilder();
        for (String s : testAnalyzer.getStringsWhichPalindromes())
        {
            sb.append(s);
            sb.append(" ");
        }
        Assertions.assertEquals(sb.toString(), correctString);
    }
}