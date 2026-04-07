package com.example;

import java.util.ArrayList;

/**
 * Допоміжний запис для збереження стану колекції.
 */
public record ArrayListSnapshot(ArrayList<RoomItem> items) {
}

