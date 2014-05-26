package gr.teicm.mp.thefmanager.models.filefilters.filetable;

import java.io.File;
import java.io.FileFilter;

public class TableHiddenNodePolicy implements FileFilter {

    @Override
    public boolean accept(File file) {
        return !file.isHidden();
    }
}
