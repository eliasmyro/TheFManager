package gr.teicm.mp.thefmanager.controllers;

import gr.teicm.mp.thefmanager.controllers.fileoperations.*;
import gr.teicm.mp.thefmanager.controllers.filetable.TableFacade;
import gr.teicm.mp.thefmanager.gui.MainForm.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Elias Myronidis on 26/5/2014.
 */
public class Mediator implements IMediator {

    JMenuItem fileTableItemPopupMenuDelete;
    JMenuItem fileTableItemPopupMenuRename;
    JMenuItem fileTableItemPopupMenuCopy;
    JMenuItem fileTableItemPopupMenuCut;
    JMenuItem fileTablePopupMenuPaste;
    JMenuItem fileTablePopupMenuNewFolder;
    JMenuItem fileTablePopupMenuNewFile;
    JMenuItem mainFileMenuNewFile;
    JMenuItem mainFileMenuNewFolder;
    JTable fileTable;

    IDeleteController deleteController;
    ICopyController copyController;
    ICutController cutController;
    TableFacade tableFacade;
    ICreateDirectoryController createDirectoryController;
    ICreateFileController createFileController;
    String currentLocationPath;
    String selectedTableItemName;
    public String lastCopyOrCut;



    public Mediator() {
        deleteController = new DeleteController();
        tableFacade = new TableFacade();
        copyController = new CopyController();
        cutController = new CutController();
        createDirectoryController = new CreateDirectoryController();
        createFileController = new CreateFileController();
    }

    /* To currentLocationPath = (String) fileTable.getModel().getValueAt(selectedRow, 5);  prepi na bi mono sta ifs pou epilegeis row count, px to delete rename klp, giati alios evgaze -1 null rowcount
       (otan epelega px sto keno na kanw paste), episis ekana ta currentLocationPath tis mainform public, kai pernw apo eki to currentLocationPath kai selectedTableItemName gia na kanw ta copy paste cut
       Protinw tin Giota na kani to idio giati paratirisa oti kai ta dika tis den doulevoun, logo tou oti xriazete row count tou table kai diname null row count.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow=  fileTable.getSelectedRow();

        System.out.println("currentLocationPath"+ MainForm.currentLocationPath);
        System.out.println("selectedTableItemName"+ MainForm.selectedTableItemName);

        if(e.getSource() == fileTableItemPopupMenuDelete){
            currentLocationPath = (String) fileTable.getModel().getValueAt(selectedRow, 5);
            selectedTableItemName = (String) fileTable.getModel().getValueAt(selectedRow, 1);
            deleteController.perform(currentLocationPath, selectedTableItemName);
            tableFacade.updateFileTable(currentLocationPath, fileTable);
        } else if(e.getSource() == fileTableItemPopupMenuRename){
            fileTable.editCellAt(fileTable.getSelectedRow(), 1);
        } else if(e.getSource() == fileTableItemPopupMenuCopy) {
            copyController.setSource(MainForm.currentLocationPath, MainForm.selectedTableItemName);
            this.lastCopyOrCut = "Copy";

        }else if(e.getSource() == fileTableItemPopupMenuCut) {
            cutController.setSource(MainForm.currentLocationPath, MainForm.selectedTableItemName);
            this.lastCopyOrCut = "Cut";
        }else if(e.getSource() == fileTablePopupMenuPaste) {
            if (this.lastCopyOrCut != null) {
                if (this.lastCopyOrCut.equals("Copy")) {
                    copyController.perform(MainForm.currentLocationPath);
                } else if (this.lastCopyOrCut.equals("Cut")) {
                    cutController.perform(MainForm.currentLocationPath);
                    System.out.println("egine cut");
                }
            }
            tableFacade.updateFileTable(MainForm.currentLocationPath, fileTable);
        }
        else if(e.getSource() == mainFileMenuNewFolder) {
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

    @Override
    public void registerCutPopMenu(JMenuItem fileTableItemPopupMenuCut) {
        this.fileTableItemPopupMenuCut = fileTableItemPopupMenuCut;
    }

    @Override
    public void registerPastePopMenu(JMenuItem fileTablePopupMenuPaste) {
        this.fileTablePopupMenuPaste = fileTablePopupMenuPaste;
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
