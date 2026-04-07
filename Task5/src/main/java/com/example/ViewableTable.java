package com.example;

/**
 * ConcreteCreator для табличного подання.
 */
public class ViewableTable extends ViewableResult {
    @Override
    public View getView() {
        return new ViewTable();
    }
}
