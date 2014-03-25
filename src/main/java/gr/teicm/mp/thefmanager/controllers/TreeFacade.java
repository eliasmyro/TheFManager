package gr.teicm.mp.thefmanager.controllers;

import gr.teicm.mp.thefmanager.models.filesystems.LocalFileSystem;
import gr.teicm.mp.thefmanager.models.dao.IDao;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class TreeFacade {
    IDao dao;
    File root;
    TreePath homeDirectory;
    TreeModel fileSystemModel;
    JTree jTree;

    public TreeFacade(IDao dao) {
        this.dao = dao;

        /*
         * Setting up Root path.
         * More than 1 roots may be only in DOS environment.
         */
        File[] roots = dao.getRoot();
        if (roots.length == 1) {
            this.root = roots[0];
        } else if (roots.length > 1) {
            throw new UnsupportedOperationException("Not supported more than 1 roots.");
        }

        fileSystemModel = new LocalFileSystem(this.root);

        /*
         * Setting up Home path.
         */
        homeDirectory = new TreePath(dao.getHomeDirectory());
    }

    public TreeModel getFileSystemModel() {
        return fileSystemModel;
    }

    public JTree initializeTree() {
        jTree = new JTree(getFileSystemModel());
        jTree.expandPath(homeDirectory); // Not working yet don't know why...

        return jTree;
    }

//    public void initializeTree(JTable fileTable, JTree fileTree, String path) {
//    }
}
