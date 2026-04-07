package com.example;

/**
 * Відображення колекції у вигляді таблиці з настроюваною шириною колонки.
 */
public class ViewTable extends ViewResult {
    private static final int DEFAULT_COLUMN_WIDTH = 14;
    private static final int MIN_COLUMN_WIDTH = 12;
    private int columnWidth = DEFAULT_COLUMN_WIDTH;

    public int setColumnWidth(int columnWidth) {
        this.columnWidth = Math.max(columnWidth, MIN_COLUMN_WIDTH);
        return this.columnWidth;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public void init(int columnWidth) {
        setColumnWidth(columnWidth);
    }

    private String fit(String text) {
        if (text == null) {
            text = "";
        }
        if (text.length() > columnWidth) {
            return text.substring(0, Math.max(1, columnWidth - 1)) + "…";
        }
        return String.format("%-" + columnWidth + "s", text);
    }

    private void printLine() {
        int columns = 10;
        for (int i = 0; i < columns; i++) {
            System.out.print("+");
            for (int j = 0; j < columnWidth + 2; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    @Override
    public void viewHeader() {
        printLine();
        System.out.printf("| %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |%n",
                fit("№"), fit("Двійк. довжина"), fit("Двійк. ширина"), fit("Двійк. висота"),
                fit("Довжина"), fit("Ширина"), fit("Висота"), fit("Периметр"), fit("Площа"), fit("Об'єм"));
        printLine();
    }

    @Override
    public void viewBody() {
        for (int i = 0; i < getItems().size(); i++) {
            RoomItem item = getItems().get(i);
            System.out.printf("| %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |%n",
                    fit(String.valueOf(i + 1)),
                    fit(item.getBinaryLength()),
                    fit(item.getBinaryWidth()),
                    fit(item.getBinaryHeight()),
                    fit(String.valueOf(item.getLength())),
                    fit(String.valueOf(item.getWidth())),
                    fit(String.valueOf(item.getHeight())),
                    fit(String.valueOf(item.getPerimeter())),
                    fit(String.valueOf(item.getArea())),
                    fit(String.valueOf(item.getVolume())));
        }
    }

    @Override
    public void viewFooter() {
        printLine();
        System.out.println("Кількість записів: " + getItems().size());
        System.out.println("Ширина колонки: " + columnWidth);
    }
}