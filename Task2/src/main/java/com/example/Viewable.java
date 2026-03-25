package com.example;

/**
 * Інтерфейс фабрикуючого методу.
 * <p>
 * Описує створення об'єкта типу {@link View}.
 * </p>
 */
public interface Viewable {

    /**
     * Створює і повертає об'єкт для відображення результатів.
     *
     * @return новий об'єкт типу View
     */
    View getView();
}