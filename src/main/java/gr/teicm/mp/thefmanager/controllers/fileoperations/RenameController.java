package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;
import gr.teicm.mp.thefmanager.controllers.IMessageBox;
import gr.teicm.mp.thefmanager.controllers.MessageBox;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.ICommand;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.RenameCommand;
import gr.teicm.mp.thefmanager.controllers.history.GlobalCommandHistory;
import gr.teicm.mp.thefmanager.controllers.history.IHistory;

import java.io.File;
import java.util.regex.Pattern;

public class RenameController implements IRenameController {
    private IHistory<ICommand> globalCommandHistory;
    private IFileDAO fileDAO;
    private IMessageBox messageBox;

    private Pattern invalidChars = Pattern.compile("[\\\\/:*?\"<>|]");

    public RenameController() {
        globalCommandHistory = GlobalCommandHistory.getInstance();
        fileDAO = new FileDAO();
        messageBox = new MessageBox();
    }

    @Override
    public boolean perform(String location, String oldName, String newName) {
        String oldNamePath = location + File.separator + oldName;
        String newNamePath = location + File.separator + newName;
        boolean done = false;

        if (!oldNamePath.equals(newNamePath)) {
            if (invalidChars.matcher(newName).find() || newName.isEmpty()) {
                messageBox.showMessageDialog(
                        null,
                        "A filename can't contain any of the following characters \\/:*?\"<>| or be empty.",
                        "Invalid Name.",
                        MessageBox.ERROR_MESSAGE
                );
            } else if (fileDAO.exists(newNamePath)) {
                String msgText;
                String msgTitle;

                if (fileDAO.isFile(oldNamePath)) {
                    msgText = "File with this name already exists.";
                    msgTitle = "File was not renamed.";
                } else {
                    msgText = "Folder with this name already exists.";
                    msgTitle = "Folder was not renamed.";
                }

                messageBox.showMessageDialog(
                        null,
                        msgText,
                        msgTitle,
                        MessageBox.WARNING_MESSAGE
                );

                return false;
            } else {
                ICommand renameCommand = new RenameCommand(oldNamePath, newNamePath);

                try {
                    done = renameCommand.perform();
                    globalCommandHistory.add(renameCommand);
                } catch (Exception e) {
                    messageBox.showMessageDialog(
                            null,
                            "Something goes wrong.",
                            "Operation failed.",
                            MessageBox.ERROR_MESSAGE
                    );
                }
            }
        } else {
            done = true;
        }

        return done;
    }
}
