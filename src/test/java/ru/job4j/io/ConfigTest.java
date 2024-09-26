package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenFileContainsCommentsAndEmptyLines() {
        String path = "./data/empty.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenFileNoContainsEqual() {
        String path = "./data/nocontainsequal.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFileNoContainsKey() {
        String path = "./data/nocontainskey.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFileNoContainsValue() {
        String path = "./data/nocontainsvalue.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFileContainsOnlyEqual() {
        String path = "./data/containsonlyequal.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFileContainsKeyEqualValueEqualValue() {
        String path = "./data/keyequalvalueequalvalue.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password=qwerty");
    }

    @Test
    void whenFileContainsKeyEqualValueEqual() {
        String path = "./data/keyequalvalueequal.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password=");
    }
}