package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 3;
        short sh = 17;
        char ch = 'a';
        int number = 127000;
        double db = Math.pow(3, 15);
        float f = 3.14F;
        boolean bool = false;
        long l = Math.abs(4L);
        LOG.debug("Primitive types: byte {}, short {}, char '{}', int {}, double {}, long {}, float {}, boolean {}",
                b, sh, ch, number, db, l, f, bool);
    }
}