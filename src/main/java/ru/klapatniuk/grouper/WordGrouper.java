package ru.klapatniuk.grouper;

import org.apache.commons.io.IOUtils;
import ru.klapatniuk.grouper.lazy.LazyGrouper;
import ru.klapatniuk.grouper.sort.SortGrouper;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Viacheslav Klapatniuk
 */
public class WordGrouper {

    public static void main(String... args) throws IOException {

        InputStream is = WordGrouper.class.getClassLoader().getResourceAsStream("ru/klapatniuk/grouper/input.txt");
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer, StandardCharsets.UTF_8);

        List<String> wordsList = Arrays.stream(writer.toString().split("[^а-яА-Я]")).filter(item -> !item.isEmpty())
                .collect(Collectors.toList());
        String[] words = wordsList.toArray(new String[wordsList.size()]);

        Collection<Set<String>> groups;

        long start1 = System.currentTimeMillis();
        groups = new LazyGrouper().group(words);
        groups.stream().filter(item -> item.size() > 2).forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        groups = new SortGrouper().group(words);
        groups.stream().filter(item -> item.size() > 2).forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start2);

    }
}
