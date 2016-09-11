package ru.klapatniuk.grouper.lazy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Viacheslav Klapatniuk
 */
public class Node {

    private final Map<Integer, Node> children = new HashMap<>();
    private final Group group = new Group();

    private Word temp;

    private Node() {
    }

    public static Node newRoot() {
        return new Node();
    }

    public void buildAll(Collection<Word> words) {
        words.forEach(this::build);
    }

    public void build(Word word) {
        if (temp == null) {
            if (group.isEmpty() && children.isEmpty()) {
                // node is empty
                temp = word;
                return;
            }
        } else {
            // node is temp
            complete(temp);
            temp = null;
        }

        complete(word);
    }

    public List<Set<String>> getFlatGroups() {
        if (temp != null) {
            return Collections.singletonList(Collections.singleton(temp.getOriginal()));
        }

        List<Set<String>> result = new ArrayList<>();
        if (!group.isEmpty()) {
            result.add(group.getWords().stream().map(Word::getOriginal).collect(Collectors.toSet()));
        }

        for (Node child : children.values()) {
            result.addAll(child.getFlatGroups());
        }

        return result;
    }

    private void complete(Word word) {
        Integer hash = word.pollFirstCharacterHash();
        if (hash == null) {
            group.add(word);
            return;
        }

        Node child = children.get(hash);
        if (child == null) {
            child = new Node();
            children.put(hash, child);
        }
        child.build(word);
    }
}
