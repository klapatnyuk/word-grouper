package ru.klapatniuk.grouper.lazy;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO change to Set<String>
 *
 * @author Viacheslav Klapatniuk
 */
public class Group {

    private final Set<Word> words = new HashSet<>();

    public void add(Word word) {
        words.add(word);
    }

    public boolean isEmpty() {
        return words.isEmpty();
    }

    public Set<Word> getWords() {
        return words;
    }
}
