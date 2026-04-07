package com.example;

import java.io.IOException;

/**
 * Інтерфейс об'єкта відображення.
 */
public interface View {
    void viewInit(String binaryLength, String binaryWidth, String binaryHeight);
    void viewHeader();
    void viewBody();
    void viewFooter();
    void viewShow();
    void viewSave() throws IOException;
    void viewRestore() throws IOException, ClassNotFoundException;
}
