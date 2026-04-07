package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Базове представлення колекції результатів.
 */
public class ViewResult implements View {
    private static final String FILE_NAME = "roomitems.bin";
    private final Random random = new Random();
    private ArrayList<RoomItem> items = new ArrayList<>();

    public ArrayList<RoomItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<RoomItem> items) {
        this.items = items;
    }

    /**
     * Створює глибоку копію колекції.
     */
    public ArrayList<RoomItem> copyItems() {
        ArrayList<RoomItem> copy = new ArrayList<>();
        for (RoomItem item : items) {
            copy.add(new RoomItem(item));
        }
        return copy;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    private boolean isBinary(String binary) {
        return binary != null && binary.matches("[01]+");
    }

    private int binaryToDecimal(String binary) {
        if (!isBinary(binary)) {
            throw new IllegalArgumentException("Некоректне двійкове число: " + binary);
        }
        return Integer.parseInt(binary, 2);
    }

    public int calcPerimeter(int length, int width) {
        return 2 * (length + width);
    }

    public int calcArea(int length, int width) {
        return length * width;
    }

    public int calcVolume(int length, int width, int height) {
        return length * width * height;
    }

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

    /**
     * Генерує випадковий елемент.
     */
    public void generateRandomItem() {
        int length = random.nextInt(15) + 1;
        int width = random.nextInt(15) + 1;
        int height = random.nextInt(10) + 1;
        viewInit(Integer.toBinaryString(length), Integer.toBinaryString(width), Integer.toBinaryString(height));
    }

    /**
     * Масштабує елемент за індексом.
     */
    public void scaleItem(int index, int factor) {
        if (index < 0 || index >= items.size()) {
            throw new IllegalArgumentException("Невірний індекс елемента: " + (index + 1));
        }
        if (factor <= 0) {
            throw new IllegalArgumentException("Коефіцієнт масштабування має бути > 0");
        }
        items.get(index).scale(factor);
    }

    /**
     * Масштабує всю колекцію.
     */
    public void scaleAll(int factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Коефіцієнт масштабування має бути > 0");
        }
        for (RoomItem item : items) {
            item.scale(factor);
        }
    }

    /**
     * Сортує колекцію за об'ємом.
     */
    public void sortByVolumeDesc() {
        items.sort(Comparator.comparingInt(RoomItem::getVolume).reversed());
    }

    /**
     * Повертає елемент з найбільшим об'ємом.
     */
    public RoomItem findMaxVolumeItem() {
        if (items.isEmpty()) {
            return null;
        }
        RoomItem max = items.get(0);
        for (RoomItem item : items) {
            if (item.getVolume() > max.getVolume()) {
                max = item;
            }
        }
        return max;
    }

    private static final String LINE =
            "--------------------------------------------------------------------------------------------------------------";

    @Override
    public void viewHeader() {
        System.out.println(LINE);
        System.out.printf("| %-2s | %-14s | %-14s | %-14s | %-7s | %-7s | %-7s | %-8s | %-6s | %-6s |%n",
                "№", "Двійк. довжина", "Двійк. ширина", "Двійк. висота", "Довж.", "Шир.", "Вис.", "Перим.", "Площа", "Об'єм");
        System.out.println(LINE);
    }

    @Override
    public void viewBody() {
        for (int i = 0; i < items.size(); i++) {
            RoomItem item = items.get(i);
            System.out.printf("| %-2d | %-14s | %-14s | %-14s | %-7d | %-7d | %-7d | %-8d | %-6d | %-6d |%n",
                    i + 1,
                    item.getBinaryLength(),
                    item.getBinaryWidth(),
                    item.getBinaryHeight(),
                    item.getLength(),
                    item.getWidth(),
                    item.getHeight(),
                    item.getPerimeter(),
                    item.getArea(),
                    item.getVolume());
        }
    }

    @Override
    public void viewFooter() {
        System.out.println(LINE);
        System.out.println("Кількість записів: " + items.size());
    }

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

    @Override
    public void viewSave() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(items);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void viewRestore() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            items = (ArrayList<RoomItem>) in.readObject();
        }
    }
}