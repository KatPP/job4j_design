package ru.job4j.set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenAddNullAndDistinctElements() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.add(2)).isTrue();
        assertThat(set.add(null)).isTrue();
        assertThat(set.add(4)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.contains(4)).isTrue();
    }

    @Test
    void whenAddTrueAndFalse() {
        SimpleSet<Boolean> set = new SimpleArraySet<>();
        assertThat(set.add(true)).isTrue();
        assertThat(set.add(false)).isTrue();
        assertThat(set.contains(true)).isTrue();
        assertThat(set.contains(false)).isTrue();
    }

    @Test
    void whenAddEmptyString() {
        SimpleSet<String> set = new SimpleArraySet<>();
        assertThat(set.add("")).isTrue();
        assertThat(set.contains("")).isTrue();
    }

    @Test
    void whenAddAllDistinctStrings() {
        SimpleSet<String> set = new SimpleArraySet<>();
        assertThat(set.add("one")).isTrue();
        assertThat(set.add("two")).isTrue();
        assertThat(set.add("three")).isTrue();
        assertThat(set.contains("one")).isTrue();
        assertThat(set.contains("two")).isTrue();
        assertThat(set.contains("three")).isTrue();
    }
}