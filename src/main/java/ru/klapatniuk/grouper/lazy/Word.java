package ru.klapatniuk.grouper.lazy;

import java.util.*;

/**
 * @author Viacheslav Klapatniuk
 */
public class Word {

    private final String original;
    private final String lower;
    private final NavigableSet<Character> characters;
    private final Map<Character, Integer> hashes;

    public Word(String original) {
        this.original = original;
        this.lower = original.toLowerCase();
        this.characters = new TreeSet<>();
        this.hashes = new HashMap<>();

        for (char letter : lower.toCharArray()) {
            if (characters.add(letter)) {
                hashes.put(letter, (int) letter);
            } else {
                hashes.put(letter, hashes.get(letter) * 31 + letter);
            }
        }
    }

    public Integer pollFirstCharacterHash() {
        Character character = characters.pollFirst();
        if (character == null) {
            return null;
        }
        return hashes.get(character);
    }

    public String getOriginal() {
        return original;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return lower.equals(word.lower);

    }

    @Override
    public int hashCode() {
        return lower.hashCode();
    }
}
