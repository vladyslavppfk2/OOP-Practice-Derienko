package com.example;

import java.util.concurrent.TimeUnit;

/**
 * Пошук елемента з максимальним об'ємом.
 */
public class MaxCommand implements Command {
    private int result = -1;
    private int progress = 0;
    private ViewResult viewResult;

    public MaxCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    public ViewResult getViewResult() {
        return viewResult;
    }

    public ViewResult setViewResult(ViewResult viewResult) {
        return this.viewResult = viewResult;
    }

    public int getResult() {
        return result;
    }

    public boolean running() {
        return progress < 100;
    }

    @Override
    public void execute() {
        progress = 0;
        if (viewResult.getItems().isEmpty()) {
            result = -1;
            progress = 100;
            System.out.println("Max: колекція порожня.");
            return;
        }
        System.out.println("Max executed...");
        result = 0;
        int size = viewResult.getItems().size();
        for (int idx = 0; idx < size; idx++) {
            if (viewResult.getItems().get(idx).getVolume() > viewResult.getItems().get(result).getVolume()) {
                result = idx;
            }
            progress = (idx + 1) * 100 / size;
            if (size >= 3 && (idx + 1) % Math.max(1, size / 3) == 0) {
                System.out.println("Max " + progress + "%");
            }
            pause(size, 600);
        }
        RoomItem max = viewResult.getItems().get(result);
        System.out.println("Max done. Item #" + (result + 1) + " found: volume = " + max.getVolume());
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
