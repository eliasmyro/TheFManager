package gr.teicm.mp.thefmanager.controllers;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Elias Myronidis on 26/5/2014.
 */
public interface IMediator extends ActionListener {
    void regFileTable(JTable fileTable);

    void regFileTableItemPopupMenuOpen(JMenuItem fileTableItemPopupMenuOpen);
    void regFileTablePopupMenuNewFolder(JMenuItem fileTablePopupMenuNewFolder);
    void regFileTablePopupMenuNewFile(JMenuItem fileTablePopupMenuNewFile);
    void regFileTableItemPopupMenuCopy(JMenuItem fileTableItemPopupMenuCopy);
    void regFileTableItemPopupMenuCut(JMenuItem fileTableItemPopupMenuCut);
    void regFileTablePopupMenuPaste(JMenuItem fileTablePopupMenuPaste);
    void regFileTableItemPopupMenuRename(JMenuItem fileTableItemPopupMenuRename);
    void regFileTableItemPopupMenuDelete(JMenuItem fileTableItemPopupMenuDelete);

    void regMainFileMenuNewFolder(JMenuItem mainFileMenuNewFolder);
    void regMainFileMenuNewFile(JMenuItem mainFileMenuNewFile);
    void regMainFileMenuPaste(JMenuItem mainFileMenuPaste);

    void regSettingsButton(JButton settingsButton);
}
