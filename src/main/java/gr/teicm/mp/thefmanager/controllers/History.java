package gr.teicm.mp.thefmanager.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achilleas Naoumidis on 5/2/14.
 */
public class History {
    private List<String> history;
    private int curr;
    private int last;

    {
        history = new ArrayList<>();
        curr = -1;
    }

    public History() {
    }

    public History(String path) {
        add(path);
    }

    public void add(String path) {
        if (history.get(curr + 1).equals(path)) {
            curr++;
        } else {
            history.add(curr++, path);
            last = curr;
        }
    }

    public String back() {
        return hasBack() ? history.get(curr--) : null;
    }

    public String current() {
        return hasCurrent() ? history.get(curr) : null;
    }

    public String forward() {
        return hasForward() ? history.get(curr++) : null;
    }

    public boolean hasBack() {
        return (curr > 0);
    }

    public boolean hasCurrent() {
        return (curr > -1);
    }

    public boolean hasForward() {
        return (last > curr);
    }
}
