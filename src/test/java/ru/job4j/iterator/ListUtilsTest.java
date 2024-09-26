package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfElementLessThenTwoThenContainsOneElement() {
        ListUtils.removeIf(input, n -> n < 2);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenRemoveIfElementLessThenFiveThenContainsZeroElements() {
        ListUtils.removeIf(input, n -> n < 5);
        assertThat(input).hasSize(0);
    }

    @Test
    void whenRemoveIfElementsLessThenZeroThenContainsThreeElements() {
        ListUtils.removeIf(input, n -> n < 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIfElementEqualsOneThenReplaceToFive() {
        ListUtils.replaceIf(input, n -> n == 1, 5);
        assertThat(input).hasSize(2).containsSequence(5, 3);
    }

    @Test
    void whenReplaceIfElementEqualsOneOrThreeThenReplaceToFive() {
        ListUtils.replaceIf(input, n -> n == 1 || n == 3, 5);
        assertThat(input).hasSize(2).containsSequence(5, 5);
    }

    @Test
    void whenReplaceIfElementEqualsZeroThenNotWillReplace() {
        ListUtils.replaceIf(input, n -> n == 0, 5);
        assertThat(input).hasSize(2).containsSequence(input);
    }

    @Test
    void whenRemoveAllThenElementsSequenceContainsFirstElement() {
        List<Integer> elements = new ArrayList<>(List.of(1));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenRemoveAllThenElementsSequenceContainsLastElement() {
        List<Integer> elements = new ArrayList<>(List.of(3));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenRemoveAllThenSequenceContainsAllElements() {
        List<Integer> elements = new ArrayList<>(List.of(1, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(0);
    }

    @Test
    void whenRemoveAllAndSourceSequenceContainsZeroElements() {
        List<Integer> elements = new ArrayList<>(List.of(1, 3));
        List<Integer> input = new ArrayList<>();
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(0);
    }

    @Test
    void whenRemoveAllAndElementsSequenceContainsZeroElements() {
        List<Integer> elements = new ArrayList<>(List.of());
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }
}