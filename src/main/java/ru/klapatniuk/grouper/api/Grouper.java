package ru.klapatniuk.grouper.api;

import java.util.Collection;
import java.util.List;

/**
 * @author Viacheslav Klapatniuk
 */
public interface Grouper {

    Collection<List<String>> group(String[] strings);
}
