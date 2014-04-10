package gr.teicm.mp.thefmanager.models.filefilters.filetree;

import java.io.FileFilter;

/**
 * Created by Achilleas Naoumidis on 3/28/14.
 */
public enum  ETreeNodePolicies {
    TRUE("gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeNodePolicy"),
    FALSE("gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeHiddenNodePolicy");

    private String className;

    ETreeNodePolicies(String s) {
        this.className = s;
    }

    public FileFilter getInstance()
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        return (FileFilter) Class.forName(this.className).newInstance();
    }
}


