package gr.teicm.mp.thefmanager.models.filefilters.filetree;

import gr.teicm.mp.thefmanager.models.filefilters.IFileFilter;

import java.io.FileFilter;

public enum TreeNodePolicies implements IFileFilter {
    TRUE("gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeNodePolicy"),
    FALSE("gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeHiddenNodePolicy");

    private String value;

    TreeNodePolicies(String s) {
        this.value = s;
    }

    @Override
    public FileFilter getInstance()
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        return (FileFilter) Class.forName(this.value).newInstance();
    }
}
