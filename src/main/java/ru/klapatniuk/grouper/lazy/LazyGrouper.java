package ru.klapatniuk.grouper.lazy;

import ru.klapatniuk.grouper.api.Grouper;

import java.util.Collection;
import java.util.Set;

/**
 * @author Viacheslav Klapatniuk
 */
public class LazyGrouper implements Grouper {

    @Override
    public Collection<Set<String>> group(String[] strings) {

        Node root = new Node();

        for (String string : strings) {
            root.build(new Word(string));
        }

        return root.getFlatGroups();
    }
}
