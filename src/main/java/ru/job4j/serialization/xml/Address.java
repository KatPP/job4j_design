package ru.job4j.serialization.xml;


import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class Address {

    @XmlAttribute
    private String street;
    @XmlAttribute
    private int number;
    @XmlAttribute
    private int floor;

    public Address() {
    }

    public Address(String street, int number, int floor) {
        this.street = street;
        this.number = number;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Address{"
                + "street='"
                + street + '\''
                + ", number="
                + number
                + ", floor="
                + floor
                + '}';
    }
}
