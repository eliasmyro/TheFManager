package gr.teicm.mp.thefmanager.controllers;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Elias Myronidis on 26/5/2014.
 */
public interface IMediator extends ActionListener{
    void registerDeletePopMenu(JMenuItem fileTableItemPopupMenuDelete);
    void registerCopyPopMenu(JMenuItem fileTableItemPopupMenuCopy);
    void registerNewFolderMenu(JMenuItem fileTablePopupMenuNewFolder);
    void registerNewFileMenu(JMenuItem fileTablePopupMenuNewFile);
    void registerFileTable(JTable fileTable);
}
