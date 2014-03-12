/*
 * Created by JFormDesigner on Wed Mar 12 12:44:13 EET 2014
 */

package gr.teicm.mp.thefmanager.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author EliasMyr
 */
public class MainForm extends JFrame {
    public MainForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mgrMenuBar = new JMenuBar();
        fileMenu = new JMenu();
        exitMenuItem = new JMenuItem();
        editMenu = new JMenu();
        cutMenuItem = new JMenuItem();
        copyMenuItem = new JMenuItem();
        pasteMenuItem = new JMenuItem();
        viewMenu = new JMenu();
        goMenu = new JMenu();
        helpMenu = new JMenu();
        aboutMenuItem = new JMenuItem();
        mgrToolbar = new JToolBar();
        previousButton = new JButton();
        nextButton = new JButton();
        upButton = new JButton();
        refreshButton = new JButton();
        filepathCombo = new JComboBox();
        fileInfoPane = new JPanel();
        fileInfoLabel = new JLabel();
        mgrSplitPane = new JSplitPane();
        fileTreeScroll = new JScrollPane();
        fileTree = new JTree();

        //======== this ========
        setTitle("The F* manager");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mgrMenuBar ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("File");

                //---- exitMenuItem ----
                exitMenuItem.setText("Exit");
                fileMenu.add(exitMenuItem);
            }
            mgrMenuBar.add(fileMenu);

            //======== editMenu ========
            {
                editMenu.setText("Edit");

                //---- cutMenuItem ----
                cutMenuItem.setText("Cut");
                editMenu.add(cutMenuItem);

                //---- copyMenuItem ----
                copyMenuItem.setText("Copy");
                editMenu.add(copyMenuItem);

                //---- pasteMenuItem ----
                pasteMenuItem.setText("Paste");
                editMenu.add(pasteMenuItem);
            }
            mgrMenuBar.add(editMenu);

            //======== viewMenu ========
            {
                viewMenu.setText("View");
            }
            mgrMenuBar.add(viewMenu);

            //======== goMenu ========
            {
                goMenu.setText("Go");
            }
            mgrMenuBar.add(goMenu);

            //======== helpMenu ========
            {
                helpMenu.setText("Help");

                //---- aboutMenuItem ----
                aboutMenuItem.setText("About");
                helpMenu.add(aboutMenuItem);
            }
            mgrMenuBar.add(helpMenu);
        }
        setJMenuBar(mgrMenuBar);

        //======== mgrToolbar ========
        {
            mgrToolbar.setFloatable(false);

            //---- previousButton ----
            previousButton.setIcon(new ImageIcon("C:\\Users\\Elias\\IdeaProjects\\TheFManager\\resources\\Actions-go-previous-icon.png"));
            mgrToolbar.add(previousButton);

            //---- nextButton ----
            nextButton.setIcon(new ImageIcon("C:\\Users\\Elias\\IdeaProjects\\TheFManager\\resources\\Actions-go-next-icon.png"));
            mgrToolbar.add(nextButton);

            //---- upButton ----
            upButton.setIcon(new ImageIcon("C:\\Users\\Elias\\IdeaProjects\\TheFManager\\resources\\Refresh-icon.png"));
            mgrToolbar.add(upButton);

            //---- refreshButton ----
            refreshButton.setIcon(new ImageIcon("C:\\Development\\JavaProjects\\Experiment\\resources\\Refresh-icon.png"));
            mgrToolbar.add(refreshButton);

            //---- filepathCombo ----
            filepathCombo.setEditable(true);
            mgrToolbar.add(filepathCombo);
        }
        contentPane.add(mgrToolbar, BorderLayout.NORTH);

        //======== fileInfoPane ========
        {
            fileInfoPane.setBorder(new CompoundBorder(
                new SoftBevelBorder(SoftBevelBorder.RAISED),
                null));
            fileInfoPane.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- fileInfoLabel ----
            fileInfoLabel.setText("\u03c0\u03bb\u03b7\u03c1\u03bf\u03c6\u03bf\u03c1\u03af\u03b5\u03c2 \u03c3\u03c7\u03b5\u03c4\u03b9\u03ba\u03ac \u03bc\u03b5 \u03c4\u03bf \u03b1\u03c1\u03c7\u03ad\u03b9\u03bf / \u03c6\u03ac\u03ba\u03b5\u03bb\u03bf");
            fileInfoLabel.setFont(fileInfoLabel.getFont().deriveFont(fileInfoLabel.getFont().getStyle() & ~Font.BOLD, fileInfoLabel.getFont().getSize() - 3f));
            fileInfoPane.add(fileInfoLabel);
        }
        contentPane.add(fileInfoPane, BorderLayout.SOUTH);

        //======== mgrSplitPane ========
        {
            mgrSplitPane.setBackground(Color.white);

            //======== fileTreeScroll ========
            {
                fileTreeScroll.setViewportView(fileTree);
            }
            mgrSplitPane.setLeftComponent(fileTreeScroll);
        }
        contentPane.add(mgrSplitPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar mgrMenuBar;
    private JMenu fileMenu;
    private JMenuItem exitMenuItem;
    private JMenu editMenu;
    private JMenuItem cutMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenu viewMenu;
    private JMenu goMenu;
    private JMenu helpMenu;
    private JMenuItem aboutMenuItem;
    private JToolBar mgrToolbar;
    private JButton previousButton;
    private JButton nextButton;
    private JButton upButton;
    private JButton refreshButton;
    private JComboBox filepathCombo;
    private JPanel fileInfoPane;
    private JLabel fileInfoLabel;
    private JSplitPane mgrSplitPane;
    private JScrollPane fileTreeScroll;
    private JTree fileTree;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
