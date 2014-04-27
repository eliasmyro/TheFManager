package gr.teicm.mp.thefmanager.models.filefilters.filetree;

import java.io.FileFilter;

/**
 * Created by Achilleas Naoumidis on 3/28/14.
 */
public enum TreeNodePolicies {
    TRUE("gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeNodePolicy"),
    FALSE("gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeHiddenNodePolicy");

    private String value;

    TreeNodePolicies(String s) {
        this.value = s;
    }

    public FileFilter getInstance()
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        return (FileFilter) Class.forName(this.value).newInstance();
    }
}


