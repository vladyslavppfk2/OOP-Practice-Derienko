package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Клас для тестування основної функціональності.
 */
public class MainTest {

    /** Перевірка правильності обчислень. */
    @Test
    public void testCalc() {
        ViewTable result = new ViewTable();
        result.viewInit("1010", "0101", "0011");

        RoomItem item = result.getItems().get(0);

        assertEquals(10, item.getLength());
        assertEquals(5, item.getWidth());
        assertEquals(3, item.getHeight());
        assertEquals(30, item.getPerimeter());
        assertEquals(50, item.getArea());
        assertEquals(150, item.getVolume());
    }

    /** Перевірка перевантаження та зміни ширини таблиці. */
    @Test
    public void testTableWidthOverloading() {
        ViewTable table = new ViewTable();
        table.init(20);
        assertEquals(20, table.getColumnWidth());

        table.init(18, "11", "10", "01");
        assertEquals(18, table.getColumnWidth());
        assertEquals(1, table.getItems().size());
    }

    /** Перевірка серіалізації та десеріалізації. */
    @Test
    public void testRestore() throws Exception {
        ViewTable result = new ViewTable();
        result.viewInit("111", "10", "11");
        result.viewSave();
        result.viewRestore();

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

    /** Перевірка некоректного двійкового рядка. */
    @Test
    public void testInvalidBinary() {
        ViewResult result = new ViewResult();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> result.viewInit("102", "101", "11")
        );

        assertEquals("Некоректне двійкове число: 102", exception.getMessage());
    }
}
