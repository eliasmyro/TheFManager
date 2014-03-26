/*
 * Created by JFormDesigner on Wed Mar 12 12:44:13 EET 2014
 */

package gr.teicm.mp.thefmanager.gui;

import gr.teicm.mp.thefmanager.controllers.TreeFacade;
import gr.teicm.mp.thefmanager.dao.LocalFileSystemDao;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author EliasMyr
 */
public class MainForm extends JFrame {
    TreeFacade treeFacade;

    public MainForm() {
        treeFacade = new TreeFacade(new LocalFileSystemDao());
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mgrToolbar = new JToolBar();
        previousButton = new JButton();
        nextButton = new JButton();
        filepathTextField = new JTextField();
        fileInfoPane = new JPanel();
        fileInfoLabel = new JLabel();
        mgrSplitPane = new JSplitPane();
        fileTreeScroll = new JScrollPane();
        fileTree = treeFacade.initializeTree();
        tableScrollPane = new JScrollPane();
        filesTable = new JTable();

        //======== this ========
        setTitle("The F* manager");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mgrToolbar ========
        {
            mgrToolbar.setFloatable(false);

            //---- previousButton ----
            previousButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/Actions-go-previous-icon.png")));
            mgrToolbar.add(previousButton);

            //---- nextButton ----
            nextButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/Actions-go-next-icon.png")));
            mgrToolbar.add(nextButton);
            mgrToolbar.add(filepathTextField);
        }
        contentPane.add(mgrToolbar, BorderLayout.PAGE_START);

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
        contentPane.add(fileInfoPane, BorderLayout.PAGE_END);

        //======== mgrSplitPane ========
        {
            mgrSplitPane.setBackground(Color.white);
            mgrSplitPane.setOneTouchExpandable(true);
            mgrSplitPane.setResizeWeight(0.1);

            //======== fileTreeScroll ========
            {
                fileTreeScroll.setViewportView(fileTree);
            }
            mgrSplitPane.setLeftComponent(fileTreeScroll);

            //======== tableScrollPane ========
            {
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
    private JPanel fileInfoPane;
    private JLabel fileInfoLabel;
    private JSplitPane mgrSplitPane;
    private JScrollPane fileTreeScroll;
    private JTree fileTree;
    private JScrollPane tableScrollPane;
    private JTable filesTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
