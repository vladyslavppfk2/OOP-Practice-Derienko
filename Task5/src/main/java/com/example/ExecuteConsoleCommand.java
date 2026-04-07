package com.example;

import java.util.concurrent.TimeUnit;

/**
 * Консольна команда запуску паралельної обробки колекції.
 */
public class ExecuteConsoleCommand implements ConsoleCommand {
    private View view;

    public ExecuteConsoleCommand(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public View setView(View view) {
        return this.view = view;
    }

    @Override
    public char getKey() {
        return 'p';
    }

    @Override
    public String toString() {
        return "'p'arallel stats";
    }

    @Override
    public void execute() {
        ViewResult viewResult = (ViewResult) view;
        if (viewResult.isEmpty()) {
            System.out.println("Колекція порожня. Спочатку додайте або згенеруйте елементи.");
            return;
        }

        CommandQueue queue1 = new CommandQueue();
        CommandQueue queue2 = new CommandQueue();

        MaxCommand maxCommand = new MaxCommand(viewResult);
        AvgCommand avgCommand = new AvgCommand(viewResult);
        MinMaxCommand minMaxCommand = new MinMaxCommand(viewResult);

        System.out.println("Запуск паралельної обробки...");
        queue1.put(minMaxCommand);
        queue2.put(maxCommand);
        queue2.put(avgCommand);

        try {
            while (avgCommand.running() || maxCommand.running() || minMaxCommand.running()) {
                TimeUnit.MILLISECONDS.sleep(50);
            }
            queue1.shutdown();
            queue2.shutdown();
            queue1.awaitTermination();
            queue2.awaitTermination();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Паралельне виконання перервано: " + e.getMessage());
            return;
        }

        System.out.println("Паралельну обробку завершено.");
        if (maxCommand.getResult() >= 0) {
            RoomItem max = viewResult.getItems().get(maxCommand.getResult());
            System.out.println("Максимальний об'єм: запис #" + (maxCommand.getResult() + 1)
                    + ", об'єм = " + max.getVolume());
        }
        System.out.println("Середній об'єм: " + String.format("%.2f", avgCommand.getResult()));
        if (minMaxCommand.getResultMin() >= 0 && minMaxCommand.getResultMax() >= 0) {
            RoomItem min = viewResult.getItems().get(minMaxCommand.getResultMin());
            RoomItem max = viewResult.getItems().get(minMaxCommand.getResultMax());
            System.out.println("Мінімальний об'єм: запис #" + (minMaxCommand.getResultMin() + 1)
                    + ", об'єм = " + min.getVolume());
            System.out.println("Максимальний об'єм (MinMax): запис #" + (minMaxCommand.getResultMax() + 1)
                    + ", об'єм = " + max.getVolume());
        }
    }
}
