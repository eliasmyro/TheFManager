package gr.teicm.mp.thefmanager.models.filefilters.filetable;

import java.io.FileFilter;

/**
 * Created by Achilleas Naoumidis on 3/28/14.
 */
public enum ETableNodePolicies {
    TRUE("gr.teicm.mp.thefmanager.models.filefilters.filetable.TableNodePolicy"),
    FALSE("gr.teicm.mp.thefmanager.models.filefilters.filetable.TableHiddenNodePolicy");

    private String className;

    ETableNodePolicies(String s) {
        this.className = s;
    }

    public FileFilter getInstance()
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        return (FileFilter) Class.forName(this.className).newInstance();
    }
}


