package ru.klapatniuk.grouper.lazy;

import java.util.*;

/**
 * @author Viacheslav Klapatniuk
 */
public class Word {

    private final String original;
    private final char[] lower;
    private final NavigableSet<Integer> characters;
    private final Map<Integer, Integer> hashes;

    private Word(String original) {
        this.original = original;
        this.lower = new char[original.length()];
        this.characters = new TreeSet<>();
        this.hashes = new HashMap<>();

        int cursor = 0;
        int character;
        for (char letter : original.toCharArray()) {
            character = lower[cursor++] = Character.toLowerCase(letter);
            if (characters.add(character)) {
                hashes.put(character, character);
            } else {
                hashes.put(character, hashes.get(character) * 31 + character);
            }
        }
    }

    public static Word valueOf(String value) {
        return new Word(value);
    }

    public Integer pollFirstCharacterHash() {
        Integer character = characters.pollFirst();
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
        return Arrays.equals(lower, word.lower);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(lower);
    }
}
