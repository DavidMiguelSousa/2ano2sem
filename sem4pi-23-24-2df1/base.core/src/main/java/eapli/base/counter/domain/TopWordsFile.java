package eapli.base.counter.domain;

import eapli.base.counter.services.WordCounterComparator;

import java.io.File;

public class TopWordsFile extends TopWords {

    private final File file;

    public TopWordsFile(File file, int top) {
        super(top);
        this.file = file;
    }

    public File file() {
        return file;
    }

    @Override
    public TopWordsFile sortTopWords() {
        super.wordCounters().sort(new WordCounterComparator());
        if (super.wordCounters().size() < super.top()) return this;
        updateWordCounters(wordCounters().subList(0, top()));
        return this;
    }

}