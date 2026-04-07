package com.example;

/**
 * Інтерфейс команди.
 */
public interface Command {
    void execute();

    default void undo() {
    }

    default boolean isUndoable() {
        return false;
    }
}
