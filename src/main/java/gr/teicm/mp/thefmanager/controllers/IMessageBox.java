package gr.teicm.mp.thefmanager.controllers;

import javax.swing.*;
import java.awt.*;

public interface IMessageBox {
    /**
     * Indicates that the user has not yet selected a value.
     */
    Object UNINITIALIZED_VALUE = "uninitializedValue";

    //
    // Option types
    //
    /**
     * Type used for <code>showConfirmDialog</code>.
     */
    int DEFAULT_OPTION = -1;
    /**
     * Type used for <code>showConfirmDialog</code>.
     */
    int YES_NO_OPTION = 0;
    /**
     * Type used for <code>showConfirmDialog</code>.
     */
    int YES_NO_CANCEL_OPTION = 1;
    /**
     * Type used for <code>showConfirmDialog</code>.
     */
    int OK_CANCEL_OPTION = 2;

    //
    // Message types.
    //
    /**
     * Used for error messages.
     */
    int ERROR_MESSAGE = 0;
    /**
     * Used for information messages.
     */
    int INFORMATION_MESSAGE = 1;
    /**
     * Used for warning messages.
     */
    int WARNING_MESSAGE = 2;
    /**
     * Used for questions.
     */
    int QUESTION_MESSAGE = 3;
    /**
     * No icon is used.
     */
    int PLAIN_MESSAGE = -1;

    //
    // Return values.
    //
    /**
     * Return value from class method if YES is chosen.
     */
    int YES_OPTION = 0;
    /**
     * Return value from class method if NO is chosen.
     */
    int NO_OPTION = 1;
    /**
     * Return value from class method if CANCEL is chosen.
     */
    int CANCEL_OPTION = 2;
    /**
     * Return value form class method if OK is chosen.
     */
    int OK_OPTION = 0;
    /**
     * Return value from class method if user closes window without selecting
     * anything, more than likely this should be treated as either a
     * <code>CANCEL_OPTION</code> or <code>NO_OPTION</code>.
     */
    int CLOSED_OPTION = -1;

    void showMessageDialog(Component parentComponent, Object message);

    void showMessageDialog(Component parentComponent, Object message, String title, int JOptionPaneMessageType);

    void showMessageDialog(Component parentComponent, Object message, String title, int JOptionPaneMessageType, Icon icon);

    int showConfirmDialog(Component parentComponent, Object message);

    int showConfirmDialog(Component parentComponent, Object message, String title, int JOptionPaneOptionType);

    int showConfirmDialog(Component parentComponent, Object message, String title, int JOptionPaneOptionType, int JOptionPaneMessageType);

    int showConfirmDialog(Component parentComponent, Object message, String title, int JOptionPaneOptionType, int JOptionPaneMessageType, Icon icon);
}
