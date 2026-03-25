package com.example;

import java.io.*;

/**
 * Головний клас програми.
 *
 * <p>
 * Реалізує діалоговий режим:
 * користувач може вводити дані,
 * зберігати об'єкт і відновлювати його.
 * </p>
 */
public class Main {

    /** Об'єкт калькулятора */
    private final RoomCalculator calculator = new RoomCalculator();

    /**
     * Головне меню
     */
    private void menu() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cmd;

        do {
            System.out.println("\nОберіть команду:");
            System.out.println("i - ввести дані");
            System.out.println("v - переглянути");
            System.out.println("s - зберегти");
            System.out.println("r - відновити");
            System.out.println("q - вихід");

            try {
                cmd = reader.readLine();
            } catch (IOException e) {
                return;
            }

            switch (cmd) {
                case "i":
                    input(reader);
                    break;
                case "v":
                    calculator.show();
                    break;
                case "s":
                    try {
                        calculator.save();
                        System.out.println("Збережено");
                    } catch (Exception e) {
                        System.out.println("Помилка");
                    }
                    break;
                case "r":
                    try {
                        calculator.restore();
                        System.out.println("Відновлено (transient буде null)");
                        calculator.show();
                    } catch (Exception e) {
                        System.out.println("Помилка");
                    }
                    break;
            }

        } while (!"q".equals(cmd));
    }

    /**
     * Ввід даних користувача
     */
    private void input(BufferedReader reader) {
        try {
            System.out.print("Довжина (двійкова): ");
            String l = reader.readLine();

            System.out.print("Ширина (двійкова): ");
            String w = reader.readLine();

            System.out.print("Висота (двійкова): ");
            String h = reader.readLine();

            calculator.init(l, w, h);
            calculator.show();

        } catch (Exception e) {
            System.out.println("Помилка вводу");
        }
    }

    /**
     * Точка входу в програму
     */
    public static void main(String[] args) {
        new Main().menu();
    }
}