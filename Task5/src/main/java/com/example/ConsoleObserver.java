package com.example;

/**
 * Observer для вывода изменений коллекции в терминал.
 */
public class ConsoleObserver implements Observer {

    private final ViewResult view;

    public ConsoleObserver(ViewResult view) {
        this.view = view;
    }

    @Override
    public void update() {
        System.out.println("\n[Collection updated]");
        view.viewShow();
    }
}