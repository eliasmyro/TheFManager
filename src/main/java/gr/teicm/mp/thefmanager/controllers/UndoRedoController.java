package gr.teicm.mp.thefmanager.controllers;

import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.ICommand;
import gr.teicm.mp.thefmanager.controllers.history.GlobalCommandHistory;
import gr.teicm.mp.thefmanager.controllers.history.IHistory;

public class UndoRedoController implements IUndoRedoController {
    private IHistory<ICommand> globalCommandHistory;

    public UndoRedoController() {
        globalCommandHistory = GlobalCommandHistory.getInstance();
    }

    @Override
    public boolean undo() {
        boolean done = false;

        ICommand command = globalCommandHistory.back();

        try {
            done = command.undo();
        } catch (Exception e) {
            e.getMessage();
        }

        return done;
    }

    @Override
    public boolean redo() {
        boolean done = false;

        ICommand command = globalCommandHistory.forward();

        try {
            done = command.redo();
        } catch (Exception e) {
            e.getMessage();
        }

        return done;
    }
}
