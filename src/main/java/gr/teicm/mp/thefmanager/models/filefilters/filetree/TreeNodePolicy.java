package gr.teicm.mp.thefmanager.models.filefilters.filetree;

import java.io.File;
import java.io.FileFilter;

public class TreeNodePolicy implements FileFilter {

    @Override
    public boolean accept(File file) {
        return !file.isFile();
    }
}
