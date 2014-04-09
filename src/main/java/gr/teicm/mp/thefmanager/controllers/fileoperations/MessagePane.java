package gr.teicm.mp.thefmanager.controllers.fileoperations;

import javax.swing.*;

/**
 * Created by Elias Myronidis on 5/4/2014.
 */
public class MessagePane implements IMessagePane{


    @Override
    public boolean showMessage(String title, String message) {
        JOptionPane myPane = new JOptionPane();
        int reply = myPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == myPane.YES_OPTION) {
            return true;
        } else {
            myPane.getRootFrame().dispose();
            return false;
        }
    }

    @Override
    public boolean showCopyFileMessage() {
        JOptionPane myPane = new JOptionPane();
        int reply = myPane.showConfirmDialog(null, "Do you want to copy the selected file?", "Copy File", JOptionPane.YES_NO_OPTION);
        if (reply == myPane.YES_OPTION) {
            return true;
        } else {
            myPane.getRootFrame().dispose();
            return false;
        }
    }

    @Override
    public boolean showOverwriteFileMessage() {
        JOptionPane myPane = new JOptionPane();
        int reply = myPane.showConfirmDialog(null, "File Already Exists! Do you want to overwrite the file?", "Overwrite File", JOptionPane.YES_NO_OPTION);
        if (reply == myPane.YES_OPTION) {
            return true;
        } else {
            myPane.getRootFrame().dispose();
            return false;
        }
    }


}
