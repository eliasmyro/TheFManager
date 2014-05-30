package gr.teicm.mp.thefmanager.controllers;

import gr.teicm.mp.thefmanager.controllers.fileoperations.*;
import gr.teicm.mp.thefmanager.controllers.filetable.TableFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Elias Myronidis on 26/5/2014.
 */
public class Mediator implements IMediator {

    JMenuItem fileTableItemPopupMenuDelete;
    JMenuItem fileTableItemPopupMenuRename;
    JMenuItem fileTableItemPopupMenuCopy;
    JMenuItem fileTablePopupMenuNewFolder;
    JMenuItem fileTablePopupMenuNewFile;
    JMenuItem mainFileMenuNewFile;
    JMenuItem mainFileMenuNewFolder;
    JTable fileTable;

    IDeleteController deleteController;
    ICopyController copyController;
    TableFacade tableFacade;
    ICreateDirectoryController createDirectoryController;
    ICreateFileController createFileController;
    String currentLocationPath;
    String selectedTableItemName;
    String lastCopyOrCut;

    public Mediator() {
        deleteController = new DeleteController();
        tableFacade = new TableFacade();
        copyController = new CopyController();
        createDirectoryController = new CreateDirectoryController();
        createFileController = new CreateFileController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow=  fileTable.getSelectedRow();
        currentLocationPath = (String) fileTable.getModel().getValueAt(selectedRow, 5);
        if(e.getSource() == fileTableItemPopupMenuDelete){
            selectedTableItemName = (String) fileTable.getModel().getValueAt(selectedRow, 1);
            deleteController.perform(currentLocationPath, selectedTableItemName);
            tableFacade.updateFileTable(currentLocationPath, fileTable);
        } else if(e.getSource() == fileTableItemPopupMenuRename){
            fileTable.editCellAt(fileTable.getSelectedRow(), 1);
        } else if(e.getSource() == fileTableItemPopupMenuCopy) {
            copyController.setSource(currentLocationPath, selectedTableItemName);
            lastCopyOrCut = "Copy";
            System.out.println("egine megale");
        }else if(e.getSource() == mainFileMenuNewFolder) {
            createDirectoryController.perform(currentLocationPath);
            tableFacade.updateFileTable(currentLocationPath, fileTable);
        }else if(e.getSource() == mainFileMenuNewFile) {
            createFileController.perform(currentLocationPath);
            tableFacade.updateFileTable(currentLocationPath, fileTable);
        }

    }

    @Override
     public void registerDeletePopMenu(JMenuItem fileTableItemPopupMenuDelete) {
        this.fileTableItemPopupMenuDelete = fileTableItemPopupMenuDelete;
    }

    @Override
    public void registerCopyPopMenu(JMenuItem fileTableItemPopupMenuCopy) {
        this.fileTableItemPopupMenuCopy = fileTableItemPopupMenuCopy;
    }

    public void registerFileTable(JTable fileTable){
        this.fileTable = fileTable;
    }

    @Override
    public void registerNewFolderMenu(JMenuItem mainFileMenuNewFolder) {
        this.mainFileMenuNewFolder = mainFileMenuNewFolder;
    }

    @Override
    public void registerNewFileMenu(JMenuItem mainFileMenuNewFile) {
        this.mainFileMenuNewFile = mainFileMenuNewFile;
    }
}
