package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Макрокоманда: виконує набір підкоманд.
 */
public class MacroConsoleCommand implements ConsoleCommand {
    private final List<Command> commands = new ArrayList<>();

    public MacroConsoleCommand add(Command command) {
        commands.add(command);
        return this;
    }

    @Override
    public char getKey() {
        return 'm';
    }

    @Override
    public String toString() {
        return "'m'acro demo";
    }

    @Override
    public void execute() {
        System.out.println("Виконання макрокоманди...");
        for (Command command : commands) {
            command.execute();
        }
        System.out.println("Макрокоманду завершено.");
    }
}
