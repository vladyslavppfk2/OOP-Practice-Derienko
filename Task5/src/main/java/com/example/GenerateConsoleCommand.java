package com.example;

/**
 * Консольна команда випадкової генерації елемента.
 */
public class GenerateConsoleCommand implements ConsoleCommand {
    private final ViewResult view;
    private ArrayListSnapshot backup;

    public GenerateConsoleCommand(ViewResult view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'g';
    }

    @Override
    public String toString() {
        return "'g'enerate";
    }

    @Override
    public void execute() {
        backup = new ArrayListSnapshot(view.copyItems());
        System.out.println("Випадкова генерація одного елемента.");
        view.generateRandomItem();
        view.viewShow();
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
