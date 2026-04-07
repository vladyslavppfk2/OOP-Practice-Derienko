package com.example;

/**
 * Команда відновлення колекції з файла.
 */
public class RestoreConsoleCommand implements ConsoleCommand {
    private final ViewResult view;
    private ArrayListSnapshot backup;

    public RestoreConsoleCommand(ViewResult view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'r';
    }

    @Override
    public String toString() {
        return "'r'estore";
    }

    @Override
    public void execute() {
        backup = new ArrayListSnapshot(view.copyItems());
        try {
            view.viewRestore();
            System.out.println("Колекцію відновлено.");
            view.viewShow();
        } catch (Exception e) {
            System.out.println("Помилка відновлення: " + e.getMessage());
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
