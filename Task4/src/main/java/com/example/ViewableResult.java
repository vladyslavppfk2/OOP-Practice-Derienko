package com.example;

/**
 * ConcreteCreator для базового подання.
 */
public class ViewableResult implements Viewable {
    @Override
    public View getView() {
        return new ViewResult();
    }
}
