package gr.teicm.mp.thefmanager.controllers.filetree;

import gr.teicm.mp.thefmanager.DAO.IFileSystemDAO;
import gr.teicm.mp.thefmanager.models.filesystems.FileTreeModel;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.io.File;

public class TreeFacade {
    private TreeModel fileTreeModel;

    public TreeFacade(IFileSystemDAO dao) {
        fileTreeModel = new FileTreeModel(new File(dao.getHomeDirectory()));
    }

    public String getSelectedItemPath(JTree fileTree) {
        File node = (File) fileTree.getLastSelectedPathComponent();

        if (node != null) {
            return node.getPath();
        }

        return null;
    }

    public TreeModel getFileTreeModel() {
        return fileTreeModel;
    }
}
