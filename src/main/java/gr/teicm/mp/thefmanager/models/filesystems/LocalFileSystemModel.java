package gr.teicm.mp.thefmanager.models.filesystems;

import gr.teicm.mp.thefmanager.models.FileNode;
import gr.teicm.mp.thefmanager.models.filefilters.TreeNodeFilter;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.FileFilter;
import java.util.Vector;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class LocalFileSystemModel implements TreeModel {
    private File root;
    private Vector<TreeModelListener> listeners = new Vector<>();
    private FileFilter treeNodeFilter = new TreeNodeFilter();

    public LocalFileSystemModel(File root) {
        this.root = root;
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
        return new FileNode(parentNode, children[index].getName());
    }

    @Override
    public int getChildCount(Object parent) {
        File parentNode = (File) parent;

        if (parentNode.isDirectory()) {
            File[] children = parentNode.listFiles(treeNodeFilter);

            return children.length;
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
        // TODO: !!!Something like!!! rename... Not actual renaming the file...
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
