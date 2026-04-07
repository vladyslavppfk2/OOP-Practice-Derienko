package com.example;

/**
 * Простий клас ручного тестування без зовнішніх бібліотек.
 */
public class MainTest {
    public static void main(String[] args) {
        testExecute();
        testChangeConsoleCommandLogic();
        testUndo();
        System.out.println("Усі тести пройдено успішно.");
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + " expected=" + expected + " actual=" + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    /**
     * Аналог testExecute() з методички: перевірка ChangeItemCommand.execute().
     */
    private static void testExecute() {
        ViewResult view = new ViewResult();
        view.viewInit("101", "10", "11"); // 5,2,3

        ChangeItemCommand command = new ChangeItemCommand(view);
        command.setItemIndex(0);
        command.setFactor(3);
        command.execute();

        RoomItem item = view.getItems().get(0);
        assertEquals(15, item.getLength(), "length");
        assertEquals(6, item.getWidth(), "width");
        assertEquals(9, item.getHeight(), "height");
        assertEquals(42, item.getPerimeter(), "perimeter");
        assertEquals(90, item.getArea(), "area");
        assertEquals(810, item.getVolume(), "volume");
    }

    /**
     * Перевірка основної функціональності зміни колекції.
     */
    private static void testChangeConsoleCommandLogic() {
        ViewResult view = new ViewResult();
        view.viewInit("10", "11", "1"); // 2,3,1
        view.scaleAll(2);

        RoomItem item = view.getItems().get(0);
        assertEquals(4, item.getLength(), "scaled length");
        assertEquals(6, item.getWidth(), "scaled width");
        assertEquals(2, item.getHeight(), "scaled height");
    }

    /**
     * Перевірка undo.
     */
    private static void testUndo() {
        ViewResult view = new ViewResult();
        view.viewInit("11", "10", "1"); // 3,2,1
        ChangeItemCommand command = new ChangeItemCommand(view);
        command.setItemIndex(0);
        command.setFactor(4);

        RoomItem before = new RoomItem(view.getItems().get(0));
        command.execute();
        command.undo();

        RoomItem afterUndo = view.getItems().get(0);
        assertTrue(before.equals(afterUndo), "undo must restore previous state");
    }
}