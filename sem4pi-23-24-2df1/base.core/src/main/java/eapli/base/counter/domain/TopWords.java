package eapli.base.counter.domain;

import eapli.base.counter.services.WordCounterComparator;

import java.util.*;

public class TopWords {

    private Integer top;
    private List<WordCounter> wordCounters;

    public TopWords(Integer top) {
        this.top = top;
        this.wordCounters = new ArrayList<>();
    }

    public Integer top() {
        return top;
    }

    public List<WordCounter> wordCounters() {
        return wordCounters;
    }

    public void updateTop(Integer top) {
        this.top = top;
    }

    public void updateWordCounters(List<WordCounter> wordCounters) {
        if (wordCounters.size() < top) {
            return;
        }
        this.wordCounters = wordCounters;
    }

    public void addWordCounter(WordCounter wordCounter) {
        wordCounters.add(wordCounter);
    }

    public TopWords sortTopWords() {
        wordCounters.sort(new WordCounterComparator());
        if (wordCounters.size() < top) return this;
        wordCounters = wordCounters.subList(0, top);
        return this;
    }

    @Override
    public String toString() {
        return "Top " + top + " words: " + wordCounters;
    }
}