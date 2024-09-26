package ru.job4j.collection;

public interface SimpleList<T> extends Iterable<T> {

    void add(T t);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();
}
