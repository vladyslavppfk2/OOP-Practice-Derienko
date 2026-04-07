package com.example;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Консольна команда зміни одного елемента колекції.
 */
public class ChangeOneItemConsoleCommand implements ConsoleCommand {
    private final ViewResult view;
    private ChangeItemCommand command;

    public ChangeOneItemConsoleCommand(ViewResult view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'e';
    }

    @Override
    public String toString() {
        return "chang'e' item";
    }

    @Override
    public void execute() {
        if (view.isEmpty()) {
            System.out.println("Колекція порожня. Спочатку додайте елементи.");
            return;
        }
        BufferedReader reader = Application.getInstance().getReader();
        try {
            view.viewShow();
            System.out.print("Введіть номер елемента: ");
            int index = Integer.parseInt(reader.readLine()) - 1;
            System.out.print("Введіть коефіцієнт масштабування: ");
            int factor = Integer.parseInt(reader.readLine());
            command = new ChangeItemCommand(view);
            command.setItemIndex(index);
            command.setFactor(factor);
            command.execute();
            System.out.println("Елемент змінено.");
            view.viewShow();
        } catch (IOException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Потрібно ввести ціле число.");
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        if (command != null) {
            command.undo();
        }
    }

    @Override
    public boolean isUndoable() {
        return command != null;
    }
}