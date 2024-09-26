package ru.job4j.serialization.json;


public class Kitty {
    private final String name;
    private final int age;

    public Kitty(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Kitty{"
                + "name='"
                + name
                + '\''
                + ", age="
                + age
                + '}';
    }
}
