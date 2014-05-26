package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;
import gr.teicm.mp.thefmanager.controllers.IMessageBox;
import gr.teicm.mp.thefmanager.controllers.MessageBox;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.DeleteCommand;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.ICommand;
import gr.teicm.mp.thefmanager.controllers.history.GlobalCommandHistory;
import gr.teicm.mp.thefmanager.controllers.history.IHistory;

import java.io.File;

public class DeleteController implements IDeleteController {
    private IHistory<ICommand> globalCommandHistory;
    private IFileDAO fileDAO;
    private IMessageBox messageBox;

    public DeleteController() {
        globalCommandHistory = GlobalCommandHistory.getInstance();
        fileDAO = new FileDAO();
        messageBox = new MessageBox();
    }

    @Override
    public boolean perform(String location, String name) {
        boolean done = false;
        int confirmDialogResult;

        String path = location + File.separator + name;

        ICommand deleteCommand = new DeleteCommand(path);

        if (fileDAO.isFile(path)) {
            confirmDialogResult = messageBox.showConfirmDialog(
                    null,
                    "Do you want to delete this file?",
                    "Delete file.",
                    MessageBox.OK_CANCEL_OPTION,
                    MessageBox.QUESTION_MESSAGE
            );
        } else {
            confirmDialogResult = messageBox.showConfirmDialog(
                    null,
                    "Do you want to delete this folder?",
                    "Delete folder.",
                    MessageBox.OK_CANCEL_OPTION,
                    MessageBox.QUESTION_MESSAGE
            );
        }

        try {
            if (confirmDialogResult == MessageBox.OK_OPTION) {
                done = deleteCommand.perform();
                globalCommandHistory.add(deleteCommand);
            }
        } catch (Exception e) {
            messageBox.showMessageDialog(
                    null,
                    "Something goes wrong.",
                    "Operation failed.",
                    MessageBox.ERROR_MESSAGE
            );
        }

        return done;
    }
}
