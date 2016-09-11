package ru.klapatniuk.grouper.sort;

import ru.klapatniuk.grouper.api.Grouper;

import java.util.*;

/**
 * @author Viacheslav Klapatniuk
 */
public class SortGrouper implements Grouper {

    @Override
    public Collection<Set<String>> group(String[] strings) {

        Map<String, Set<String>> groups = new HashMap<>();

        String original;
        char[] chars;
        for (String string : strings) {

            // lower case
            string = string.toLowerCase();
            original = string;

            // sort
            chars = string.toCharArray();
            Arrays.sort(chars);
            string = new String(chars);

            // group
            if (groups.containsKey(string)) {
                groups.get(string).add(original);
            } else {
                groups.put(string, new HashSet<>(Collections.singleton(original)));
            }
        }

        return groups.values();
    }
}
