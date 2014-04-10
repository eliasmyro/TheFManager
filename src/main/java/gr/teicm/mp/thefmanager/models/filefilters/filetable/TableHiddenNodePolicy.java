package gr.teicm.mp.thefmanager.models.filefilters.filetable;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class TableHiddenNodePolicy implements FileFilter {

    @Override
    public boolean accept(File file) {
        return !file.isHidden();
    }
}
