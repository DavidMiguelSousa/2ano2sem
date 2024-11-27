package eapli.base.counter.domain;

public class WordCounter {
    private final String word;
    private Integer counter;

    public WordCounter(String word, Integer counter) {
        this.word = word;
        this.counter = counter;
    }

    public WordCounter(String word) {
        this.word = word;
        this.counter = 1;
    }

    public String word() {
        return word;
    }

    public Integer counter() {
        return counter;
    }

    public void incrementCounter() {
        this.counter++;
    }

    @Override
    public String toString() {
        return String.format("Word: %s, Counter: %d", word, counter);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordCounter wc) {
            return wc.word().equalsIgnoreCase(this.word);
        }
        return false;
    }
}
