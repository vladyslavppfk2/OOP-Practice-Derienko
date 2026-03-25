package com.example;

import java.io.Serializable;

/**
 * Клас RoomData призначений для зберігання:
 * <ul>
 *     <li>вхідних параметрів (довжина, ширина, висота у двійковому вигляді)</li>
 *     <li>перетворених значень у десятковій системі</li>
 *     <li>результатів обчислень (периметр, площа, об'єм)</li>
 * </ul>
 *
 * <p>
 * Клас реалізує інтерфейс {@link Serializable}, що дозволяє:
 * зберігати об'єкт у файл (серіалізація)
 * та відновлювати його (десеріалізація)
 * </p>
 *
 * <p>
 * Поле transient не зберігається при серіалізації
 * і після відновлення матиме значення null.
 * </p>
 */
public class RoomData implements Serializable {

    /** Версія серіалізації (потрібна для контролю сумісності) */
    private static final long serialVersionUID = 1L;

    /** Двійкові значення */
    private String binaryLength;
    private String binaryWidth;
    private String binaryHeight;

    /** Десяткові значення */
    private int length;
    private int width;
    private int height;

    /** Результати обчислень */
    private int perimeter;
    private int area;
    private int volume;

    /**
     * transient поле НЕ серіалізується
     * Використовується для демонстрації особливостей serializable
     */
    private transient String transientNote;

    /**
     * Конструктор за замовчуванням
     */
    public RoomData() {
        binaryLength = "0";
        binaryWidth = "0";
        binaryHeight = "0";
        transientNote = "Об'єкт створено";
    }

    // ===== ГЕТТЕРИ І СЕТТЕРИ =====

    public String getBinaryLength() { return binaryLength; }
    public void setBinaryLength(String binaryLength) { this.binaryLength = binaryLength; }

    public String getBinaryWidth() { return binaryWidth; }
    public void setBinaryWidth(String binaryWidth) { this.binaryWidth = binaryWidth; }

    public String getBinaryHeight() { return binaryHeight; }
    public void setBinaryHeight(String binaryHeight) { this.binaryHeight = binaryHeight; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getPerimeter() { return perimeter; }
    public void setPerimeter(int perimeter) { this.perimeter = perimeter; }

    public int getArea() { return area; }
    public void setArea(int area) { this.area = area; }

    public int getVolume() { return volume; }
    public void setVolume(int volume) { this.volume = volume; }

    public String getTransientNote() { return transientNote; }
    public void setTransientNote(String transientNote) { this.transientNote = transientNote; }

    /**
     * Повертає текстове представлення об'єкта
     */
    @Override
    public String toString() {
        return "RoomData{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", perimeter=" + perimeter +
                ", area=" + area +
                ", volume=" + volume +
                ", transientNote='" + transientNote + '\'' +
                '}';
    }
}