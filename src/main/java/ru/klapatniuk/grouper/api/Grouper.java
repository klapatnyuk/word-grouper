package ru.klapatniuk.grouper.api;

import java.util.Collection;
import java.util.Set;

/**
 * @author Viacheslav Klapatniuk
 */
public interface Grouper {

    Collection<Set<String>> group(String[] strings);
}
