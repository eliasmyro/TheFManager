package gr.teicm.mp.thefmanager.models;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class NodeFilter implements FileFilter {

    @Override
    public boolean accept(File file) {

        boolean isHidden = file.isHidden();
        boolean isFile = file.isFile();

        if (!isHidden) {
            if (!isFile) {
                return true;
            }
        }

        return false;
    }
}
