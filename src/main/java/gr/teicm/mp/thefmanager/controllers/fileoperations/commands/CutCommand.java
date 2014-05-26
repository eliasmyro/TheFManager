package gr.teicm.mp.thefmanager.controllers.fileoperations.commands;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;
import gr.teicm.mp.thefmanager.controllers.history.IHistory;
import gr.teicm.mp.thefmanager.controllers.history.LocalCommandHistory;

import java.io.File;

public class CutCommand implements ICommand {
    private IHistory<ICommand> localCommandHistory;
    private ICommand deleteCommand;
    private IFileDAO fileDAO;

    private String source;
    private String destination;
    private String filePath;

    public CutCommand(String source, String destination) {
        this.filePath = destination + File.separator + new File(source).getName();

        localCommandHistory = new LocalCommandHistory();

        deleteCommand = new DeleteCommand(filePath);
        fileDAO = new FileDAO();

        this.source = source;
        this.destination = destination;
    }

    @Override
    public boolean perform() throws Exception {
        boolean done = false;

        if (fileDAO.exists(filePath)) {
            deleteCommand.perform();
            localCommandHistory.add(deleteCommand);
        }

        ICommand deleteCommand = new DeleteCommand(source);

        if (fileDAO.isFile(source)) {
            boolean copyDone = fileDAO.copyFile(source, destination);
            boolean deleteDone = deleteCommand.perform();

            done = (copyDone && deleteDone);
        } else {
            boolean copyDone = fileDAO.copyDirectory(source, destination);
            boolean deleteDone = deleteCommand.perform();

            done = (copyDone && deleteDone);
        }

        return done;
    }

    @Override
    public boolean undo() throws Exception {
        boolean done = false;

        ICommand copyCommand = new CopyCommand(filePath, new File(source).getParent());

        if (fileDAO.exists(filePath)) {
            boolean copyDone = copyCommand.perform();
            boolean deleteDone = deleteCommand.perform();

            done = (copyDone && deleteDone);
        }

        while (localCommandHistory.hasCurrent()) {
            localCommandHistory.back().undo();
        }

        return done;
    }

    @Override
    public boolean redo() throws Exception {
        return perform();
    }
}
