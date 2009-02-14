package com.pl.plugins.commons.ui.uinew.util.runner;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 14:53:54
 */
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;

/**
 * <pre>
 * Панель блокировки фрейма при запуске фоновых задач.
 * </pre>
 *
 * <pre>UC #<номер UC></pre>
 * <br>
 * $Rev$
 * <p>
 * $LastChangedDate::                     $
 * <p>
 *
 * @author <a href="mailto:orlyansky.vladimir@otr.ru" >Орлянский Владимир</a>
 *
 * @since 1.9.1
 */
final class LockingGlassPane extends JPanel {
    private Component recentFocusOwner;

    public LockingGlassPane() {
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public void setVisible(boolean isVisible) {
        boolean oldVisible = isVisible();
        super.setVisible(isVisible);
        JRootPane rootPane = SwingUtilities.getRootPane(this);
        if (rootPane != null && isVisible() != oldVisible) {
            if (isVisible) {
                Component focusOwner = KeyboardFocusManager.
                        getCurrentKeyboardFocusManager().getPermanentFocusOwner();
                if (focusOwner != null &&
                        SwingUtilities.isDescendingFrom(focusOwner, rootPane)) {
                    recentFocusOwner = focusOwner;
                }
                rootPane.getLayeredPane().setVisible(false);
                requestFocusInWindow();
            } else {
                rootPane.getLayeredPane().setVisible(true);
                if (recentFocusOwner != null) {
                    recentFocusOwner.requestFocusInWindow();
                }
                recentFocusOwner = null;
            }
        }

    }

    protected void paintComponent(Graphics g) {
        JRootPane rootPane = SwingUtilities.getRootPane(this);
        if (rootPane != null) {
            // it is important to call print() instead of paint() here
            // because print() doesn't affect the frame's double buffer
            rootPane.getLayeredPane().print(g);
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(0, 128, 128, 64));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}
