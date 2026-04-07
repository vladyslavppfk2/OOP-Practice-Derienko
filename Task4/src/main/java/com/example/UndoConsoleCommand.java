package com.example;

/**
 * Команда скасування останньої операції.
 */
public class UndoConsoleCommand implements ConsoleCommand {
    @Override
    public char getKey() {
        return 'u';
    }

    @Override
    public String toString() {
        return "'u'ndo";
    }

    @Override
    public void execute() {
        Application.getInstance().undoLast();
    }
}
