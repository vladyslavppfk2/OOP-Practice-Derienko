package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Singleton-клас програми.
 */
public class Application {
    private static final Application INSTANCE = new Application();

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final ViewResult view = (ViewResult) new ViewableTable().getView();
    private final Menu menu = new Menu();
    private final Deque<Command> history = new ArrayDeque<>();

    private Application() {
    }

    public static Application getInstance() {
        return INSTANCE;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public ViewResult getView() {
        return view;
    }

    public void pushHistory(Command command) {
        history.push(command);
    }

    public void undoLast() {
        if (history.isEmpty()) {
            System.out.println("Немає операцій для скасування.");
            return;
        }
        Command command = history.pop();
        command.undo();
        System.out.println("Останню операцію скасовано.");
        view.viewShow();
    }

    public void run() {
        ConsoleCommand generate = new GenerateConsoleCommand(view);
        ConsoleCommand viewCommand = new ViewConsoleCommand(view);
        ConsoleCommand changeAll = new ChangeConsoleCommand(view);

        MacroConsoleCommand macro = new MacroConsoleCommand()
                .add(generate)
                .add(changeAll)
                .add(viewCommand);

        menu.add(new AddItemConsoleCommand(view));
        menu.add(viewCommand);
        menu.add(generate);
        menu.add(new ChangeOneItemConsoleCommand(view));
        menu.add(changeAll);
        menu.add(new SortConsoleCommand(view));
        menu.add(new FindMaxConsoleCommand(view));
        menu.add(new ExecuteConsoleCommand(view));
        menu.add(new SaveConsoleCommand(view));
        menu.add(new RestoreConsoleCommand(view));
        menu.add(new UndoConsoleCommand());
        menu.add(macro);
        menu.execute();
    }
}
