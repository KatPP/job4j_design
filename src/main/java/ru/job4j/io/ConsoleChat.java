package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static boolean isStoppedBot;
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botAnswers = readPhrases();
        Random randomIndexBotAnswer = new Random();
        System.out.println("Начните беседу: ");
        String userAnswer = new Scanner(System.in).nextLine();
        while (!OUT.equals(userAnswer)) {
            List<String> chat = new ArrayList<>();
            chat.add(userAnswer);
            if (STOP.equals(userAnswer)) {
                isStoppedBot = true;
            }
            if (CONTINUE.equals(userAnswer)) {
                isStoppedBot = false;
            }
            if (!isStoppedBot) {
                String botAnswer = botAnswers.get(randomIndexBotAnswer.nextInt(botAnswers.size()));
                System.out.println(botAnswer);
                chat.add(botAnswer);
            }
            saveLog(chat);
            userAnswer = new Scanner(System.in).nextLine();
        }
    }

    private List<String> readPhrases() {
        List<String> botAnswers = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            while (buffer.ready()) {
                botAnswers.add(buffer.readLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return botAnswers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(botAnswers, true))) {
            log.stream().forEach(writer::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/botAnswers.txt", "./data/answersUserLog.txt");
        consoleChat.run();
    }
}