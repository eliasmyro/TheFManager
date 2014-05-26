package gr.teicm.mp.thefmanager.controllers.filetable;

import gr.teicm.mp.thefmanager.DAO.IUserPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.UserPreferencesDAO;
import gr.teicm.mp.thefmanager.models.filesystems.FileTableModel;
import gr.teicm.mp.thefmanager.models.filesystems.FileTableRow;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

public class TableFacade {
    private FileFilter tableNodeFilter;
    private FileTableRow fileTableRow;

    public TableFacade() {
        fileTableRow = null;

        IUserPreferencesDAO userPreferencesDAO = new UserPreferencesDAO();
        this.tableNodeFilter = userPreferencesDAO.getHiddenFilesPolicy(true);
    }

    public void updateFileTable(String parent, JTable table) {
        File parentDirectory = new File(parent);

        FileTableModel tableModel = new FileTableModel();

        File[] children = parentDirectory.listFiles(tableNodeFilter);

        if (children != null) {
            for (File file : children) {
                fileTableRow = new FileTableRow(file);
                tableModel.addRow(fileTableRow.getRow());
            }
        }

        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setMinWidth(30);
        table.getColumnModel().getColumn(0).setMaxWidth(30);
    }
}
