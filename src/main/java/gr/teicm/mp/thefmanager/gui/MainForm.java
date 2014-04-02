/*
 * Created by JFormDesigner on Wed Mar 12 12:44:13 EET 2014
 */

package gr.teicm.mp.thefmanager.gui;

import gr.teicm.mp.thefmanager.DAO.LocalFileSystemDAO;
import gr.teicm.mp.thefmanager.controllers.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Date;

/**
 * @author Elias Myronidis
 */
public class MainForm extends JFrame {
    private TreeFacade treeFacade;
    private TableFacade tableFacade;
    private boolean themeIsSet = false;
    private IWriteThemeController mThemeFile = new WriteThemeController();

    public MainForm() {
        treeFacade = new TreeFacade(new LocalFileSystemDAO());
        tableFacade = new TableFacade();
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void napkinMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("napkin");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("net.sourceforge.napkinlaf.NapkinLookAndFeel");
        }

        this.dispose();
    }

    private void nextButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void seaglassMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("seaglass");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        }

        dispose();
    }

    private void quaquaMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("quaqua");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("ch.randelshofer.quaqua.QuaquaLookAndFeel");
        }

        this.dispose();
    }

    private void jTatAlumMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("jtatAluminium");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        }

        this.dispose();
    }

    private void jTatHifiMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("jtatHifi");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        }

        this.dispose();
    }

    private void jTatBernsteinMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("jtatBernstein");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        }

        this.dispose();
    }

    private void fileTreeItemSelect(TreeSelectionEvent e) {
        filepathTextField.setText(treeFacade.getSelectedItemPath());
        fileInfoLabel.setText("Folder items: " + Integer.toString(treeFacade.getSelectedItemContentNumber()));
        tableFacade.updateFileTable(treeFacade.getSelectedFileItem(), filesTable);
    }

    private void themesMenuStateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void tableScrollPaneFocusGained(FocusEvent e) {
        // TODO add your code here
    }

    private void tableScrollPaneMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void tableScrollPaneKeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    private void filesTableKeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    private void filesTableFocusGained(FocusEvent e) {
        // TODO add your code here
    }

    private void filesTablePropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void filesTableFocusLost(FocusEvent e) {
        // TODO add your code here
    }

    private void filesTableKeyReleased(KeyEvent e) {
        // TODO add your code here
    }

    private void filesTableMousePressed(MouseEvent e) {
        int selectedRow = filesTable.getSelectedRow();
        int pathColumn = 2;
        String selectedFilePath;
        File selectedTableFile;

        selectedFilePath = filesTable.getValueAt(selectedRow,pathColumn).toString();
        tableFacade = new TableFacade(selectedFilePath);
        selectedTableFile = tableFacade.getSelectedTableFile();
        fileIconLbl.setIcon(tableFacade.fileSystemView.getSystemIcon(selectedTableFile));
        fileName.setText(selectedTableFile.getName());
        filePath.setText(selectedTableFile.getPath());
        fileSize.setText(selectedTableFile.length() +" Bytes");
        readAttribute.setSelected(selectedTableFile.canRead());
        writeAttribute.setSelected(selectedTableFile.canWrite());
        executeAttribute.setSelected(selectedTableFile.canExecute());


  //    ------ Example of renaming a simple txt file, testing if i got the selected file object successfully ------
    /*  File newName = new File(selectedTableFile.getParent()+"/newName.txt");
        System.out.println(selectedTableFile.getParent()+"/newName.txt");
        if(selectedTableFile.renameTo(newName)) {
            System.out.println("renamed");
        } else {
            System.out.println(selectedTableFile.getParent()+"/newName.txt");
        } */

    }

    private void previousButtonMouseClicked(MouseEvent e) {

    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mgrToolbar = new JToolBar();
        previousButton = new JButton();
        nextButton = new JButton();
        filepathTextField = new JTextField();
        menuBar1 = new JMenuBar();
        themesMenu = new JMenu();
        napkinMenuItem = new JMenuItem();
        seaglassMenuItem = new JMenuItem();
        quaquaMenuItem = new JMenuItem();
        jTatAlumMenuItem = new JMenuItem();
        jTatHifiMenuItem = new JMenuItem();
        jTatBernsteinMenuItem = new JMenuItem();
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
        mgrSplitPane = new JSplitPane();
        fileTreeScroll = new JScrollPane();
        fileTree = treeFacade.initializeTree();
        tableScrollPane = new JScrollPane();
        filesTable = new JTable();
        filesTable.setModel(new javax.swing.table.DefaultTableModel (
                         new Object [][]  {

                        },
                                new String [] {
                                        "Icon",
                                        "File",
                                        "Path/name",
                                        "Size",
                                        "Last Modified",
                                        "R",
                                        "W",
                                        "E",
                                        "Directory",
                                        "File",
                                }


                        )
                        { public boolean isCellEditable(int row, int column){return false;}

                            public Class getColumnClass(int columnIndex) {
                                switch (columnIndex) {
                                    case 0:
                                        return ImageIcon.class;
                                    case 3:
                                        return Long.class;
                                    case 4:
                                        return Date.class;
                                    case 5:
                                        return Boolean.class;
                                    case 6:
                                        return Boolean.class;
                                    case 7:
                                        return Boolean.class;
                                    case 8:
                                        return Boolean.class;
                                    case 9:
                                        return Boolean.class;
                                }
                                return String.class;

                            }
                        });

        //======== this ========
        setTitle("The F* manager");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mgrToolbar ========
        {
            mgrToolbar.setFloatable(false);
            mgrToolbar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            //---- previousButton ----
            previousButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/Actions-go-previous-icon.png")));
            previousButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    previousButtonMouseClicked(e);
                }
            });
            mgrToolbar.add(previousButton);

            //---- nextButton ----
            nextButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/Actions-go-next-icon.png")));
            nextButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    nextButtonMouseClicked(e);
                }
            });
            mgrToolbar.add(nextButton);

            //---- filepathTextField ----
            filepathTextField.setHorizontalAlignment(SwingConstants.LEFT);
            mgrToolbar.add(filepathTextField);

            //======== menuBar1 ========
            {

                //======== themesMenu ========
                {
                    themesMenu.setHorizontalAlignment(SwingConstants.CENTER);
                    themesMenu.setText("theme");
                    themesMenu.addChangeListener(new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent e) {
                            themesMenuStateChanged(e);
                            themesMenuStateChanged(e);
                        }
                    });

                    //---- napkinMenuItem ----
                    napkinMenuItem.setText("Napkin");
                    napkinMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            napkinMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(napkinMenuItem);

                    //---- seaglassMenuItem ----
                    seaglassMenuItem.setText("Seaglass");
                    seaglassMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            seaglassMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(seaglassMenuItem);

                    //---- quaquaMenuItem ----
                    quaquaMenuItem.setText("Quaqua");
                    quaquaMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            quaquaMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(quaquaMenuItem);

                    //---- jTatAlumMenuItem ----
                    jTatAlumMenuItem.setText("JTattoo-Aluminium");
                    jTatAlumMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            jTatAlumMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(jTatAlumMenuItem);

                    //---- jTatHifiMenuItem ----
                    jTatHifiMenuItem.setText("JTattoo-HiFI");
                    jTatHifiMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            jTatHifiMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(jTatHifiMenuItem);

                    //---- jTatBernsteinMenuItem ----
                    jTatBernsteinMenuItem.setText("JTattoo-Bernstein");
                    jTatBernsteinMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            jTatBernsteinMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(jTatBernsteinMenuItem);
                }
                menuBar1.add(themesMenu);
            }
            mgrToolbar.add(menuBar1);
        }
        contentPane.add(mgrToolbar, BorderLayout.PAGE_START);

        //======== fileInfoPane ========
        {
            fileInfoPane.setBorder(new CompoundBorder(
                new SoftBevelBorder(SoftBevelBorder.RAISED),
                null));
            fileInfoPane.setPreferredSize(new Dimension(592, 70));
            fileInfoPane.setLayout(new FlowLayout(FlowLayout.LEFT));

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
                tableScrollPane.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        tableScrollPaneFocusGained(e);
                    }
                });
                tableScrollPane.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tableScrollPaneMouseClicked(e);
                    }
                });
                tableScrollPane.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        tableScrollPaneKeyPressed(e);
                    }
                });

                //---- filesTable ----
                filesTable.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        filesTableKeyPressed(e);
                        filesTableKeyPressed(e);
                    }
                });
                filesTable.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        filesTableFocusGained(e);
                    }
                });
                filesTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        filesTableMousePressed(e);
                    }
                });
                tableScrollPane.setViewportView(filesTable);
            }
            mgrSplitPane.setRightComponent(tableScrollPane);
        }
        contentPane.add(mgrSplitPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JToolBar mgrToolbar;
    private JButton previousButton;
    private JButton nextButton;
    private JTextField filepathTextField;
    private JMenuBar menuBar1;
    private JMenu themesMenu;
    private JMenuItem napkinMenuItem;
    private JMenuItem seaglassMenuItem;
    private JMenuItem quaquaMenuItem;
    private JMenuItem jTatAlumMenuItem;
    private JMenuItem jTatHifiMenuItem;
    private JMenuItem jTatBernsteinMenuItem;
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
    private JSplitPane mgrSplitPane;
    private JScrollPane fileTreeScroll;
    private JTree fileTree;
    private JScrollPane tableScrollPane;
    private JTable filesTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
