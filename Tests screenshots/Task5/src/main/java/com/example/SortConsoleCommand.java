package com.example;

/**
 * Команда сортування колекції за об'ємом.
 */
public class SortConsoleCommand implements ConsoleCommand {
    private final ViewResult view;
    private ArrayListSnapshot backup;

    public SortConsoleCommand(ViewResult view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'o';
    }

    @Override
    public String toString() {
        return "s'o'rt by volume";
    }

    @Override
    public void execute() {
        if (view.isEmpty()) {
            System.out.println("Колекція порожня.");
            return;
        }
        backup = new ArrayListSnapshot(view.copyItems());
        view.sortByVolumeDesc();
        System.out.println("Колекцію відсортовано за спаданням об'єму.");
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
