package com.example;

import javax.swing.*;
import java.awt.*;

/**
 * Головне GUI-вікно.
 * Містить таблицю, статистику та кнопки керування.
 */
public class MainFrame extends JFrame implements Observer {

    private final ViewResult view;
    private final TableModel model;

    private final JTable table;
    private final JLabel countLabel;
    private final JLabel maxLabel;
    private final JLabel avgLabel;

    public MainFrame(ViewResult view) {
        this.view = view;

        setTitle("Room Collection");
        setSize(1000, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new TableModel(view);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        countLabel = new JLabel("Count: 0");
        maxLabel = new JLabel("Max volume: 0");
        avgLabel = new JLabel("Avg volume: 0.00");

        statsPanel.add(countLabel);
        statsPanel.add(maxLabel);
        statsPanel.add(avgLabel);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Add");
        JButton generateButton = new JButton("Generate");
        JButton changeItemButton = new JButton("Change Item");
        JButton scaleAllButton = new JButton("Change All");
        JButton sortButton = new JButton("Sort");
        JButton maxButton = new JButton("Find Max");
        JButton saveButton = new JButton("Save");
        JButton restoreButton = new JButton("Restore");
        JButton undoButton = new JButton("Undo");
        JButton macroButton = new JButton("Macro");

        buttonsPanel.add(addButton);
        buttonsPanel.add(generateButton);
        buttonsPanel.add(changeItemButton);
        buttonsPanel.add(scaleAllButton);
        buttonsPanel.add(sortButton);
        buttonsPanel.add(maxButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(restoreButton);
        buttonsPanel.add(undoButton);
        buttonsPanel.add(macroButton);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(statsPanel, BorderLayout.NORTH);
        southPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addItem());
        generateButton.addActionListener(e -> generateItem());
        changeItemButton.addActionListener(e -> changeItem());
        scaleAllButton.addActionListener(e -> scaleAll());
        sortButton.addActionListener(e -> sortItems());
        maxButton.addActionListener(e -> showMax());
        saveButton.addActionListener(e -> saveItems());
        restoreButton.addActionListener(e -> restoreItems());
        undoButton.addActionListener(e -> Application.getInstance().undoLast());
        macroButton.addActionListener(e -> runMacro());

        update();
        setVisible(true);
    }

    private void addItem() {
        String length = JOptionPane.showInputDialog(this, "Binary length:");
        if (length == null) {
            return;
        }

        String width = JOptionPane.showInputDialog(this, "Binary width:");
        if (width == null) {
            return;
        }

        String height = JOptionPane.showInputDialog(this, "Binary height:");
        if (height == null) {
            return;
        }

        ArrayListSnapshot backup = new ArrayListSnapshot(view.copyItems());

        try {
            view.viewInit(length, width, height);

            Command guiCommand = new Command() {
                @Override
                public void execute() {
                }

                @Override
                public void undo() {
                    view.setItems(backup.items());
                }

                @Override
                public boolean isUndoable() {
                    return true;
                }
            };

            Application.getInstance().pushHistory(guiCommand);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateItem() {
        ArrayListSnapshot backup = new ArrayListSnapshot(view.copyItems());

        try {
            view.generateRandomItem();

            Command guiCommand = new Command() {
                @Override
                public void execute() {
                }

                @Override
                public void undo() {
                    view.setItems(backup.items());
                }

                @Override
                public boolean isUndoable() {
                    return true;
                }
            };

            Application.getInstance().pushHistory(guiCommand);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeItem() {
        if (view.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Collection is empty.");
            return;
        }

        String indexInput = JOptionPane.showInputDialog(this,
                "Item number (from 1 to " + view.getItems().size() + "):");
        if (indexInput == null) {
            return;
        }

        String factorInput = JOptionPane.showInputDialog(this, "Scale factor:");
        if (factorInput == null) {
            return;
        }

        try {
            int index = Integer.parseInt(indexInput) - 1;
            int factor = Integer.parseInt(factorInput);

            ChangeItemCommand command = new ChangeItemCommand(view);
            command.setItemIndex(index);
            command.setFactor(factor);
            command.execute();

            Application.getInstance().pushHistory(command);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void scaleAll() {
        String input = JOptionPane.showInputDialog(this, "Scale factor:");
        if (input == null) {
            return;
        }

        ArrayListSnapshot backup = new ArrayListSnapshot(view.copyItems());

        try {
            int factor = Integer.parseInt(input);
            view.scaleAll(factor);

            Command guiCommand = new Command() {
                @Override
                public void execute() {
                }

                @Override
                public void undo() {
                    view.setItems(backup.items());
                }

                @Override
                public boolean isUndoable() {
                    return true;
                }
            };

            Application.getInstance().pushHistory(guiCommand);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortItems() {
        ArrayListSnapshot backup = new ArrayListSnapshot(view.copyItems());

        try {
            view.sortByVolumeDesc();

            Command guiCommand = new Command() {
                @Override
                public void execute() {
                }

                @Override
                public void undo() {
                    view.setItems(backup.items());
                }

                @Override
                public boolean isUndoable() {
                    return true;
                }
            };

            Application.getInstance().pushHistory(guiCommand);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMax() {
        RoomItem max = view.findMaxVolumeItem();
        if (max == null) {
            JOptionPane.showMessageDialog(this, "Collection is empty.");
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Length = " + max.getLength()
                        + "\nWidth = " + max.getWidth()
                        + "\nHeight = " + max.getHeight()
                        + "\nVolume = " + max.getVolume(),
                "Max Volume Item",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void saveItems() {
        try {
            view.viewSave();
            JOptionPane.showMessageDialog(this, "Collection saved.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void restoreItems() {
        ArrayListSnapshot backup = new ArrayListSnapshot(view.copyItems());

        try {
            view.viewRestore();

            Command guiCommand = new Command() {
                @Override
                public void execute() {
                }

                @Override
                public void undo() {
                    view.setItems(backup.items());
                }

                @Override
                public boolean isUndoable() {
                    return true;
                }
            };

            Application.getInstance().pushHistory(guiCommand);
            JOptionPane.showMessageDialog(this, "Collection restored.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void runMacro() {
        ArrayListSnapshot backup = new ArrayListSnapshot(view.copyItems());

        try {
            view.generateRandomItem();
            view.scaleAll(2);
            view.sortByVolumeDesc();

            Command guiCommand = new Command() {
                @Override
                public void execute() {
                }

                @Override
                public void undo() {
                    view.setItems(backup.items());
                }

                @Override
                public boolean isUndoable() {
                    return true;
                }
            };

            Application.getInstance().pushHistory(guiCommand);

            JOptionPane.showMessageDialog(this,
                    "Macro executed:\n1) Generate\n2) Change All x2\n3) Sort");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update() {
        model.refresh();

        int count = view.getItems().size();
        int max = view.getItems().stream()
                .mapToInt(RoomItem::getVolume)
                .max()
                .orElse(0);

        double avg = view.getItems().stream()
                .mapToInt(RoomItem::getVolume)
                .average()
                .orElse(0);

        countLabel.setText("Count: " + count);
        maxLabel.setText("Max volume: " + max);
        avgLabel.setText(String.format("Avg volume: %.2f", avg));
    }
}