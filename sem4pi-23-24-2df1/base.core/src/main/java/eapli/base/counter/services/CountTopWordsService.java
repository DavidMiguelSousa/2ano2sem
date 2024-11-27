package eapli.base.counter.services;

import eapli.base.counter.domain.TopWords;
import eapli.base.counter.domain.WordAggregator;
import eapli.base.counter.domain.WordCounter;
import eapli.base.counter.domain.TopWordsFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountTopWordsService extends WordCounterComparator {

    private final TopWords topWords = new TopWords(MAX_WORDS);
    private final List<WordAggregator> wordAggregators = new ArrayList<>();
    private static final Integer MAX_WORDS = 20;

    public TopWords countTopWords(List<File> files) {
        //count words for all files
        countWords(files);

        //return the top 20 words from all files combined
        return topWords();
    }

    public void countWords(List<File> files) {
        List<Thread> threads = new ArrayList<>();
        //create a thread to process each file in parallel
        AtomicInteger counter = new AtomicInteger(1);
        files.forEach(file -> {
            Thread thread = new Thread(() -> countWords(file));
            thread.setName("Thread " + counter.getAndIncrement());
            //debug print
            System.out.printf("%s created to process file %s.\n", thread.getName(), file.getName());
            threads.add(thread);
            //debug print
            System.out.printf("%s about to start.\n", thread.getName());
            thread.start();
        });

        //wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
                //debug print
                System.out.printf("%s finished.\n", thread.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void countWords(File file) {
        //create a new TopWordsFile object with the file and the maximum number of words
        TopWordsFile fileTopWords = new TopWordsFile(file, MAX_WORDS);
        //create a pattern to match email addresses
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        //create a pattern to match words
        Pattern wordPattern = Pattern.compile("[a-zA-Z]+");
        //read the file line by line
        BufferedReader br = null;
        try {

            //debug print
            System.out.println("Processing file: " + file.getName());

            br = new BufferedReader(new FileReader(file));

            //for each line in the file
            String line;
            //read the line and ensure it's not null
            while ((line = br.readLine()) != null) {

                //create a matcher for the email pattern
                Matcher emailMatcher = emailPattern.matcher(line);

                //for each email found in the line
                while (emailMatcher.find()) {
                    //process the email
                    processWord(emailMatcher.group(), fileTopWords);
                }

                //create a matcher for the word pattern
                Matcher wordMatcher = wordPattern.matcher(line);

                //for each word found in the line
                while (wordMatcher.find()) {
                    //process the word
                    processWord(wordMatcher.group(), fileTopWords);
                }

            }

            //debug print
            System.out.println("Finished processing file: " + file.getName());

        } catch (IOException e) {
            //exception handling
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (br != null) {

                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }

            }
        }

        //add to the list of WordAggregators
        addWordAggregators(fileTopWords, file);
    }

    protected void processWord(String word, TopWordsFile topWordsFile) {
        //create new WordCounter object with the word
        WordCounter wordCounter = new WordCounter(word);
        //if the word is already in the list of TopWords
        if (topWordsFile.wordCounters().contains(wordCounter)) {
            //then increment the counter of the word
            topWordsFile.wordCounters().get(topWordsFile.wordCounters().indexOf(wordCounter)).incrementCounter();
        } else {
            //else add the word to the list of TopWords, and the counter will start at 1
            topWordsFile.addWordCounter(wordCounter);
        }
    }

    protected void addWordAggregators(TopWordsFile fileTopWords, File file) {
        //for each WordCounter in the list of WordCounters
        for (WordCounter counter : fileTopWords.wordCounters()) {
            //create a new WordAggregator object with the word
            WordAggregator wordAggregator = new WordAggregator(counter.word());
            //if the WordAggregator is not in the list of WordAggregators
            if (!wordAggregators.contains(wordAggregator)) {
                //then add the WordAggregator to the list of WordAggregators
                wordAggregator.updateFileOccurrences(file, counter.counter());
                wordAggregators.add(wordAggregator);
            } else {
                //else update the WordAggregator in the list of WordAggregators
                wordAggregator = wordAggregators.get(wordAggregators.indexOf(wordAggregator));
                wordAggregator.updateFileOccurrences(file, counter.counter());
                wordAggregators.set(wordAggregators.indexOf(wordAggregator), wordAggregator);
            }
        }
    }

    public List<WordAggregator> wordAggregators() {
        return wordAggregators;
    }

    public TopWords topWords() {
        //for each WordAggregator in the list of WordAggregators
        for (WordAggregator wordAggregator : wordAggregators) {
            //add the WordCounter object to the list of top 20 words
            this.topWords.addWordCounter(new WordCounter(wordAggregator.word(), wordAggregator.totalOccurrences()));
        }

        //return topWords
        return this.topWords.sortTopWords();
    }

    public WordAggregator filesWhereWordIsPresent(String word) {
        //return the WordAggregator object with the word or create a new one if it doesn't exist with no files
        WordAggregator wordAggregator = new WordAggregator(word);
        if (wordAggregators.contains(wordAggregator)) {
            return wordAggregators.get(wordAggregators.indexOf(wordAggregator));
        } else {
            return wordAggregator;
        }
    }
}