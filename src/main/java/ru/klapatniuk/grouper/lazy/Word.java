package ru.klapatniuk.grouper.lazy;

import java.util.*;

/**
 * @author Viacheslav Klapatniuk
 */
class Word {

    private final String lower;
    private final NavigableSet<Character> characters;
    private final Map<Character, Integer> hashes;

    Word(String original) {
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

    Integer pollFirstCharacterHash() {
        Character character = characters.pollFirst();
        if (character == null) {
            return null;
        }
        return hashes.get(character);
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
        return lower.hashCode();
    }
}
