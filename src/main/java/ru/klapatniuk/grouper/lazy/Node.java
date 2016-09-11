package ru.klapatniuk.grouper.lazy;

import java.util.*;

/**
 * @author Viacheslav Klapatniuk
 */
public class Node {

    private final Map<Integer, Node> children = new HashMap<>();
    private final Set<Word> group = new HashSet<>();

    private Word temp;

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
            Set<String> strings = new HashSet<>();
            for (Word word : group) {
                strings.add(word.getOriginal());
            }
            result.add(strings);
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
