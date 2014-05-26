package gr.teicm.mp.thefmanager.controllers.fileoperations.commands;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;

public class RenameCommand implements ICommand {
    private IFileDAO fileDAO;

    private String oldNamePath;
    private String newNamePath;

    public RenameCommand(String oldNamePath, String newNamePath) {
        fileDAO = new FileDAO();

        this.oldNamePath = oldNamePath;
        this.newNamePath = newNamePath;
    }

    @Override
    public boolean perform() throws Exception {
        boolean done;

        if (fileDAO.isFile(oldNamePath)) {
            done = fileDAO.renameFile(oldNamePath, newNamePath);
        } else {
            done = fileDAO.renameDirectory(oldNamePath, newNamePath);
        }

        return done;
    }

    @Override
    public boolean undo() throws Exception {
        boolean done;

        if (fileDAO.isFile(oldNamePath)) {
            done = fileDAO.renameFile(newNamePath, oldNamePath);
        } else {
            done = fileDAO.renameDirectory(newNamePath, oldNamePath);
        }

        return done;
    }

    @Override
    public boolean redo() throws Exception {
        return perform();
    }
}
