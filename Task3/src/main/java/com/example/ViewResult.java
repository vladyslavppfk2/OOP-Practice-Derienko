package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Базовий клас відображення результатів.
 * <p>
 * Реалізує інтерфейс {@link View} та зберігає колекцію результатів
 * обчислень у вигляді списку {@link RoomItem}.
 * </p>
 */
public class ViewResult implements View {

    /** Ім'я файлу для серіалізації. */
    private static final String FILE_NAME = "roomitems.bin";

    /** Колекція результатів обчислень. */
    private ArrayList<RoomItem> items;

    /** Конструктор без параметрів. */
    public ViewResult() {
        items = new ArrayList<>();
    }

    /**
     * Повертає колекцію результатів.
     *
     * @return список об'єктів RoomItem
     */
    public List<RoomItem> getItems() {
        return items;
    }

    /**
     * Перевіряє, чи рядок є коректним двійковим числом.
     *
     * @param binary рядок для перевірки
     * @return true, якщо рядок містить лише 0 та 1
     */
    protected boolean isBinary(String binary) {
        return binary != null && binary.matches("[01]+");
    }

    /**
     * Перетворює двійковий рядок у десяткове число.
     *
     * @param binary двійковий рядок
     * @return десяткове значення
     */
    protected int binaryToDecimal(String binary) {
        if (!isBinary(binary)) {
            throw new IllegalArgumentException("Некоректне двійкове число: " + binary);
        }
        return Integer.parseInt(binary, 2);
    }

    /**
     * Обчислює периметр підлоги.
     *
     * @param length довжина
     * @param width ширина
     * @return периметр
     */
    public int calcPerimeter(int length, int width) {
        return 2 * (length + width);
    }

    /**
     * Обчислює площу підлоги.
     *
     * @param length довжина
     * @param width ширина
     * @return площа
     */
    public int calcArea(int length, int width) {
        return length * width;
    }

    /**
     * Обчислює об'єм приміщення.
     *
     * @param length довжина
     * @param width ширина
     * @param height висота
     * @return об'єм
     */
    public int calcVolume(int length, int width, int height) {
        return length * width * height;
    }

    /**
     * Додає новий елемент у колекцію та виконує обчислення.
     *
     * @param binaryLength двійкове значення довжини
     * @param binaryWidth двійкове значення ширини
     * @param binaryHeight двійкове значення висоти
     */
    @Override
    public void viewInit(String binaryLength, String binaryWidth, String binaryHeight) {
        RoomItem item = new RoomItem();

        item.setBinaryLength(binaryLength);
        item.setBinaryWidth(binaryWidth);
        item.setBinaryHeight(binaryHeight);

        int length = binaryToDecimal(binaryLength);
        int width = binaryToDecimal(binaryWidth);
        int height = binaryToDecimal(binaryHeight);

        item.setLength(length);
        item.setWidth(width);
        item.setHeight(height);
        item.setPerimeter(calcPerimeter(length, width));
        item.setArea(calcArea(length, width));
        item.setVolume(calcVolume(length, width, height));
        item.setTransientNote("Останній розрахунок виконано успішно");

        items.add(item);
    }

    @Override
    public void viewHeader() {
        System.out.println("Результати обчислень:");
    }

    @Override
    public void viewBody() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    @Override
    public void viewFooter() {
        System.out.println("Кількість записів: " + items.size());
    }

    /** Повністю відображає всі результати. */
    @Override
    public void viewShow() {
        if (items.isEmpty()) {
            System.out.println("Колекція порожня.");
            return;
        }
        viewHeader();
        viewBody();
        viewFooter();
    }

    /** Зберігає колекцію у файл. */
    @Override
    public void viewSave() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(items);
        }
    }

    /** Відновлює колекцію з файлу. */
    @Override
    @SuppressWarnings("unchecked")
    public void viewRestore() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            items = (ArrayList<RoomItem>) in.readObject();
        }
    }
}
