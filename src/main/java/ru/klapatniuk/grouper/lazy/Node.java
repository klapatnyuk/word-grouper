package ru.klapatniuk.grouper.lazy;

import java.util.*;

/**
 * @author Viacheslav Klapatniuk
 */
class Node {

    private final Map<Integer, Node> children = new HashMap<>();
    private final List<String> group = new ArrayList<>();

    private Word temp;

    void build(Word word) {
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

    List<List<String>> getFlatGroups() {
        if (temp != null) {
            return Collections.singletonList(Collections.singletonList(temp.toString()));
        }

        List<List<String>> result = new ArrayList<>();
        if (!group.isEmpty()) {
            result.add(group);
        }

        for (Node child : children.values()) {
            result.addAll(child.getFlatGroups());
        }

        return result;
    }

    private void complete(Word word) {
        Integer hash = word.pollFirstCharacterHash();
        if (hash == null) {
            group.add(word.toString());
            return;
        }

        Node child = children.computeIfAbsent(hash, k -> new Node());
        child.build(word);
    }
}
