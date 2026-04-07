package com.example;

import java.util.concurrent.TimeUnit;

/**
 * Обчислення середнього об'єму елементів колекції.
 */
public class AvgCommand implements Command {
    private double result;
    private int progress;
    private ViewResult viewResult;

    public AvgCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    public ViewResult getViewResult() {
        return viewResult;
    }

    public ViewResult setViewResult(ViewResult viewResult) {
        return this.viewResult = viewResult;
    }

    public double getResult() {
        return result;
    }

    public boolean running() {
        return progress < 100;
    }

    @Override
    public void execute() {
        progress = 0;
        if (viewResult.getItems().isEmpty()) {
            result = 0.0;
            progress = 100;
            System.out.println("Average: колекція порожня.");
            return;
        }
        System.out.println("Average executed...");
        result = 0.0;
        int idx = 0;
        int size = viewResult.getItems().size();
        for (RoomItem item : viewResult.getItems()) {
            result += item.getVolume();
            idx++;
            progress = idx * 100 / size;
            if (size >= 2 && idx % Math.max(1, size / 2) == 0) {
                System.out.println("Average " + progress + "%");
            }
            pause(size, 500);
        }
        result /= size;
        System.out.println("Average done. Result = " + String.format("%.2f", result));
        progress = 100;
    }

    private void pause(int size, long totalMs) {
        try {
            TimeUnit.MILLISECONDS.sleep(Math.max(1L, totalMs / Math.max(1, size)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
