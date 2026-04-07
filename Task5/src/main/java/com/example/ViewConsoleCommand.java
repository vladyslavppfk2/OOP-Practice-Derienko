package com.example;

/**
 * Команда перегляду поточного стану колекції.
 */
public class ViewConsoleCommand implements ConsoleCommand {
    private final ViewResult view;

    public ViewConsoleCommand(ViewResult view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'v';
    }

    @Override
    public String toString() {
        return "'v'iew";
    }

    @Override
    public void execute() {
        System.out.println("Поточна колекція:");
        view.viewShow();
    }
}
