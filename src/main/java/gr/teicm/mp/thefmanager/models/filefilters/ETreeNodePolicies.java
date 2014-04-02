package gr.teicm.mp.thefmanager.models.filefilters;

import java.io.FileFilter;

/**
 * Created by Achilleas Naoumidis on 3/28/14.
 */
public enum  ETreeNodePolicies {
    SHOW_HIDDEN_FILES("gr.teicm.mp.thefmanager.models.filefilters.TreeNodePolicy"),
    DONT_SHOW_HIDDEN_FILES("gr.teicm.mp.thefmanager.models.filefilters.TreeHiddenNodePolicy");

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


