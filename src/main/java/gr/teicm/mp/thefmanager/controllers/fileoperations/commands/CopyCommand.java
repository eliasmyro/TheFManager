package gr.teicm.mp.thefmanager.controllers.fileoperations.commands;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;
import gr.teicm.mp.thefmanager.controllers.history.IHistory;
import gr.teicm.mp.thefmanager.controllers.history.LocalCommandHistory;

import java.io.File;

public class CopyCommand implements ICommand {
    private IHistory<ICommand> localCommandHistory;
    private ICommand deleteCommand;
    private IFileDAO fileDAO;

    private String source;
    private String destination;
    private String filePath;

    public CopyCommand(String source, String destination) {
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

        if (fileDAO.isFile(source)) {
            done = fileDAO.copyFile(source, destination);
        } else {
            return fileDAO.copyDirectory(source, destination);
        }

        return done;
    }

    @Override
    public boolean undo() throws Exception {
        boolean done = false;

        if (fileDAO.exists(filePath)) {
            done = deleteCommand.perform();
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
