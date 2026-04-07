package com.example;

import javax.swing.table.AbstractTableModel;

/**
 * Модель таблиці для відображення RoomItem у GUI.
 */
public class TableModel extends AbstractTableModel {

    private final ViewResult view;

    private final String[] columns = {
            "Binary Length", "Binary Width", "Binary Height",
            "Length", "Width", "Height",
            "Perimeter", "Area", "Volume"
    };

    public TableModel(ViewResult view) {
        this.view = view;
    }

    @Override
    public int getRowCount() {
        return view.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RoomItem item = view.getItems().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return item.getBinaryLength();
            case 1:
                return item.getBinaryWidth();
            case 2:
                return item.getBinaryHeight();
            case 3:
                return item.getLength();
            case 4:
                return item.getWidth();
            case 5:
                return item.getHeight();
            case 6:
                return item.getPerimeter();
            case 7:
                return item.getArea();
            case 8:
                return item.getVolume();
            default:
                return "";
        }
    }

    public void refresh() {
        fireTableDataChanged();
    }
}