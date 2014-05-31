package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;
import gr.teicm.mp.thefmanager.controllers.IMessageBox;
import gr.teicm.mp.thefmanager.controllers.MessageBox;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.CopyCommand;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.DeleteCommand;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.ICommand;
import gr.teicm.mp.thefmanager.controllers.history.GlobalCommandHistory;
import gr.teicm.mp.thefmanager.controllers.history.IHistory;

import java.io.File;

public class CopyController implements ICopyController {
    private IHistory<ICommand> globalCommandHistory;
    private IFileDAO fileDAO;
    private IMessageBox messageBox;

    private String sourcePath;
    private String name;

    public CopyController() {
        globalCommandHistory = GlobalCommandHistory.getInstance();
        fileDAO = new FileDAO();
        messageBox = new MessageBox();

        sourcePath = null;
        name = null;
    }

    @Override
    public boolean setSource(String location, String name) {
        this.name = name;
        this.sourcePath = location + File.separator + name;

        return true;
    }



    @Override
    public boolean perform(String destinationLocationPath) {
        String destinationPath = destinationLocationPath + File.separator + name;
        boolean done = false;
        if (isReady()) {
            ICommand copyCommand = new CopyCommand(sourcePath, destinationLocationPath);
            int confirmDialogResult = MessageBox.NO_OPTION;

            if (fileDAO.exists(destinationPath)) {
                String msgText;
                String msgTitle;

                if (fileDAO.isFile(sourcePath)) {
                    msgText = "Do you want to replace the existing file?";
                    msgTitle = "File with this name already exists";
                } else {
                    msgText = "Do you want to replace the existing folder?";
                    msgTitle = "Folder with this name already exists";
                }

                confirmDialogResult = messageBox.showConfirmDialog(
                        null,
                        msgText,
                        msgTitle,
                        MessageBox.YES_NO_OPTION,
                        MessageBox.QUESTION_MESSAGE
                );
            }

            if ((!fileDAO.exists(destinationPath)) || (confirmDialogResult == MessageBox.YES_OPTION)) {
                try {
                    done = copyCommand.perform();
                    globalCommandHistory.add(copyCommand);
                } catch (Exception e) {
                    messageBox.showMessageDialog(
                            null,
                            "Something goes wrong.",
                            "Operation failed.",
                            MessageBox.ERROR_MESSAGE
                    );
                }
            }
        }

        return done;
    }

    @Override
    public boolean isReady() {
        if (sourcePath != null) {
            if (fileDAO.exists(sourcePath)) {
                return true;
            }
        }

        return false;
    }
}
