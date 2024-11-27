package eapli.base.counter.services;

import eapli.base.counter.domain.WordCounter;

import java.util.Comparator;

public class WordCounterComparator implements Comparator<WordCounter> {
    @Override
    public int compare(WordCounter o1, WordCounter o2) {
        return -o1.counter().compareTo(o2.counter());
    }
}
