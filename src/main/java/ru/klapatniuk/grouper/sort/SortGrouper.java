package ru.klapatniuk.grouper.sort;

import ru.klapatniuk.grouper.api.Grouper;

import java.util.*;

/**
 * @author Viacheslav Klapatniuk
 */
public class SortGrouper implements Grouper {

    @Override
    public Collection<List<String>> group(String[] strings) {

        Set<String> filtered = new HashSet<>();
        for (String string : strings) {
            filtered.add(string.toLowerCase());
        }

        Map<String, List<String>> groups = new HashMap<>();

        String original;
        char[] chars;
        List<String> group;
        for (String string : filtered) {
            original = string;

            // sort
            chars = string.toCharArray();
            Arrays.sort(chars);
            string = new String(chars);

            // group
            group = groups.get(string);
            if (group == null) {
                groups.put(string, new ArrayList<>(Collections.singleton(original)));
            } else {
                group.add(original);
            }
        }

        return groups.values();
    }
}
