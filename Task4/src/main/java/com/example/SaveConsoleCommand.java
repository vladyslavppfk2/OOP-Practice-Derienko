package com.example;

import java.io.IOException;

/**
 * Команда збереження колекції.
 */
public class SaveConsoleCommand implements ConsoleCommand {
    private final ViewResult view;

    public SaveConsoleCommand(ViewResult view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 's';
    }

    @Override
    public String toString() {
        return "'s'ave";
    }

    @Override
    public void execute() {
        try {
            view.viewSave();
            System.out.println("Колекцію збережено.");
        } catch (IOException e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }
    }
}
