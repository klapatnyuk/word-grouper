package ru.klapatniuk.wordgrouper;

import org.junit.Assert;
import org.junit.Test;
import ru.klapatniuk.wordgrouper.lazy.LazyGrouper;
import ru.klapatniuk.wordgrouper.sort.SortGrouper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Viacheslav Klapatniuk
 */
public class GrouperTest {

    private static final String[] INPUT = {"ток", "торс", "кот", "рост", "фывап", "Кто"};

    private static final Collection<List<String>> EXPECTED = Arrays.asList(
            Arrays.asList("ток", "кот", "Кто"),
            Arrays.asList("рост", "торс"),
            Collections.singletonList("фывап"));

    @Test
    public void testSortGrouperStandard() {
        assertEquals(EXPECTED, new SortGrouper().group(INPUT));
    }

    @Test
    public void testLazyGrouperStandard() {
        assertEquals(EXPECTED, new LazyGrouper().group(INPUT));
    }

    private void assertEquals(Collection<List<String>> expected, Collection<List<String>> actual) {
        Assert.assertEquals(expected.size(), actual.size());

        Map<String, List<String>> expectedMap = toNormalizedMap(expected);
        Map<String, List<String>> actualMap = toNormalizedMap(actual);
        Assert.assertTrue(expectedMap.keySet().containsAll(actualMap.keySet()));

        expectedMap.forEach((token, group) -> Assert.assertEquals(group, actualMap.get(token)));
    }

    private Map<String, List<String>> toNormalizedMap(Collection<List<String>> groups) {
        return groups.stream().collect(
                Collectors.toMap(
                        group -> {
                            char[] chars = group.iterator().next().toLowerCase().toCharArray();
                            Arrays.sort(chars);
                            return new String(chars);
                        },
                        group -> group.stream().map(String::toLowerCase).sorted().collect(Collectors.toList())));
    }
}
