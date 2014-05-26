package gr.teicm.mp.thefmanager.controllers.history;

import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.ICommand;

import java.util.ArrayList;
import java.util.List;

public class GlobalCommandHistory implements IHistory<ICommand> {
    private static GlobalCommandHistory instance = null;

    private List<ICommand> history;
    private int curr;
    private int last;

    protected GlobalCommandHistory() {
        history = new ArrayList<>();
        curr = -1;
        last = -1;
    }

    public static GlobalCommandHistory getInstance() {
        if (instance == null) {
            instance = new GlobalCommandHistory();
        }

        return instance;
    }

    public void add(ICommand item) {
        history.add(++curr, item);
        last = curr;
    }

    public ICommand back() {
        return hasCurrent() ? history.get(curr--) : null;
    }

    public ICommand forward() {
        return hasForward() ? history.get(++curr) : null;
    }

    public boolean hasBack() {
        return (curr > 0);
    }

    public boolean hasCurrent() {
        return (curr != -1);
    }

    public boolean hasForward() {
        return (last > curr);
    }
}
