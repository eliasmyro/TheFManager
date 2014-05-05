package gr.teicm.mp.thefmanager.controllers.filetree;

import gr.teicm.mp.thefmanager.DAO.IFileSystemDAO;
import gr.teicm.mp.thefmanager.models.filesystems.FileTreeModel;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class TreeFacade {
    private File node;
    private TreeModel fileSystemModel;

    public TreeFacade(IFileSystemDAO dao) {
        fileSystemModel = new FileTreeModel(dao.getHomeDirectory());
    }

    public String getSelectedItemPath(JTree fileTree) {
        node = (File) fileTree.getLastSelectedPathComponent();

        if (node != null) {
            return node.getPath();
        }

        return null;
    }

    public int getSelectedItemChildCount(JTree fileTree) {
        node = (File) fileTree.getLastSelectedPathComponent();

        if (node != null) {
            File[] children = new File(node.getPath()).listFiles();

            if (children != null) {
                return children.length;
            }
        }

        return 0;
    }

    public File getSelectedFileItem(JTree fileTree) {
        node = (File) fileTree.getLastSelectedPathComponent();

        if (node != null) {
            return node;
        }

        return null;
    }

    public TreeModel getFileSystemModel() {
        return fileSystemModel;
    }
}
