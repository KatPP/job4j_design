package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder builder = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                builder.append((char) read);
            }
            String[] numbers = builder.toString().split(System.lineSeparator());
            for (String num : numbers) {
                if (Integer.parseInt(num) % 2 == 0) {
                    System.out.printf("%s четное%n", num);
                    continue;
                }
                System.out.printf("%s нечетное%n", num);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
