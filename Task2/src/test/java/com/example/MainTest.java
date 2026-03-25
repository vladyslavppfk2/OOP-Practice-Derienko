package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Клас для тестування обчислень та серіалізації.
 */
public class MainTest {

    /**
     * Перевірка правильності обчислень.
     */
    @Test
    public void testCalc() {
        ViewResult result = new ViewResult();

        result.viewInit("1010", "0101", "0011");

        RoomItem item = result.getItems().get(0);

        assertEquals(10, item.getLength());
        assertEquals(5, item.getWidth());
        assertEquals(3, item.getHeight());

        assertEquals(30, item.getPerimeter());
        assertEquals(50, item.getArea());
        assertEquals(150, item.getVolume());
    }

    /**
     * Перевірка серіалізації та десеріалізації.
     */
    @Test
    public void testRestore() {
        ViewResult result = new ViewResult();
        result.viewInit("111", "10", "11"); // 7, 2, 3

        try {
            result.viewSave();
            result.viewRestore();
        } catch (Exception e) {
            fail("Помилка під час серіалізації/десеріалізації: " + e.getMessage());
        }

        RoomItem item = result.getItems().get(0);

        assertEquals("111", item.getBinaryLength());
        assertEquals("10", item.getBinaryWidth());
        assertEquals("11", item.getBinaryHeight());

        assertEquals(7, item.getLength());
        assertEquals(2, item.getWidth());
        assertEquals(3, item.getHeight());

        assertEquals(18, item.getPerimeter());
        assertEquals(14, item.getArea());
        assertEquals(42, item.getVolume());

        assertNull(item.getTransientNote());
    }

    /**
     * Перевірка некоректного двійкового рядка.
     */
    @Test
    public void testInvalidBinary() {
        try {
            ViewResult result = new ViewResult();
            result.viewInit("102", "101", "11");
            fail("Очікувалась помилка IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Некоректне двійкове число: 102", e.getMessage());
        }
    }
}