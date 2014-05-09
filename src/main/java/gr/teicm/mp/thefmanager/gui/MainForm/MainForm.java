/*
 * Created by JFormDesigner on Wed Mar 12 12:44:13 EET 2014
 */

package gr.teicm.mp.thefmanager.gui.MainForm;

import gr.teicm.mp.thefmanager.DAO.IFileSystemDAO;
import gr.teicm.mp.thefmanager.DAO.FileSystemDAO;
import gr.teicm.mp.thefmanager.controllers.History;
import gr.teicm.mp.thefmanager.controllers.fileoperations.*;
import gr.teicm.mp.thefmanager.controllers.filetable.TableFacade;
import gr.teicm.mp.thefmanager.controllers.filetree.TreeFacade;
import gr.teicm.mp.thefmanager.controllers.filetree.FileTreeCellRenderer;
import gr.teicm.mp.thefmanager.gui.PreferencesForm.PreferencesForm;
import gr.teicm.mp.thefmanager.models.filesystems.FileTableModel;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainForm extends JFrame {
    private TreeFacade treeFacade;
    private TableFacade tableFacade;
    private History history;

    private String selectedFilePath;
    private File selectedTableFile;
    private File fileToCopy;

    public MainForm() {
        IFileSystemDAO fileSystemDAO = new FileSystemDAO();

        treeFacade = new TreeFacade(fileSystemDAO);
        tableFacade = new TableFacade();
        history = new History();

        initComponents();

        new FileTableModel(fileTable);
        fileTree.setCellRenderer(new FileTreeCellRenderer());
        tableFacade.updateFileTable(fileSystemDAO.getHomeDirectory(), fileTable);
        showFilePosition(fileSystemDAO.getHomeDirectory().getPath(), true, true);
    }

    /**
     * Shows current location in top menu bar.
     *
     * @param filePath
     * @param addToHistory
     */

    public void showFilePosition(String filePath, boolean addToHistory, boolean addSeparatorToEnd) {
        String path;

        if (addSeparatorToEnd) {
            path = FilenameUtils.normalize(filePath + File.separator);
        } else {
            path = FilenameUtils.normalize(filePath);
        }

        filepathTextField.setText(path);

        if (addToHistory) {
            history.add(path);
        }
    }

    /**
     * Open location by typing path in top menu bar.
     *
     * @param e
     */

    private void filepathTextFieldKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == File.separatorChar) {
            String path = filepathTextField.getText();
            File directory = new File(path);

            if (directory.exists()) {
                tableFacade.updateFileTable(directory, fileTable);

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    showFilePosition(path, true, true);
                } else if (e.getKeyCode() == File.separatorChar) {
                    showFilePosition(path, true, false);
                }
            }
        }
    }

    /**
     * On "Forward" button click.
     *
     * @param e
     */

    private void nextButtonMouseClicked(MouseEvent e) {
        String path = history.forward();
        tableFacade.updateFileTable(new File(path), fileTable);
        showFilePosition(path, false, true);
    }

    /**
     * On "Back" button click.
     *
     * @param e
     */

    private void previousButtonMouseClicked(MouseEvent e) {
        String path = history.back();
        tableFacade.updateFileTable(new File(path), fileTable);
        showFilePosition(path, false, true);
    }

    /**
     * "Open" Command.
     *
     * @param e
     */

    private void fileMenuItemOpenActionPerformed(ActionEvent e) {
        IFileOpenController fileOpen = new FileOpenController();
        int returnedCode = fileOpen.fileOpen(selectedTableFile);

        if (returnedCode == 0) {
            JOptionPane.showMessageDialog(this, "There is no App for this file or Desktop is not supported");
        }
    }

    /**
     * "New File" Command.
     *
     * @param e
     */

    private void newFileMenuItemActionPerformed(ActionEvent e) {
        INewFileController newFile = new NewFileController();
        String fileName = JOptionPane.showInputDialog("Give the name of the file", "New File Name");
        boolean fileCreated = newFile.createNewFile(selectedTableFile, fileName);
        if(!fileCreated){
            JOptionPane.showMessageDialog(this, "File not created");
        }
        else
            tableRefresh();
    }

    /**
     * "New Folder" Command.
     *
     * @param e
     */

    private void newFolderMenuItemActionPerformed(ActionEvent e) {
        INewFolderController newFolder = new NewFolderController();
        String fileName = JOptionPane.showInputDialog("Give the name of the file", "New File Name");
        boolean fileCreated = newFolder.createNewFolder(selectedTableFile,fileName);
        if(!fileCreated){
            JOptionPane.showMessageDialog(this, "Folder not created");
        }
        else
            tableRefresh();
    }

    /**
     * "Copy" Command.
     *
     * @param e
     */

    private void CopyFileMousePressed(MouseEvent e) {
        tableFacade = new TableFacade(selectedFilePath);
        this.fileToCopy = tableFacade.getSelectedTableFile();
    }

    /**
     * "Paste" Command.
     *
     * @param e
     */

    private void PasteFileMousePressed(MouseEvent e) {
        ICopyFileController myCopyFile = new CopyFileController();
        boolean isCopied = myCopyFile.copyFile(this.fileToCopy,new File(history.current()));
    }

    /**
     * "Rename" Command.
     *
     * @param e
     */

    private void fileMenuItemRenameMousePressed(MouseEvent e) {
        IRenameFileController myRename = new RenameFileController();
        String newFileName = JOptionPane.showInputDialog("Enter new name", selectedTableFile.getName());

        boolean fileRenamed = myRename.renameFile(selectedTableFile,newFileName);
        if(!fileRenamed){
            JOptionPane.showMessageDialog(this, "File was not renamed!");
        }
        else
            tableRefresh();
    }

    /**
     * "Delete" Command.
     *
     * @param e
     */

    private void fileMenuItemDeleteMousePressed(MouseEvent e) {
        IDeleteFileController myDelete = new DeleteFileController();
        boolean isDeleted = myDelete.deleteFile(selectedTableFile);

        if(isDeleted)
            tableRefresh();
    }

    /**
     * Tree Item Select Event
     *
     * @param e
     */

    private void fileTreeItemSelect(TreeSelectionEvent e) {
        String currentPath = treeFacade.getSelectedItemPath(fileTree);
        fileInfoLabel.setText("Folder items: " + Integer.toString(treeFacade.getSelectedItemChildCount(fileTree)));
        showFilePosition(currentPath, true, true);
        tableFacade.updateFileTable(treeFacade.getSelectedFileItem(fileTree), fileTable);
    }

    /**
     * File Table Mouse Pressed Event.
     *
     * @param e
     */

    private void filesTableMousePressed(MouseEvent e) {
        IFileOpenController fileOpen = new FileOpenController();
        if(e.isPopupTrigger()) {
            rightClickTableMenu.show(e.getComponent(),e.getX(),e.getY());
        }

        int selectedRow = fileTable.getSelectedRow();
        int pathColumn = 2;

        selectedFilePath = fileTable.getValueAt(selectedRow, pathColumn).toString();
        tableFacade = new TableFacade(selectedFilePath);
        selectedTableFile = tableFacade.getSelectedTableFile();
        fileIconLbl.setIcon(tableFacade.fileSystemView.getSystemIcon(selectedTableFile));
        fileName.setText(selectedTableFile.getName());
        filePath.setText(selectedTableFile.getPath());
        fileSize.setText(selectedTableFile.length() + " Bytes");
        readAttribute.setSelected(selectedTableFile.canRead());
        writeAttribute.setSelected(selectedTableFile.canWrite());
        executeAttribute.setSelected(selectedTableFile.canExecute());

        if(e.getClickCount() ==2){
            int returnedCode = fileOpen.fileOpen(selectedTableFile);
            if (returnedCode == 0) {
                JOptionPane.showMessageDialog(this, "There is no App for this file or Desktop is not supported");
            }
        }
    }

    /**
     * File Table Mouse Released Event.
     *
     * @param e
     */

    private void filesTableMouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            rightClickTableMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    /**
     * Opens Settings Form Button Click.
     *
     * @param e
     */

    private void settingsButtonActionPerformed(ActionEvent e) {
        PreferencesForm preferencesForm = new PreferencesForm();
        preferencesForm.setVisible(true);
    }

    /**
     * Refreshes the File Table.
     */

    private void tableRefresh(){
        tableFacade.updateFileTable(new File(history.current()), fileTable);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        rightClickTableMenu = new JPopupMenu();
        fileMenuItemOpen2 = new JMenuItem();
        CopyFile = new JMenuItem();
        fileMenuItemDelete2 = new JMenuItem();
        fileMenuItemRename2 = new JMenuItem();
        PasteFile = new JMenuItem();
        menuBar2 = new JMenuBar();
        fileMenu = new JMenu();
        newMenu = new JMenu();
        newFileMenuItem = new JMenuItem();
        newFolderMenuItem = new JMenuItem();
        fileMenuItemOpen = new JMenuItem();
        fileMenuItemCopy = new JMenuItem();
        fileMenuItemPaste = new JMenuItem();
        fileMenuItemDelete = new JMenuItem();
        fileMenuItemRename = new JMenuItem();
        mgrToolbar = new JToolBar();
        previousButton = new JButton();
        nextButton = new JButton();
        filepathTextField = new JTextField();
        settingsButton = new JButton();
        fileInfoPane = new JPanel();
        fileInfoLabel = new JLabel();
        fileNameLbl = new JLabel();
        fileIconLbl = new JLabel();
        fileName = new JLabel();
        filePathLbl = new JLabel();
        filePath = new JLabel();
        fileSizeLbl = new JLabel();
        fileSize = new JLabel();
        fileAttributesLbl = new JLabel();
        readAttribute = new JCheckBox();
        writeAttribute = new JCheckBox();
        executeAttribute = new JCheckBox();
        renameLabel = new JLabel();
        renameTextField = new JTextField();
        mgrSplitPane = new JSplitPane();
        fileTreeScroll = new JScrollPane();
        fileTree = new JTree(treeFacade.getFileSystemModel());
        tableScrollPane = new JScrollPane();
        fileTable = new JTable();

        //======== rightClickTableMenu ========
        {

            //---- fileMenuItemOpen2 ----
            fileMenuItemOpen2.setText("Open");
            fileMenuItemOpen2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fileMenuItemOpenActionPerformed(e);
                }
            });
            rightClickTableMenu.add(fileMenuItemOpen2);

            //---- CopyFile ----
            CopyFile.setText("Copy");
            CopyFile.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    CopyFileMousePressed(e);
                }
            });
            rightClickTableMenu.add(CopyFile);

            //---- fileMenuItemDelete2 ----
            fileMenuItemDelete2.setText("Delete");
            fileMenuItemDelete2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    fileMenuItemDeleteMousePressed(e);
                }
            });
            rightClickTableMenu.add(fileMenuItemDelete2);

            //---- fileMenuItemRename2 ----
            fileMenuItemRename2.setText("Rename");
            fileMenuItemRename2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    fileMenuItemRenameMousePressed(e);
                }
            });
            rightClickTableMenu.add(fileMenuItemRename2);

            //---- PasteFile ----
            PasteFile.setText("Paste");
            PasteFile.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    PasteFileMousePressed(e);
                }
            });
            rightClickTableMenu.add(PasteFile);
        }

        //======== this ========
        setTitle("The F* manager");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar2 ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("File");

                //======== newMenu ========
                {
                    newMenu.setText("New");

                    //---- newFileMenuItem ----
                    newFileMenuItem.setText("File");
                    newFileMenuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            newFileMenuItemActionPerformed(e);
                        }
                    });
                    newMenu.add(newFileMenuItem);

                    //---- newFolderMenuItem ----
                    newFolderMenuItem.setText("Folder");
                    newFolderMenuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            newFolderMenuItemActionPerformed(e);
                        }
                    });
                    newMenu.add(newFolderMenuItem);
                }
                fileMenu.add(newMenu);

                //---- fileMenuItemOpen ----
                fileMenuItemOpen.setText("Open");
                fileMenuItemOpen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fileMenuItemOpenActionPerformed(e);
                    }
                });
                fileMenu.add(fileMenuItemOpen);

                //---- fileMenuItemCopy ----
                fileMenuItemCopy.setText("Copy");
                fileMenu.add(fileMenuItemCopy);

                //---- fileMenuItemPaste ----
                fileMenuItemPaste.setText("Paste");
                fileMenu.add(fileMenuItemPaste);

                //---- fileMenuItemDelete ----
                fileMenuItemDelete.setText("Delete");
                fileMenuItemDelete.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        fileMenuItemDeleteMousePressed(e);
                    }
                });
                fileMenu.add(fileMenuItemDelete);

                //---- fileMenuItemRename ----
                fileMenuItemRename.setText("Rename");
                fileMenuItemRename.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        fileMenuItemRenameMousePressed(e);
                    }
                });
                fileMenu.add(fileMenuItemRename);
            }
            menuBar2.add(fileMenu);
        }
        setJMenuBar(menuBar2);

        //======== mgrToolbar ========
        {
            mgrToolbar.setFloatable(false);
            mgrToolbar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            //---- previousButton ----
            previousButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/arrow-89-m-L.png")));
            previousButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    previousButtonMouseClicked(e);
                }
            });
            mgrToolbar.add(previousButton);

            //---- nextButton ----
            nextButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/arrow-89-m-R.png")));
            nextButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    nextButtonMouseClicked(e);
                }
            });
            mgrToolbar.add(nextButton);

            //---- filepathTextField ----
            filepathTextField.setHorizontalAlignment(SwingConstants.LEFT);
            filepathTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    filepathTextFieldKeyPressed(e);
                }
            });
            mgrToolbar.add(filepathTextField);

            //---- settingsButton ----
            settingsButton.setText("Settings");
            settingsButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/settings-3-m.png")));
            settingsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settingsButtonActionPerformed(e);
                }
            });
            mgrToolbar.add(settingsButton);
        }
        contentPane.add(mgrToolbar, BorderLayout.PAGE_START);

        //======== fileInfoPane ========
        {
            fileInfoPane.setBorder(new CompoundBorder(
                new SoftBevelBorder(SoftBevelBorder.RAISED),
                null));
            fileInfoPane.setPreferredSize(new Dimension(592, 70));
            fileInfoPane.setLayout(new FlowLayout());

            //---- fileInfoLabel ----
            fileInfoLabel.setText("\u03c0\u03bb\u03b7\u03c1\u03bf\u03c6\u03bf\u03c1\u03af\u03b5\u03c2 \u03c3\u03c7\u03b5\u03c4\u03b9\u03ba\u03ac \u03bc\u03b5 \u03c4\u03bf \u03b1\u03c1\u03c7\u03ad\u03b9\u03bf / \u03c6\u03ac\u03ba\u03b5\u03bb\u03bf");
            fileInfoLabel.setFont(fileInfoLabel.getFont().deriveFont(fileInfoLabel.getFont().getStyle() & ~Font.BOLD, fileInfoLabel.getFont().getSize() - 3f));
            fileInfoLabel.setMaximumSize(new Dimension(133, 10));
            fileInfoLabel.setMinimumSize(new Dimension(133, 10));
            fileInfoLabel.setPreferredSize(new Dimension(160, 10));
            fileInfoPane.add(fileInfoLabel);

            //---- fileNameLbl ----
            fileNameLbl.setText("File : ");
            fileNameLbl.setFont(fileNameLbl.getFont().deriveFont(fileNameLbl.getFont().getStyle() | Font.BOLD));
            fileInfoPane.add(fileNameLbl);
            fileInfoPane.add(fileIconLbl);
            fileInfoPane.add(fileName);

            //---- filePathLbl ----
            filePathLbl.setText("Path :");
            filePathLbl.setFont(filePathLbl.getFont().deriveFont(filePathLbl.getFont().getStyle() | Font.BOLD));
            fileInfoPane.add(filePathLbl);
            fileInfoPane.add(filePath);

            //---- fileSizeLbl ----
            fileSizeLbl.setText("Size : ");
            fileSizeLbl.setFont(fileSizeLbl.getFont().deriveFont(fileSizeLbl.getFont().getStyle() | Font.BOLD));
            fileInfoPane.add(fileSizeLbl);
            fileInfoPane.add(fileSize);

            //---- fileAttributesLbl ----
            fileAttributesLbl.setText("File attributes :");
            fileAttributesLbl.setFont(fileAttributesLbl.getFont().deriveFont(fileAttributesLbl.getFont().getStyle() | Font.BOLD));
            fileInfoPane.add(fileAttributesLbl);

            //---- readAttribute ----
            readAttribute.setText("Read");
            fileInfoPane.add(readAttribute);

            //---- writeAttribute ----
            writeAttribute.setText("Write");
            fileInfoPane.add(writeAttribute);

            //---- executeAttribute ----
            executeAttribute.setText("Execute");
            fileInfoPane.add(executeAttribute);

            //---- renameLabel ----
            renameLabel.setText("Rename");
            renameLabel.setVisible(false);
            fileInfoPane.add(renameLabel);

            //---- renameTextField ----
            renameTextField.setToolTipText("insert new name");
            renameTextField.setMinimumSize(new Dimension(20, 22));
            renameTextField.setVisible(false);
            fileInfoPane.add(renameTextField);
        }
        contentPane.add(fileInfoPane, BorderLayout.PAGE_END);

        //======== mgrSplitPane ========
        {
            mgrSplitPane.setBackground(Color.white);
            mgrSplitPane.setOneTouchExpandable(true);
            mgrSplitPane.setResizeWeight(0.1);
            mgrSplitPane.setPreferredSize(new Dimension(539, 418));
            mgrSplitPane.setDividerLocation(150);
            mgrSplitPane.setLastDividerLocation(-1);

            //======== fileTreeScroll ========
            {
                fileTreeScroll.setPreferredSize(new Dimension(79, 324));

                //---- fileTree ----
                fileTree.addTreeSelectionListener(new TreeSelectionListener() {
                    @Override
                    public void valueChanged(TreeSelectionEvent e) {
                        fileTreeItemSelect(e);
                    }
                });
                fileTreeScroll.setViewportView(fileTree);
            }
            mgrSplitPane.setLeftComponent(fileTreeScroll);

            //======== tableScrollPane ========
            {

                //---- fileTable ----
                fileTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        filesTableMousePressed(e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        filesTableMouseReleased(e);
                    }
                });
                tableScrollPane.setViewportView(fileTable);
            }
            mgrSplitPane.setRightComponent(tableScrollPane);
        }
        contentPane.add(mgrSplitPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPopupMenu rightClickTableMenu;
    private JMenuItem fileMenuItemOpen2;
    private JMenuItem CopyFile;
    private JMenuItem fileMenuItemDelete2;
    private JMenuItem fileMenuItemRename2;
    private JMenuItem PasteFile;
    private JMenuBar menuBar2;
    private JMenu fileMenu;
    private JMenu newMenu;
    private JMenuItem newFileMenuItem;
    private JMenuItem newFolderMenuItem;
    private JMenuItem fileMenuItemOpen;
    private JMenuItem fileMenuItemCopy;
    private JMenuItem fileMenuItemPaste;
    private JMenuItem fileMenuItemDelete;
    private JMenuItem fileMenuItemRename;
    private JToolBar mgrToolbar;
    private JButton previousButton;
    private JButton nextButton;
    private JTextField filepathTextField;
    private JButton settingsButton;
    private JPanel fileInfoPane;
    private JLabel fileInfoLabel;
    private JLabel fileNameLbl;
    private JLabel fileIconLbl;
    private JLabel fileName;
    private JLabel filePathLbl;
    private JLabel filePath;
    private JLabel fileSizeLbl;
    private JLabel fileSize;
    private JLabel fileAttributesLbl;
    private JCheckBox readAttribute;
    private JCheckBox writeAttribute;
    private JCheckBox executeAttribute;
    private JLabel renameLabel;
    private JTextField renameTextField;
    private JSplitPane mgrSplitPane;
    private JScrollPane fileTreeScroll;
    private JTree fileTree;
    private JScrollPane tableScrollPane;
    private JTable fileTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
