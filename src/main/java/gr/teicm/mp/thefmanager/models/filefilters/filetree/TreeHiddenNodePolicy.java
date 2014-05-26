package gr.teicm.mp.thefmanager.models.filefilters.filetree;

import java.io.File;
import java.io.FileFilter;

public class TreeHiddenNodePolicy implements FileFilter {

    @Override
    public boolean accept(File file) {
        if (!file.isHidden()) {
            if (!file.isFile()) {
                return true;
            }
        }

        return false;
    }
}
