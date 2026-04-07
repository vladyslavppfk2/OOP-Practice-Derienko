package com.example;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Консольна команда додавання одного елемента.
 */
public class AddItemConsoleCommand implements ConsoleCommand {
    private final ViewResult view;
    private ArrayListSnapshot backup;

    public AddItemConsoleCommand(ViewResult view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'i';
    }

    @Override
    public String toString() {
        return "'i'nput";
    }

    @Override
    public void execute() {
        BufferedReader reader = Application.getInstance().getReader();
        backup = new ArrayListSnapshot(view.copyItems());
        try {
            System.out.print("Введіть двійкове значення довжини: ");
            String l = reader.readLine();
            System.out.print("Введіть двійкове значення ширини: ");
            String w = reader.readLine();
            System.out.print("Введіть двійкове значення висоти: ");
            String h = reader.readLine();
            view.viewInit(l, w, h);
            System.out.println("Елемент додано.");
            view.viewShow();
        } catch (IOException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        view.setItems(backup.items());
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
