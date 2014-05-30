package gr.teicm.mp.thefmanager.controllers;

import gr.teicm.mp.thefmanager.controllers.fileoperations.CopyController;
import gr.teicm.mp.thefmanager.controllers.fileoperations.DeleteController;
import gr.teicm.mp.thefmanager.controllers.fileoperations.ICopyController;
import gr.teicm.mp.thefmanager.controllers.fileoperations.IDeleteController;
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
    JTable fileTable;

    IDeleteController deleteController;
    ICopyController copyController;
    TableFacade tableFacade;

    String currentLocationPath;
    String selectedTableItemName;
    String lastCopyOrCut;

    public Mediator() {
        deleteController = new DeleteController();
        tableFacade = new TableFacade();
        copyController = new CopyController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fileTableItemPopupMenuDelete){
            int selectedRow =  fileTable.getSelectedRow();

            currentLocationPath = (String) fileTable.getModel().getValueAt(selectedRow, 5);
            selectedTableItemName = (String) fileTable.getModel().getValueAt(selectedRow, 1);

            deleteController.perform(currentLocationPath, selectedTableItemName);
            tableFacade.updateFileTable(currentLocationPath, fileTable);
        } else if(e.getSource() == fileTableItemPopupMenuRename){
            fileTable.editCellAt(fileTable.getSelectedRow(), 1);
        } else if(e.getSource() == fileTableItemPopupMenuCopy) {
            copyController.setSource(currentLocationPath, selectedTableItemName);
            lastCopyOrCut = "Copy";
            System.out.println("egine megale");
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

}
