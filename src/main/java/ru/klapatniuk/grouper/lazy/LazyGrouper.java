package ru.klapatniuk.grouper.lazy;

import ru.klapatniuk.grouper.api.Grouper;

import java.util.*;

/**
 * @author Viacheslav Klapatniuk
 */
public class LazyGrouper implements Grouper {

    @Override
    public Collection<List<String>> group(String[] strings) {

        Node root = new Node();

        Set<String> filtered = new HashSet<>();
        String lower;
        for (String string : new HashSet<>(Arrays.asList(strings))) {
            lower = string.toLowerCase();
            if (filtered.add(lower)) {
                root.build(new Word(lower));
            }
        }

        return root.getFlatGroups();
    }
}
