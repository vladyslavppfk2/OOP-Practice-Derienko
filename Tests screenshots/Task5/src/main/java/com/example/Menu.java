package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Макрокоманда-контейнер для консольних команд.
 */
public class Menu implements Command {
    private final List<ConsoleCommand> menu = new ArrayList<>();

    public ConsoleCommand add(ConsoleCommand command) {
        menu.add(command);
        return command;
    }

    @Override
    public void execute() {
        while (true) {
            System.out.print(this);
            String input;
            try {
                input = Application.getInstance().getReader().readLine();
            } catch (Exception e) {
                System.out.println("Помилка введення: " + e.getMessage());
                return;
            }
            if (input == null || input.length() != 1) {
                System.out.println("Некоректна команда.");
                continue;
            }
            char key = input.charAt(0);
            if (key == 'q') {
                System.out.println("Завершення роботи.");
                return;
            }
            boolean found = false;
            for (ConsoleCommand command : menu) {
                if (command.getKey() == key) {
                    command.execute();
                    if (command.isUndoable()) {
                        Application.getInstance().pushHistory(command);
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Невідома команда.");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nОберіть команду: ");
        for (ConsoleCommand command : menu) {
            sb.append(command).append(", ");
        }
        sb.append("'q'uit: ");
        return sb.toString();
    }
}
