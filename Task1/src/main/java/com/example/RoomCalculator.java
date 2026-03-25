package com.example;

import java.io.*;

/**
 * Клас RoomCalculator виконує всі обчислення.
 *
 * <p>
 * Використовується агрегування:
 * клас містить об'єкт {@link RoomData}, в якому зберігаються дані.
 * </p>
 */
public class RoomCalculator {

    /** Файл для збереження об'єкта */
    private static final String FILE_NAME = "roomdata.bin";

    /** Об'єкт даних */
    private RoomData data;

    public RoomCalculator() {
        data = new RoomData();
    }

    public RoomData getData() {
        return data;
    }

    /**
     * Перевірка чи є рядок двійковим числом
     */
    private boolean isBinary(String binary) {
        return binary.matches("[01]+");
    }

    /**
     * Перетворення двійкового числа у десяткове
     */
    private int binaryToDecimal(String binary) {
        if (!isBinary(binary)) {
            throw new IllegalArgumentException("Некоректне двійкове число: " + binary);
        }
        return Integer.parseInt(binary, 2);
    }

    /**
     * Ініціалізація даних і запуск обчислень
     */
    public void init(String bl, String bw, String bh) {

        // зберігаємо двійкові значення
        data.setBinaryLength(bl);
        data.setBinaryWidth(bw);
        data.setBinaryHeight(bh);

        // переводимо в десяткові
        int l = binaryToDecimal(bl);
        int w = binaryToDecimal(bw);
        int h = binaryToDecimal(bh);

        data.setLength(l);
        data.setWidth(w);
        data.setHeight(h);

        // обчислення
        data.setPerimeter(2 * (l + w));
        data.setArea(l * w);
        data.setVolume(l * w * h);

        data.setTransientNote("Останній розрахунок виконано");
    }

    /**
     * Серіалізація (збереження об'єкта у файл)
     */
    public void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        out.writeObject(data);
        out.close();
    }

    /**
     * Десеріалізація (відновлення об'єкта з файлу)
     */
    public void restore() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
        data = (RoomData) in.readObject();
        in.close();
    }

    /**
     * Вивід інформації
     */
    public void show() {
        System.out.println(data);
    }
}