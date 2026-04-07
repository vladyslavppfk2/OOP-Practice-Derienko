package com.example;

import java.util.List;
import java.util.ArrayList;

/**
 * ConcreteCreator для базового подання.
 */
public class ViewableResult implements Viewable {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public View getView() {
        return new ViewResult();
    }

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void notifyObservers(){
        for(Observer o: observers){
            o.update();
        }
    }
}