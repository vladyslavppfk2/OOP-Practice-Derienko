package com.example;

import java.io.Serializable;
import java.util.Objects;

/**
 * Один елемент колекції результатів.
 */
public class RoomItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String binaryLength;
    private String binaryWidth;
    private String binaryHeight;

    private int length;
    private int width;
    private int height;

    private int perimeter;
    private int area;
    private int volume;

    private transient String transientNote;

    /** Створює порожній об'єкт. */
    public RoomItem() {
        transientNote = "Об'єкт створено";
    }

    /** Конструктор копіювання. */
    public RoomItem(RoomItem other) {
        this.binaryLength = other.binaryLength;
        this.binaryWidth = other.binaryWidth;
        this.binaryHeight = other.binaryHeight;
        this.length = other.length;
        this.width = other.width;
        this.height = other.height;
        this.perimeter = other.perimeter;
        this.area = other.area;
        this.volume = other.volume;
        this.transientNote = other.transientNote;
    }

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
     * Оновлює залежні значення після зміни розмірів.
     */
    public void recalculate() {
        perimeter = 2 * (length + width);
        area = length * width;
        volume = length * width * height;
        binaryLength = Integer.toBinaryString(length);
        binaryWidth = Integer.toBinaryString(width);
        binaryHeight = Integer.toBinaryString(height);
    }

    /**
     * Масштабує елемент.
     * @param factor коефіцієнт масштабування
     */
    public void scale(int factor) {
        length *= factor;
        width *= factor;
        height *= factor;
        recalculate();
        transientNote = "Елемент масштабовано";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomItem)) return false;
        RoomItem roomItem = (RoomItem) o;
        return length == roomItem.length && width == roomItem.width && height == roomItem.height
                && perimeter == roomItem.perimeter && area == roomItem.area && volume == roomItem.volume
                && Objects.equals(binaryLength, roomItem.binaryLength)
                && Objects.equals(binaryWidth, roomItem.binaryWidth)
                && Objects.equals(binaryHeight, roomItem.binaryHeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(binaryLength, binaryWidth, binaryHeight, length, width, height, perimeter, area, volume);
    }
}

