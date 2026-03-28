package com.example;

/**
 * ConcreteCreator шаблону Factory Method.
 * <p>
 * Створює об'єкти типу {@link ViewTable}.
 * </p>
 */
public class ViewableTable extends ViewableResult {

    /**
     * Створює табличне подання результатів.
     *
     * @return об'єкт типу ViewTable
     */
    @Override
    public View getView() {
        return new ViewTable();
    }
}
