package com.example;

import java.util.Formatter;

/**
 * ConcreteProduct шаблону Factory Method.
 * <p>
 * Виводить результати у вигляді текстової таблиці.
 * Демонструє перевизначення та перевантаження методів.
 * </p>
 */
public class ViewTable extends ViewResult {

    /** Мінімальна допустима ширина колонки. */
    private static final int MIN_COLUMN_WIDTH = 14;

    /** Ширина колонки за замовчуванням. */
    private static final int DEFAULT_COLUMN_WIDTH = 16;

    /** Поточна ширина колонки. */
    private int columnWidth;

    /** Створює табличне представлення з шириною за замовчуванням. */
    public ViewTable() {
        this.columnWidth = DEFAULT_COLUMN_WIDTH;
    }

    /**
     * Створює табличне представлення із заданою шириною колонки.
     *
     * @param columnWidth ширина колонки
     */
    public ViewTable(int columnWidth) {
        setColumnWidth(columnWidth);
    }

    /**
     * Встановлює ширину колонки.
     *
     * @param columnWidth нова ширина
     * @return встановлена ширина
     */
    public int setColumnWidth(int columnWidth) {
        if (columnWidth < MIN_COLUMN_WIDTH) {
            throw new IllegalArgumentException(
                    "Ширина колонки повинна бути не меншою за " + MIN_COLUMN_WIDTH);
        }
        this.columnWidth = columnWidth;
        return this.columnWidth;
    }

    /**
     * Повертає ширину колонки.
     *
     * @return поточна ширина колонки
     */
    public int getColumnWidth() {
        return columnWidth;
    }

    /**
     * Перевантаження методу: змінює лише ширину таблиці.
     *
     * @param columnWidth нова ширина колонки
     */
    public void init(int columnWidth) {
        setColumnWidth(columnWidth);
    }

    /**
     * Перевантаження методу: змінює ширину і додає новий елемент.
     *
     * @param columnWidth ширина колонки
     * @param binaryLength двійкове значення довжини
     * @param binaryWidth двійкове значення ширини
     * @param binaryHeight двійкове значення висоти
     */
    public void init(int columnWidth, String binaryLength, String binaryWidth, String binaryHeight) {
        setColumnWidth(columnWidth);
        viewInit(binaryLength, binaryWidth, binaryHeight);
    }

    /**
     * Формує горизонтальну лінію таблиці.
     *
     * @return рядок-розділювач
     */
    private String createLine() {
        int columns = 10;
        return "-".repeat(columns * (columnWidth + 3) + 1);
    }

    /**
     * Форматує комірку таблиці.
     *
     * @param value значення
     * @return форматований текст комірки
     */
    private String formatCell(String value) {
        Formatter formatter = new Formatter();
        formatter.format(" %-" + columnWidth + "s |", value);
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /** Переозначення заголовка для табличного відображення. */
    @Override
    public void viewHeader() {
        String line = createLine();
        System.out.println(line);
        System.out.print("|");
        System.out.print(formatCell("№"));
        System.out.print(formatCell("Двійк. довжина"));
        System.out.print(formatCell("Двійк. ширина"));
        System.out.print(formatCell("Двійк. висота"));
        System.out.print(formatCell("Довжина"));
        System.out.print(formatCell("Ширина"));
        System.out.print(formatCell("Висота"));
        System.out.print(formatCell("Периметр"));
        System.out.print(formatCell("Площа"));
        System.out.print(formatCell("Об'єм"));
        System.out.println();
        System.out.println(line);
    }

    /** Переозначення тіла таблиці. */
    @Override
    public void viewBody() {
        int index = 1;
        for (RoomItem item : getItems()) {
            System.out.print("|");
            System.out.print(formatCell(String.valueOf(index++)));
            System.out.print(formatCell(item.getBinaryLength()));
            System.out.print(formatCell(item.getBinaryWidth()));
            System.out.print(formatCell(item.getBinaryHeight()));
            System.out.print(formatCell(String.valueOf(item.getLength())));
            System.out.print(formatCell(String.valueOf(item.getWidth())));
            System.out.print(formatCell(String.valueOf(item.getHeight())));
            System.out.print(formatCell(String.valueOf(item.getPerimeter())));
            System.out.print(formatCell(String.valueOf(item.getArea())));
            System.out.print(formatCell(String.valueOf(item.getVolume())));
            System.out.println();
        }
    }

    /** Переозначення нижньої частини таблиці. */
    @Override
    public void viewFooter() {
        System.out.println(createLine());
        System.out.println("Кількість записів: " + getItems().size());
        System.out.println("Ширина колонки: " + columnWidth);
    }
}
