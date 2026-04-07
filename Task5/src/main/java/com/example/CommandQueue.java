package com.example;

import java.util.LinkedList;

/**
 * Черга задач з окремим worker-потоком.
 */
public class CommandQueue implements Queue {
    private final LinkedList<Command> tasks = new LinkedList<>();
    private boolean shutdown;
    private final Thread workerThread;

    /**
     * Створює чергу та запускає worker-потік.
     */
    public CommandQueue() {
        workerThread = new Thread(new Worker(), "command-queue-worker");
        workerThread.setDaemon(true);
        workerThread.start();
    }

    /**
     * Завершує роботу черги після обробки поточних задач.
     */
    public synchronized void shutdown() {
        shutdown = true;
        notifyAll();
    }

    /**
     * Очікує завершення worker-потоку.
     *
     * @throws InterruptedException якщо очікування перервано
     */
    public void awaitTermination() throws InterruptedException {
        workerThread.join();
    }

    @Override
    public synchronized void put(Command cmd) {
        if (shutdown) {
            throw new IllegalStateException("Чергу вже завершено");
        }
        tasks.addLast(cmd);
        notifyAll();
    }

    @Override
    public synchronized Command take() {
        while (tasks.isEmpty() && !shutdown) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        if (tasks.isEmpty()) {
            return null;
        }
        return tasks.removeFirst();
    }

    /**
     * Обробник черги задач.
     */
    private class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                Command task = take();
                if (task == null) {
                    return;
                }
                task.execute();
            }
        }
    }
}
