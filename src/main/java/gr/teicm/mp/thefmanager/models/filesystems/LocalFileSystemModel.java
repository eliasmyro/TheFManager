package gr.teicm.mp.thefmanager.models.filesystems;

import gr.teicm.mp.thefmanager.controllers.preferences.IUserPreferences;
import gr.teicm.mp.thefmanager.controllers.preferences.UserPreferences;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class LocalFileSystemModel implements TreeModel {
    private File root;
    private FileFilter treeNodeFilter;
    private List<TreeModelListener> listeners = new ArrayList<>();

    public LocalFileSystemModel(File root) {
        this.root = root;

        IUserPreferences preferencesDAO = new UserPreferences();
        treeNodeFilter = preferencesDAO.getHiddenFilesFilter(false);
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        File parentNode = (File) parent;
        File[] children = parentNode.listFiles(treeNodeFilter);

        assert children != null;
        return new File(parentNode, children[index].getName());
    }

    @Override
    public int getChildCount(Object parent) {
        File parentNode = (File) parent;

        if (parentNode.isDirectory()) {
            File[] children = parentNode.listFiles(treeNodeFilter);

            if(children != null) {
                return children.length;
            }
        }

        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        File file = (File) node;

        return file.isFile();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File parentNode = (File) parent;
        File childNode = (File) child;

        File[] children = parentNode.listFiles(treeNodeFilter);

        int result = -1;

        for (int i = 0; i < children.length; i++) {
            if (childNode.equals(children[i])) {
                result = i;
                break;
            }
        }

        return result;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addTreeModelListener(TreeModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener listener) {
        listeners.remove(listener);
    }
}
