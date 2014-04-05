package gr.teicm.mp.thefmanager.controllers.fileoperations;

import javax.swing.*;

/**
 * Created by Elias Myronidis on 5/4/2014.
 */
public class MessagePane implements IMessagePane{


    @Override
    public boolean showMessage() {
        JOptionPane myPane = new JOptionPane();
        int reply = myPane.showConfirmDialog(null, "Do you want to delete the selected file?", "Delete File", JOptionPane.YES_NO_OPTION);
        if (reply == myPane.YES_OPTION) {
            return true;
        } else {
            myPane.getRootFrame().dispose();
            return false;
        }
    }
}
