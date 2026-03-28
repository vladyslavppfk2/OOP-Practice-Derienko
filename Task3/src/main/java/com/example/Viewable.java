package com.example;

/**
 * Інтерфейс фабрикуючого методу.
 */
public interface Viewable {

    /**
     * Створює і повертає об'єкт для відображення результатів.
     *
     * @return новий об'єкт типу View
     */
    View getView();
}
