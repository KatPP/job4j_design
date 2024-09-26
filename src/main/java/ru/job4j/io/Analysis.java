package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String readLine;
            boolean badFlag = false;
            StringBuilder result = new StringBuilder();
            String formatSourceFile = source.split("\\.")[1]
                    + System.lineSeparator()
                    + System.lineSeparator();
            writer.write(formatSourceFile);
            while ((readLine = reader.readLine()) != null) {
                String[] arr = readLine.split(" ");
                if (("400".equals(arr[0]) || "500".equals(arr[0])) && !badFlag) {
                    result.append(arr[1]).append(";");
                    badFlag = true;
                }
                if (("200".equals(arr[0]) || "300".equals(arr[0])) && badFlag) {
                    result.append(arr[1]).append(";").append(System.lineSeparator());
                    writer.write(result.toString());
                    result = new StringBuilder();
                    badFlag = false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/serverTwo.log", "data/target.csv");
    }
}