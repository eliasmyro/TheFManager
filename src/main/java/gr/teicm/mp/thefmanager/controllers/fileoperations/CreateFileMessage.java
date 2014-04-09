package gr.teicm.mp.thefmanager.controllers.fileoperations;

import javax.swing.*;

/**
 * Created by Giota Z on 7/4/2014.
 */
public class CreateFileMessage implements IMessagePane{
    @Override
    public boolean showMessage() {
        JOptionPane myPane = new JOptionPane();
        int reply = myPane.showConfirmDialog(null, "Do you want to replace the existing file/folder ?", "File/Folder already exists.", JOptionPane.YES_NO_OPTION);
        if (reply == myPane.YES_OPTION) {
            return true;
        } else {
            myPane.getRootFrame().dispose();
            return false;
        }
    }
}
