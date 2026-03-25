package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Головний клас програми.
 * <p>
 * Реалізує діалоговий режим роботи з колекцією результатів:
 * введення нових даних,
 * перегляд,
 * збереження,
 * відновлення.
 * </p>
 */
public class Main {

    private final View view;

    /**
     * Конструктор.
     *
     * @param view об'єкт для роботи з результатами
     */
    public Main(View view) {
        this.view = view;
    }

    /**
     * Виводить меню та обробляє команди користувача.
     */
    private void menu() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        do {
            System.out.println("\nОберіть команду:");
            System.out.println("i - ввести нові двійкові значення");
            System.out.println("v - переглянути всі результати");
            System.out.println("s - зберегти колекцію");
            System.out.println("r - відновити колекцію");
            System.out.println("q - вихід");
            System.out.print("Команда: ");

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
                case 'i':
                    inputAndAdd(reader);
                    break;
                case 'v':
                    view.viewShow();
                    break;
                case 's':
                    try {
                        view.viewSave();
                        System.out.println("Колекцію успішно збережено.");
                    } catch (IOException e) {
                        System.out.println("Помилка серіалізації: " + e.getMessage());
                    }
                    break;
                case 'r':
                    try {
                        view.viewRestore();
                        System.out.println("Колекцію успішно відновлено.");
                        System.out.println("Поля transient після десеріалізації не відновлюються.");
                        view.viewShow();
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Помилка десеріалізації: " + e.getMessage());
                    }
                    break;
                case 'q':
                    System.out.println("Завершення роботи.");
                    break;
                default:
                    System.out.println("Невідома команда.");
            }
        } while (!"q".equals(command));
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

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        new Main(new ViewableResult().getView()).menu();
    }
}