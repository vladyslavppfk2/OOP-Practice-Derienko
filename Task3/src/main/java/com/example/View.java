package com.example;

import java.io.IOException;

/**
 * Інтерфейс відображуваного об'єкта.
 */
public interface View {

    /**
     * Ініціалізація або додавання нового елемента в колекцію.
     *
     * @param binaryLength двійкове значення довжини
     * @param binaryWidth двійкове значення ширини
     * @param binaryHeight двійкове значення висоти
     */
    void viewInit(String binaryLength, String binaryWidth, String binaryHeight);

    /** Виведення заголовка. */
    void viewHeader();

    /** Виведення основної частини. */
    void viewBody();

    /** Виведення завершальної частини. */
    void viewFooter();

    /** Повне відображення результатів. */
    void viewShow();

    /**
     * Збереження колекції у файл.
     *
     * @throws IOException якщо виникла помилка введення-виведення
     */
    void viewSave() throws IOException;

    /**
     * Відновлення колекції з файлу.
     *
     * @throws IOException якщо виникла помилка введення-виведення
     * @throws ClassNotFoundException якщо клас не знайдено
     */
    void viewRestore() throws IOException, ClassNotFoundException;
}
