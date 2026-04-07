package com.example;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Консольна команда масштабування всієї колекції.
 */
public class ChangeConsoleCommand implements ConsoleCommand {
    private final ViewResult view;
    private ArrayListSnapshot backup;

    public ChangeConsoleCommand(ViewResult view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'c';
    }

    @Override
    public String toString() {
        return "'c'hange all";
    }

    @Override
    public void execute() {
        if (view.isEmpty()) {
            System.out.println("Колекція порожня. Спочатку додайте елементи.");
            return;
        }
        BufferedReader reader = Application.getInstance().getReader();
        backup = new ArrayListSnapshot(view.copyItems());
        try {
            System.out.print("Введіть коефіцієнт масштабування для всієї колекції: ");
            int factor = Integer.parseInt(reader.readLine());
            view.scaleAll(factor);
            System.out.println("Усю колекцію масштабовано.");
            view.viewShow();
        } catch (IOException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Потрібно ввести ціле число.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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