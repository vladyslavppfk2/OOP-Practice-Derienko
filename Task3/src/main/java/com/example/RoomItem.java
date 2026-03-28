package com.example;

import java.io.Serializable;
import java.util.Objects;

/**
 * Серіалізований клас для збереження одного результату обчислень.
 * <p>
 * Об'єкт містить двійкові та десяткові значення параметрів приміщення,
 * а також результати обчислень периметра, площі та об'єму.
 * </p>
 */
public class RoomItem implements Serializable {

    /** Ідентифікатор версії серіалізації. */
    private static final long serialVersionUID = 1L;

    /** Двійкове значення довжини. */
    private String binaryLength;
    /** Двійкове значення ширини. */
    private String binaryWidth;
    /** Двійкове значення висоти. */
    private String binaryHeight;

    /** Десяткове значення довжини. */
    private int length;
    /** Десяткове значення ширини. */
    private int width;
    /** Десяткове значення висоти. */
    private int height;

    /** Периметр підлоги. */
    private int perimeter;
    /** Площа підлоги. */
    private int area;
    /** Об'єм приміщення. */
    private int volume;

    /** Поле для демонстрації transient. */
    private transient String transientNote;

    /** Конструктор без параметрів. */
    public RoomItem() {
        transientNote = "Об'єкт створено";
    }

    public String getBinaryLength() {
        return binaryLength;
    }

    public void setBinaryLength(String binaryLength) {
        this.binaryLength = binaryLength;
    }

    public String getBinaryWidth() {
        return binaryWidth;
    }

    public void setBinaryWidth(String binaryWidth) {
        this.binaryWidth = binaryWidth;
    }

    public String getBinaryHeight() {
        return binaryHeight;
    }

    public void setBinaryHeight(String binaryHeight) {
        this.binaryHeight = binaryHeight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getTransientNote() {
        return transientNote;
    }

    public void setTransientNote(String transientNote) {
        this.transientNote = transientNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoomItem roomItem)) {
            return false;
        }
        return length == roomItem.length
                && width == roomItem.width
                && height == roomItem.height
                && perimeter == roomItem.perimeter
                && area == roomItem.area
                && volume == roomItem.volume
                && Objects.equals(binaryLength, roomItem.binaryLength)
                && Objects.equals(binaryWidth, roomItem.binaryWidth)
                && Objects.equals(binaryHeight, roomItem.binaryHeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(binaryLength, binaryWidth, binaryHeight,
                length, width, height, perimeter, area, volume);
    }

    @Override
    public String toString() {
        return "RoomItem{" +
                "binaryLength='" + binaryLength + '\'' +
                ", binaryWidth='" + binaryWidth + '\'' +
                ", binaryHeight='" + binaryHeight + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", perimeter=" + perimeter +
                ", area=" + area +
                ", volume=" + volume +
                ", transientNote='" + transientNote + '\'' +
                '}';
    }
}
