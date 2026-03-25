package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Клас для тестування коректності обчислень
 * та серіалізації/десеріалізації.
 */
public class MainTest {

    @Test
    public void testCalculation() {
        RoomCalculator calculator = new RoomCalculator();

        calculator.init("1010", "0101", "0011");

        RoomData data = calculator.getData();

        assertEquals(10, data.getLength());
        assertEquals(5, data.getWidth());
        assertEquals(3, data.getHeight());

        assertEquals(30, data.getPerimeter());
        assertEquals(50, data.getArea());
        assertEquals(150, data.getVolume());
    }

    @Test
    public void testSerializationRestore() {
        RoomCalculator calculator = new RoomCalculator();
        calculator.init("111", "10", "11");

        try {
            calculator.save();
        } catch (Exception e) {
            fail("Помилка під час збереження: " + e.getMessage());
        }

        calculator.getData().setTransientNote("Змінене значення перед restore");

        try {
            calculator.restore();
        } catch (Exception e) {
            fail("Помилка під час відновлення: " + e.getMessage());
        }

        RoomData data = calculator.getData();

        assertEquals("111", data.getBinaryLength());
        assertEquals("10", data.getBinaryWidth());
        assertEquals("11", data.getBinaryHeight());

        assertEquals(7, data.getLength());
        assertEquals(2, data.getWidth());
        assertEquals(3, data.getHeight());

        assertEquals(18, data.getPerimeter());
        assertEquals(14, data.getArea());
        assertEquals(42, data.getVolume());

        assertNull(data.getTransientNote());
    }

    @Test
    public void testInvalidBinary() {
        try {
            RoomCalculator calculator = new RoomCalculator();
            calculator.init("102", "101", "11");
            fail("Очікувалась помилка IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Некоректне двійкове число: 102", e.getMessage());
        }
    }
}