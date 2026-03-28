package com.example;

/**
 * Конкретний клас фабричного методу для стандартного відображення.
 */
public class ViewableResult implements Viewable {

    /**
     * Створює новий об'єкт для відображення результатів.
     *
     * @return об'єкт типу View
     */
    @Override
    public View getView() {
        return new ViewResult();
    }
}
