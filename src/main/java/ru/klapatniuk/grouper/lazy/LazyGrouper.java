package ru.klapatniuk.grouper.lazy;

import ru.klapatniuk.grouper.api.Grouper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Viacheslav Klapatniuk
 */
public class LazyGrouper implements Grouper {

    @Override
    public Collection<Set<String>> group(String[] strings) {

        Node root = Node.newRoot();

        List<Word> words = Arrays.stream(strings).map(Word::valueOf).collect(Collectors.toList());
        root.buildAll(words);

        return root.getFlatGroups();
    }
}
