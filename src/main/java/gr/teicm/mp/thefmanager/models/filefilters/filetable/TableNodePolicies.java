package gr.teicm.mp.thefmanager.models.filefilters.filetable;

import gr.teicm.mp.thefmanager.models.filefilters.IFileFilter;

import java.io.FileFilter;

public enum TableNodePolicies implements IFileFilter {
    TRUE("gr.teicm.mp.thefmanager.models.filefilters.filetable.TableNodePolicy"),
    FALSE("gr.teicm.mp.thefmanager.models.filefilters.filetable.TableHiddenNodePolicy");

    private String className;

    TableNodePolicies(String s) {
        this.className = s;
    }

    @Override
    public FileFilter getInstance()
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        return (FileFilter) Class.forName(this.className).newInstance();
    }
}
