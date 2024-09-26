package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.*;


public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        int oldSize = set.size();
        if (!contains(value)) {
            set.add(value);
        }
        return set.size() != oldSize;
    }

    @Override
    public boolean contains(T value) {
        boolean isContains = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

}