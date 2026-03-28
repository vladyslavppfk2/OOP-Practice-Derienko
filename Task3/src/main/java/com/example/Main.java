package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Головний клас програми.
 * <p>
 * Реалізує діалоговий режим роботи з колекцією результатів:
 * введення нових даних, перегляд, збереження, відновлення,
 * а також зміну параметрів табличного відображення.
 * </p>
 */
public class Main {

    /** Поліморфне посилання на об'єкт відображення. */
    private final View view;

    /**
     * Конструктор.
     *
     * @param view об'єкт для роботи з результатами
     */
    public Main(View view) {
        this.view = view;
    }

    /** Виводить меню та обробляє команди користувача. */
    private void menu() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        do {
            printMenu();
            try {
                command = reader.readLine();
            } catch (IOException e) {
                System.out.println("Помилка введення: " + e.getMessage());
                return;
            }

            if (command == null || command.length() != 1) {
                System.out.println("Некоректна команда.");
                continue;
            }

            switch (command.charAt(0)) {
                case 'i' -> inputAndAdd(reader);
                case 'v' -> view.viewShow();
                case 'w' -> changeTableWidth(reader);
                case 's' -> saveData();
                case 'r' -> restoreData();
                case 'q' -> System.out.println("Завершення роботи.");
                default -> System.out.println("Невідома команда.");
            }
        } while (!"q".equals(command));
    }

    /** Друкує список команд. */
    private void printMenu() {
        System.out.println("\nОберіть команду:");
        System.out.println("i - ввести нові двійкові значення");
        System.out.println("v - переглянути всі результати");
        System.out.println("w - встановити ширину колонки таблиці");
        System.out.println("s - зберегти колекцію");
        System.out.println("r - відновити колекцію");
        System.out.println("q - вихід");
        System.out.print("Команда: ");
    }

    /**
     * Зчитує двійкові значення і додає новий запис до колекції.
     *
     * @param reader потік введення
     */
    private void inputAndAdd(BufferedReader reader) {
        try {
            System.out.print("Введіть двійкове значення довжини: ");
            String binaryLength = reader.readLine();

            System.out.print("Введіть двійкове значення ширини: ");
            String binaryWidth = reader.readLine();

            System.out.print("Введіть двійкове значення висоти: ");
            String binaryHeight = reader.readLine();

            view.viewInit(binaryLength, binaryWidth, binaryHeight);
            System.out.println("Запис успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        }
    }

    /** Змінює параметр відображення таблиці. */
    private void changeTableWidth(BufferedReader reader) {
        if (!(view instanceof ViewTable table)) {
            System.out.println("Поточне відображення не підтримує зміну ширини таблиці.");
            return;
        }

        try {
            System.out.print("Введіть нову ширину колонки: ");
            int width = Integer.parseInt(reader.readLine());
            table.init(width);
            System.out.println("Ширину колонки встановлено: " + table.getColumnWidth());
        } catch (NumberFormatException e) {
            System.out.println("Потрібно ввести ціле число.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        }
    }

    /** Зберігає колекцію у файл. */
    private void saveData() {
        try {
            view.viewSave();
            System.out.println("Колекцію успішно збережено.");
        } catch (IOException e) {
            System.out.println("Помилка серіалізації: " + e.getMessage());
        }
    }

    /** Відновлює колекцію з файлу. */
    private void restoreData() {
        try {
            view.viewRestore();
            System.out.println("Колекцію успішно відновлено.");
            System.out.println("Поля transient після десеріалізації не відновлюються.");
            view.viewShow();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка десеріалізації: " + e.getMessage());
        }
    }

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        View view = new ViewableTable().getView();
        new Main(view).menu();
    }
}
