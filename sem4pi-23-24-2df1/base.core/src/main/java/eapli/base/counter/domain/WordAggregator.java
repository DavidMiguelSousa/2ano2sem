package eapli.base.counter.domain;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WordAggregator {
    private final String word;
    private final Map<File, Integer> occurrencesPerFile;

    public WordAggregator(String word) {
        this.word = word;
        this.occurrencesPerFile = new HashMap<>();
    }

    public String word() {
        return word;
    }

    public Map<File, Integer> occurrencesPerFile() {
        return occurrencesPerFile;
    }

    public void updateFileOccurrences(File file, int occurrences) {
        occurrencesPerFile.put(file, occurrences);
    }

    public int totalOccurrences() {
        return occurrencesPerFile.values().stream().mapToInt(Integer::intValue).sum();
    }

    public String filesToString() {
        StringBuilder string = new StringBuilder();

        for (File file : occurrencesPerFile.keySet()) {
            string.append(file.getName());
            if (occurrencesPerFile.keySet().toArray()[occurrencesPerFile.size()-1] != file) string.append(", ");
        }

        return string.toString();
    }

    @Override
    public String toString() {
        if (occurrencesPerFile.isEmpty()) return String.format("Word %s doesn't appear in any file.\n", word);
        StringBuilder string = new StringBuilder();
        string.append("Word: ").append(word).append("\n");
        for (File file : occurrencesPerFile.keySet()) {
            string.append(String.format("\tFile %s: %d times.\n", file.getName(), occurrencesPerFile.get(file)));
        }
        return string.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WordAggregator that = (WordAggregator) obj;
        return word.equalsIgnoreCase(that.word);
    }
}
