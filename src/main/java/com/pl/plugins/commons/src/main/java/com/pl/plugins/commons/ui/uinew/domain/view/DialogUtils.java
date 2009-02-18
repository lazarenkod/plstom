package com.pl.plugins.commons.ui.uinew.domain.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 14.02.2009
 * Time: 15:59:41
 */
public class DialogUtils {
    private static final Logger logger = Logger.getLogger(DialogUtils.class.getName());

    static {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
    }

    public static void showForm(
            final JPanel panel,
            final String dialogTitle,
            final String okButtonText,
            final String cancelButtonText,
            final ActionListener actionListener) {
//        final DialogDescriptor dialogDescriptor = new DialogDescriptor(panel,dialogTitle);
//        dialogDescriptor.setModal(true);
//        final JButton cancelButton = new JButton(cancelButtonText);
//        cancelButton.setActionCommand("_Cancel_");
//
//        final JButton okButton = new JButton(okButtonText);
//        okButton.setActionCommand("OK");
//
//        dialogDescriptor.setOptions( new Object[] {okButton, cancelButton} );
//
//        dialogDescriptor.setButtonListener(actionListener);
//        dialogDescriptor.setClosingOptions(new Object[]{"NON_EXISTANT_OPTION"});
//
//
//        final JDialog dialog = (JDialog) DialogDisplayer.getDefault().createDialog(dialogDescriptor);
//        dialog.getRootPane().setDefaultButton(okButton);
//        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//        dialog.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent we) {
//                cancelButton.doClick();
//            }
//        });
//
//        if(panel instanceof IOkButtonCallback){
//            ((IOkButtonCallback)panel).setOkButton(okButton);
//        }
//        dialog.setVisible(true);
    }


    public static void closeParentDialogFor(final JComponent component) {
        logger.info("closeParentDialogFor(" + component + ")");
        final JDialog dialog = (JDialog) SwingUtilities.getAncestorOfClass(JDialog.class, component);

        if (dialog != null) {
            dialog.setVisible(false);
        }
    }

    /**
     * Показать диалог по предупреждению о закрытии окна без сохранения
     */
//    public static Object showCloseConfirmationDialog() {
//        NotifyDescriptor nd = new NotifyDescriptor.Confirmation(NbBundle.getMessage(DialogUtil.class, "close.form.confirm"),
//                NbBundle.getMessage(DialogUtil.class, "close.form.warningTitle"),
//                NotifyDescriptor.YES_NO_OPTION, NotifyDescriptor.WARNING_MESSAGE
//        );
//        return DialogDisplayer.getDefault().notify(nd);
//    }
    public static DialogResult showCloseConfirmationDialog() {
        switch (JOptionPane.showConfirmDialog(null, "Данные были изменены.Вы уверены что хотите выйти?", "Внимание", JOptionPane.YES_NO_OPTION)) {
            case JOptionPane.NO_OPTION:
                return DialogResult.NO;
            case JOptionPane.YES_OPTION:
                return DialogResult.YES;
            default:
                break;
        }
        return null;
    }

}
