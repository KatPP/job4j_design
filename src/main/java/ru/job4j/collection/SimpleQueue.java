package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        for (int index = 0; index < size - 1; index++) {
            output.push(input.pop());
        }
        T elem = input.pop();
        size--;
        for (int index = 0; index < size; index++) {
            input.push(output.pop());
        }
        return elem;
    }

    public void push(T value) {
        input.push(value);
        size++;
    }
}