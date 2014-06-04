package gr.teicm.mp.thefmanager.controllers;

import gr.teicm.mp.thefmanager.controllers.fileoperations.*;
import gr.teicm.mp.thefmanager.controllers.filetable.TableFacade;
import gr.teicm.mp.thefmanager.gui.MainForm.MainForm;
import gr.teicm.mp.thefmanager.gui.PreferencesForm.PreferencesForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created by Elias Myronidis on 26/5/2014.
 */
public class Mediator implements IMediator {
    JMenuItem fileTableItemPopupMenuOpen;
    JMenuItem fileTablePopupMenuNewFolder;
    JMenuItem fileTablePopupMenuNewFile;
    JMenuItem fileTableItemPopupMenuCopy;
    JMenuItem fileTableItemPopupMenuCut;
    JMenuItem fileTablePopupMenuPaste;
    JMenuItem fileTableItemPopupMenuRename;
    JMenuItem fileTableItemPopupMenuDelete;

    JMenuItem mainFileMenuNewFolder;
    JMenuItem mainFileMenuNewFile;
    JMenuItem mainFileMenuPaste;

    JTable fileTable;
    JButton settingsButton;

    TableFacade tableFacade;

    IOpenController openController;
    ICreateDirectoryController createDirectoryController;
    ICreateFileController createFileController;
    ICopyCutController copyCutController;
    IDeleteController deleteController;

    String currentLocationPath;
    String selectedTableItemName;

    public Mediator() {
        tableFacade = new TableFacade();

        openController = new OpenController();
        createDirectoryController = new CreateDirectoryController();
        createFileController = new CreateFileController();
        deleteController = new DeleteController();
    }

    /* To currentLocationPath = (String) fileTable.getModel().getValueAt(selectedRow, 5);  prepi na bi mono sta ifs pou epilegeis row count, px to delete rename klp, giati alios evgaze -1 null rowcount
       (otan epelega px sto keno na kanw paste), episis ekana ta currentLocationPath tis mainform public, kai pernw apo eki to currentLocationPath kai selectedTableItemName gia na kanw ta copy paste cut
       Protinw tin Giota na kani to idio giati paratirisa oti kai ta dika tis den doulevoun, logo tou oti xriazete row count tou table kai diname null row count.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = fileTable.getSelectedRow();

        if (e.getSource() == fileTableItemPopupMenuOpen) {
//            currentLocationPath = (String) fileTable.getModel().getValueAt(selectedRow, 5);
//            selectedTableItemName = (String) fileTable.getModel().getValueAt(selectedRow, 1);
            openController.perform(MainForm.currentLocationPath, MainForm.selectedTableItemName);
        } else if ((e.getSource() == fileTablePopupMenuNewFolder) || (e.getSource() == mainFileMenuNewFolder)) {
//            currentLocationPath = (String) fileTable.getModel().getValueAt(selectedRow, 5);
            createDirectoryController.perform(MainForm.currentLocationPath);
            tableFacade.updateFileTable(MainForm.currentLocationPath, fileTable);
        } else if ((e.getSource() == fileTablePopupMenuNewFile) || (e.getSource() == mainFileMenuNewFile)) {
//            currentLocationPath = (String) fileTable.getModel().getValueAt(selectedRow, 5);
            createFileController.perform(currentLocationPath);
            tableFacade.updateFileTable(currentLocationPath, fileTable);
        } else if (e.getSource() == fileTableItemPopupMenuCopy) {
            copyCutController = new CopyController();
            copyCutController.setSource(MainForm.currentLocationPath, MainForm.selectedTableItemName);
        } else if (e.getSource() == fileTableItemPopupMenuCut) {
            copyCutController = new CutController();
            copyCutController.setSource(MainForm.currentLocationPath, MainForm.selectedTableItemName);
        } else if (e.getSource() == fileTablePopupMenuPaste) {
            copyCutController.perform(MainForm.currentLocationPath);
            tableFacade.updateFileTable(MainForm.currentLocationPath, fileTable);
        } else if (e.getSource() == fileTableItemPopupMenuRename) {
            fileTable.editCellAt(fileTable.getSelectedRow(), 1);
        } else if (e.getSource() == fileTableItemPopupMenuDelete) {
            currentLocationPath = (String) fileTable.getModel().getValueAt(selectedRow, 5);
            selectedTableItemName = (String) fileTable.getModel().getValueAt(selectedRow, 1);
            System.out.println(currentLocationPath + File.separator + selectedTableItemName);
            deleteController.perform(currentLocationPath, selectedTableItemName);
            tableFacade.updateFileTable(currentLocationPath, fileTable);
        } else if (e.getSource() == settingsButton) {
            PreferencesForm preferencesForm = new PreferencesForm();
            preferencesForm.setVisible(true);
        }
    }

    @Override
    public void regFileTable(JTable fileTable) {
        this.fileTable = fileTable;
    }

    @Override
    public void regFileTableItemPopupMenuOpen(JMenuItem fileTableItemPopupMenuOpen) {
        this.fileTableItemPopupMenuOpen = fileTableItemPopupMenuOpen;
    }

    @Override
    public void regFileTablePopupMenuNewFolder(JMenuItem fileTableItemPopupMenuNewFolder) {
        this.fileTablePopupMenuNewFolder = fileTableItemPopupMenuNewFolder;
    }

    @Override
    public void regFileTablePopupMenuNewFile(JMenuItem fileTableItemPopupMenuNewFile) {
        this.fileTablePopupMenuNewFile = fileTableItemPopupMenuNewFile;
    }

    @Override
    public void regFileTableItemPopupMenuCopy(JMenuItem fileTableItemPopupMenuCopy) {
        this.fileTableItemPopupMenuCopy = fileTableItemPopupMenuCopy;
    }

    @Override
    public void regFileTableItemPopupMenuCut(JMenuItem fileTableItemPopupMenuCut) {
        this.fileTableItemPopupMenuCut = fileTableItemPopupMenuCut;
    }

    @Override
    public void regFileTablePopupMenuPaste(JMenuItem fileTablePopupMenuPaste) {
        this.fileTablePopupMenuPaste = fileTablePopupMenuPaste;
    }

    @Override
    public void regFileTableItemPopupMenuRename(JMenuItem fileTableItemPopupMenuDelete) {
        this.fileTableItemPopupMenuRename = fileTableItemPopupMenuDelete;
    }

    @Override
    public void regFileTableItemPopupMenuDelete(JMenuItem fileTableItemPopupMenuDelete) {
        this.fileTableItemPopupMenuDelete = fileTableItemPopupMenuDelete;
    }

    @Override
    public void regMainFileMenuNewFolder(JMenuItem mainFileMenuNewFolder) {
        this.mainFileMenuNewFolder = mainFileMenuNewFolder;
    }

    @Override
    public void regMainFileMenuNewFile(JMenuItem mainFileMenuNewFile) {
        this.mainFileMenuNewFile = mainFileMenuNewFile;
    }

    @Override
    public void regMainFileMenuPaste(JMenuItem mainFileMenuPaste) {
        this.mainFileMenuPaste = mainFileMenuPaste;
    }

    @Override
    public void regSettingsButton(JButton settingsButton) {
        this.settingsButton = settingsButton;
    }
}
