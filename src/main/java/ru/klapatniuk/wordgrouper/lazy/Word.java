package ru.klapatniuk.wordgrouper.lazy;

import java.util.*;

/**
 * @author Viacheslav Klapatniuk
 */
class Word {

    private final String lower;

    private int hash;
    private NavigableSet<Character> characters;
    private Map<Character, Integer> hashes;

    Word(String lower) {
        this.lower = lower;
    }

    Integer pollFirstCharacterHash() {
        if (characters == null) {
            characters = new TreeSet<>();
            hashes = new HashMap<>();

            for (char letter : lower.toCharArray()) {
                if (characters.add(letter)) {
                    hashes.put(letter, (int) letter);
                } else {
                    hashes.put(letter, hashes.get(letter) * 31 + letter);
                }
            }
        }
        if (characters.isEmpty()) {
            return null;
        }
        return hashes.get(characters.pollFirst());
    }

    @Override
    public String toString() {
        return lower;
    }

    @Override
    public boolean equals(Object o) {
        return lower.equals(((Word) o).lower);
    }

    @Override
    public int hashCode() {
        if (hash == 0) {
            hash = lower.hashCode();
        }
        return hash;
    }
}
