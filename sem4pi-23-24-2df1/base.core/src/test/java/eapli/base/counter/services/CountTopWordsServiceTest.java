package eapli.base.counter.services;

import eapli.base.counter.domain.TopWords;
import eapli.base.counter.domain.WordAggregator;
import eapli.base.counter.domain.WordCounter;
import eapli.base.counter.domain.TopWordsFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CountTopWordsServiceTest {

    private CountTopWordsService service;
    private File file1;
    private File file2;

    @BeforeEach
    void setUp() {
        service = new CountTopWordsService();
        file1 = new File("src/test/testFiles/file1.txt");
        file2 = new File("src/test/testFiles/file2.txt");

        service.countWords(List.of(file1, file2));
    }

    @Test
    void testCountTopWords() {
        TopWords result = service.topWords();

        assertNotNull(result);
        assertTrue(result.wordCounters().size() <= 20);
        assertEquals(2, result.wordCounters().stream().filter(wc -> wc.word().equals("hello")).findFirst().get().counter());
        assertEquals(4, result.wordCounters().stream().filter(wc -> wc.word().equals("test")).findFirst().get().counter());
    }

    @Test
    void testCountWords() {
        assertFalse(service.wordAggregators().isEmpty());
    }

    @Test
    void testProcessWord() {
        TopWordsFile topWordsFile = new TopWordsFile(file1, 20);
        service.processWord("hello", topWordsFile);

        assertTrue(topWordsFile.wordCounters().stream().anyMatch(wc -> wc.word().equals("hello")));
    }

    @Test
    void testAddWordAggregators() {
        TopWordsFile topWordsFile = new TopWordsFile(file1, 20);
        topWordsFile.addWordCounter(new WordCounter("hello", 2));

        service.addWordAggregators(topWordsFile, file1);

        assertTrue(service.wordAggregators().stream().anyMatch(wa -> wa.word().equals("hello")));
    }

    @Test
    void testTopWords() {
        TopWords topWords = service.topWords();

        assertNotNull(topWords);
        assertTrue(topWords.wordCounters().stream().anyMatch(wc -> wc.word().equals("hello")));
    }

    @Test
    void testFilesWhereWordIsPresent() {
        Map<File, Integer> expected = Map.of(file1, 2);
        Map<File, Integer> actual = service.filesWhereWordIsPresent("hello").occurrencesPerFile();

        assertEquals(expected, actual);
    }
}
