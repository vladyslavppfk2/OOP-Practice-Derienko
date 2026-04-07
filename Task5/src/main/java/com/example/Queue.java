package com.example;

/**
 * Інтерфейс черги задач для шаблону Worker Thread.
 */
public interface Queue {
    /**
     * Додає задачу в чергу.
     *
     * @param cmd задача
     */
    void put(Command cmd);

    /**
     * Повертає наступну задачу з черги.
     *
     * @return задача або {@code null}, якщо чергу завершено
     */
    Command take();
}
