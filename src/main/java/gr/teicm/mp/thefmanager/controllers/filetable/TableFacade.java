package gr.teicm.mp.thefmanager.controllers.filetable;

import gr.teicm.mp.thefmanager.DAO.IPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;
import gr.teicm.mp.thefmanager.models.filesystems.TableFileModel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreeModel;
import java.io.File;
import java.io.FileFilter;

/**
 * Created by Ilias on 1/4/2014.
 */
public class TableFacade {

    private String selectedFilePath;
    private File selectedFile;
    private TreeModel fileSystemModel;
    private FileFilter tableNodeFilter;
    public FileSystemView fileSystemView = FileSystemView.getFileSystemView();

    {
        IPreferencesDAO preferencesDAO = new PreferencesDAO();
        tableNodeFilter = preferencesDAO.getHiddenFilesPolicy(true);
    }

    public TableFacade() {
    }

    public TableFacade(String filePath) {

        this.selectedFilePath = filePath;

    }

    public File getSelectedTableFile(){

        try{
            selectedFile = new File(selectedFilePath);
            if(selectedFile.isFile())
                return selectedFile;
            else if(selectedFile.isDirectory())
                return selectedFile;
            else
                return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateFileTable(File node,JTable table) {
       // FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        TableFileModel fileObject = new TableFileModel(node.listFiles(tableNodeFilter));
        File[] allFiles;

        allFiles = fileObject.getFiles();

        try{
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }
            if(allFiles != null) {
                for (File file : allFiles) {
                    tableModel.addRow(new Object[]{fileSystemView.getSystemIcon(file),
                            file.getName(), file.getPath(),
                            file.length(),
                            file.lastModified(),
                            file.canRead(),
                            file.canWrite(),
                            file.canExecute(),
                            file.isDirectory(),
                            file.isFile()});
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
