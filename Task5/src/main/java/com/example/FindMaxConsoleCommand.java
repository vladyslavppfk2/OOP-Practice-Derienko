package com.example;

/**
 * Команда пошуку елемента з найбільшим об'ємом.
 */
public class FindMaxConsoleCommand implements ConsoleCommand {
    private final ViewResult view;

    public FindMaxConsoleCommand(ViewResult view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'f';
    }

    @Override
    public String toString() {
        return "'f'ind max volume";
    }

    @Override
    public void execute() {
        RoomItem max = view.findMaxVolumeItem();
        if (max == null) {
            System.out.println("Колекція порожня.");
            return;
        }
        System.out.println("Елемент з найбільшим об'ємом:");
        System.out.println("Довжина=" + max.getLength() + ", ширина=" + max.getWidth() +
                ", висота=" + max.getHeight() + ", об'єм=" + max.getVolume());
    }
}
