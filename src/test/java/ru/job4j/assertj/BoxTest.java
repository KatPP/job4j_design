package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotEmpty()
                .isNotEqualTo("Unknown object")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(-2, 5);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotEmpty()
                .isNotEqualTo("Sphere")
                .containsIgnoringCase("unknown")
                .containsIgnoringCase("object")
                .isEqualTo("Unknown object");
    }

    @Test
    void whenVertexFourThenReturnTwo() {
        Box box = new Box(4, 4);
        int result = box.getNumberOfVertices();
        int expected = 4;
        assertThat(result)
                .isNotZero()
                .isNotNegative()
                .isGreaterThan(3)
                .isLessThan(5)
                .isEven()
                .isEqualTo(expected);
    }

    @Test
    void whenVertexMinusTwoThenReturnMinusOne() {
        Box box = new Box(-2, 5);
        int result = box.getNumberOfVertices();
        int expected = -1;
        assertThat(result)
                .isNotZero()
                .isNegative()
                .isLessThan(0)
                .isOdd()
                .isEqualTo(expected);
    }

    @Test
    void whenVertexMinusTwoThenFigureNotExist() {
        Box box = new Box(-2, 10);
        boolean result = box.isExist();
        boolean expected = false;
        assertThat(result)
                .isFalse()
                .isEqualTo(expected);
    }

    @Test
    void whenVertexFourThenFigureExist() {
        Box box = new Box(4, 8);
        boolean result = box.isExist();
        boolean expected = true;
        assertThat(result)
                .isTrue()
                .isEqualTo(expected);
    }

    @Test
    void whenVertexZeroEdgeTwoThenAreaFiftyPointTwentySix() {
        Box box = new Box(0, 2);
        double result = box.getArea();
        double expected = 50.26d;
        assertThat(result)
                .isGreaterThan(50.25d)
                .isLessThan(50.27)
                .isEqualTo(expected, withPrecision(0.01d));
    }

    @Test
    void whenVertexFourEdgeEdgeFourThenAreaTwentySevenPointSeventyOne() {
        Box box = new Box(4, 4);
        double result = box.getArea();
        double expected = 27.71d;
        assertThat(result)
                .isGreaterThan(27.70d)
                .isLessThan(27.72d)
                .isEqualTo(expected, withPrecision(0.01d));
    }

    @Test
    void whenVertexEightEdgeOneThenAreaSixPointZero() {
        Box box = new Box(8, 1);
        double result = box.getArea();
        double expected = 6.0d;
        assertThat(result)
                .isGreaterThan(5.9d)
                .isLessThan(6.1d)
                .isEqualTo(expected, withPrecision(0.01d));
    }

    @Test
    void whenVertexZeroEdgeZeroThenAreaZero() {
        Box box = new Box(0, 0);
        double result = box.getArea();
        double expected = 0.0d;
        assertThat(result)
                .isBetween(-0.1d, 0.1d)
                .isEqualTo(expected, withPrecision(0.01d));
    }
}