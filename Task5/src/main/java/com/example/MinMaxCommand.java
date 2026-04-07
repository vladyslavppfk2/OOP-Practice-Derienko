package com.example;

import java.util.concurrent.TimeUnit;

/**
 * Пошук мінімального та максимального об'єму в колекції.
 */
public class MinMaxCommand implements Command {
    private int resultMin = -1;
    private int resultMax = -1;
    private int progress;
    private ViewResult viewResult;

    public MinMaxCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    public ViewResult getViewResult() {
        return viewResult;
    }

    public ViewResult setViewResult(ViewResult viewResult) {
        return this.viewResult = viewResult;
    }

    public int getResultMin() {
        return resultMin;
    }

    public int getResultMax() {
        return resultMax;
    }

    public boolean running() {
        return progress < 100;
    }

    @Override
    public void execute() {
        progress = 0;
        if (viewResult.getItems().isEmpty()) {
            resultMin = -1;
            resultMax = -1;
            progress = 100;
            System.out.println("MinMax: колекція порожня.");
            return;
        }
        System.out.println("MinMax executed...");
        resultMin = 0;
        resultMax = 0;
        int idx = 0;
        int size = viewResult.getItems().size();
        for (RoomItem item : viewResult.getItems()) {
            if (item.getVolume() < viewResult.getItems().get(resultMin).getVolume()) {
                resultMin = idx;
            }
            if (item.getVolume() > viewResult.getItems().get(resultMax).getVolume()) {
                resultMax = idx;
            }
            idx++;
            progress = idx * 100 / size;
            if (size >= 5 && idx % Math.max(1, size / 5) == 0) {
                System.out.println("MinMax " + progress + "%");
            }
            pause(size, 700);
        }
        System.out.println("MinMax done. Min item #" + (resultMin + 1) + " volume = "
                + viewResult.getItems().get(resultMin).getVolume()
                + ". Max item #" + (resultMax + 1) + " volume = "
                + viewResult.getItems().get(resultMax).getVolume());
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
