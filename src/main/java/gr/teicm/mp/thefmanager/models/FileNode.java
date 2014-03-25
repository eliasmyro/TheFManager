package gr.teicm.mp.thefmanager.models;

import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/25/14.
 */
public class FileNode extends File {
    public FileNode(String file) {
        super(file);
    }

    public FileNode(File parent, String child) {
        super(parent, child);
    }

    @Override
    public String toString() {
        return getName();
    }
}
