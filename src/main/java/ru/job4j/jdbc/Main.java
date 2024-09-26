package ru.job4j.jdbc;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }

        try (TableEditor tableEditor = new TableEditor(config)) {
            String tableName = "demo_table";

            /** Создаем таблицу */
            tableEditor.createTable(tableName);
            System.out.println("Схема таблицы после создания:");
            System.out.println(tableEditor.getTableScheme(tableName));

            /** Добавляем столбец */
            tableEditor.addColumn(tableName, "name", "TEXT");
            System.out.println("Схема таблицы после добавления столбца:");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.renameColumn(tableName, "name", "full_name");
            System.out.println("Схема таблицы после переименования столбца:");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.dropColumn(tableName, "full_name");
            System.out.println("Схема таблицы после удаления столбца:");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.dropTable(tableName);
            System.out.println("Таблица удалена.");
        }
    }
}
