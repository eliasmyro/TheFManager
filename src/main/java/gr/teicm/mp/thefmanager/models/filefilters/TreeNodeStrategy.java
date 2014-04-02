package gr.teicm.mp.thefmanager.models.filefilters;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Elias Myronidis on 28/3/2014.
 */
public class TreeNodeStrategy implements FileFilter {
    @Override
    public boolean accept(File pathname) {

        if (!pathname.isFile()) {
            return true;
        }

        return false;
    }
}
