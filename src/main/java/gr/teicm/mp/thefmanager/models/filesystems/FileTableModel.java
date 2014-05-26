package gr.teicm.mp.thefmanager.models.filesystems;

import gr.teicm.mp.thefmanager.controllers.fileoperations.IRenameController;
import gr.teicm.mp.thefmanager.controllers.fileoperations.RenameController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FileTableModel extends AbstractTableModel {
    private String[] columnNames = {
            "",
            "Filename",
            "Size",
            "Type",
            "Modified"
    };

    private List<Object[]> data;

    public FileTableModel() {
        data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        String oldValue = (String) data.get(rowIndex)[columnIndex];
        String newValue = (String) value;
        String location = (String) getValueAt(rowIndex, 5);

        IRenameController renameController = new RenameController();
        boolean done = renameController.perform(location, oldValue, newValue);

        if (done) {
            data.get(rowIndex)[columnIndex] = newValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    public void addRow(Object[] rowData) {
        data.add(rowData);
        this.fireTableDataChanged();
    }
}
