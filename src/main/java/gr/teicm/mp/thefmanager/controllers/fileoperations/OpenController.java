package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;
import gr.teicm.mp.thefmanager.controllers.IMessageBox;
import gr.teicm.mp.thefmanager.controllers.MessageBox;

import java.awt.*;
import java.io.File;

public class OpenController implements IOpenController {
    private IFileDAO fileDAO;
    private IMessageBox messageBox;

    public OpenController() {
        fileDAO = new FileDAO();
        messageBox = new MessageBox();
    }

    @Override
    public boolean perform(String location, String name) {
        String path = location + File.separator + name;
        boolean opened = false;

        if (fileDAO.isDirectory(path)) {
            // TODO: Open in this file manager
            opened = true;
        } else {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File(path));
                    opened = true;
                }
            } catch (Exception e) {
                messageBox.showMessageDialog(
                        null,
                        "There is no App for this file or Desktop is not supported.",
                        "Operation failed.",
                        MessageBox.ERROR_MESSAGE
                );
            }
        }

        return opened;
    }
}
