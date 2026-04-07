package com.example;

/**
 * Команда масштабування одного елемента колекції.
 */
public class ChangeItemCommand implements Command {
    private final ViewResult view;
    private int itemIndex;
    private int factor;
    private RoomItem backup;

    public ChangeItemCommand(ViewResult view) {
        this.view = view;
    }

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public int getFactor() {
        return factor;
    }

    @Override
    public void execute() {
        backup = new RoomItem(view.getItems().get(itemIndex));
        view.scaleItem(itemIndex, factor);
    }

    @Override
    public void undo() {
        view.getItems().set(itemIndex, new RoomItem(backup));
        view.notifyObservers();
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}