package com.example;

/**
 * Конкретний клас фабрикуючого методу.
 * <p>
 * Створює об'єкт типу {@link ViewResult}.
 * </p>
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