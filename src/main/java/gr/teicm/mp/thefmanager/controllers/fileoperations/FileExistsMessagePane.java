package gr.teicm.mp.thefmanager.controllers.fileoperations;

import javax.swing.*;

/**
 * Created by Elias Myronidis on 7/4/2014.
 */
public class FileExistsMessagePane implements IFileExistsMessagePane {
    @Override
    public boolean fileExistsMsg() {
        JOptionPane myPane = new JOptionPane();
        int reply = myPane.showConfirmDialog(null, "There is a file with the same name! Do you want to overwrite it?", "Overwrite", JOptionPane.YES_NO_OPTION);
        if (reply == myPane.YES_OPTION) {
            return true;
        } else {
            myPane.getRootFrame().dispose();
            return false;
        }
    }
}
