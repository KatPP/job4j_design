package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        int currentCount = count;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
        }
        return currentCount != count;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        MapEntry<K, V> entry = table[index];
        return Objects.hashCode(entry) == 0
                ? null
                : !isEqualsHashCodesOfKeys(key, entry.key)
                ? null
                : !Objects.equals(key, entry.key)
                ? null
                : entry.value;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        MapEntry<K, V> entry = table[index];
        int currentCount = count;
        if (entry != null && isEqualsHashCodesOfKeys(key, entry.key)) {
            if (Objects.equals(key, entry.key)) {
                table[index] = null;
                modCount++;
                count--;
            }
        }
        return currentCount != count;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity) {
                    if (table[index] != null) {
                        break;
                    }
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return Objects.hashCode(hashCode);
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean isEqualsHashCodesOfKeys(K key, K entryKey) {
        return Objects.hashCode(key) != Objects.hashCode(entryKey) || Objects.equals(key, entryKey);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry entry : table) {
            if (entry != null) {
                int index = getIndex((K) entry.key);
                if (newTable[index] == null) {
                    newTable[index] = entry;
                }
            }
        }

        table = newTable;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
