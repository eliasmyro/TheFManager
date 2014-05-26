package gr.teicm.mp.thefmanager.controllers.history;

import java.util.ArrayList;
import java.util.List;

public class PathHistory implements IHistory<String> {
    private static PathHistory instance;

    private List<String> history;
    private int curr;
    private int last;

    protected PathHistory() {
        history = new ArrayList<>();
        curr = -1;
        last = -1;
    }

    public static PathHistory getInstance() {
        if (instance == null) {
            instance = new PathHistory();
        }

        return instance;
    }

    public void add(String item) {
        if (hasForward()) {
            if (item.equals(history.get(curr + 1))) {
                curr++;
            } else {
                history.add(++curr, item);
                last = curr;
            }
        } else {
            history.add(++curr, item);
            last = curr;
        }
    }

    public String back() {
        return hasBack() ? history.get(--curr) : history.get(0);
    }

    public String forward() {
        return hasForward() ? history.get(++curr) : history.get(last);
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
