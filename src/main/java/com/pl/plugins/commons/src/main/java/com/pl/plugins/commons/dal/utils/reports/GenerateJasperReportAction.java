package com.pl.plugins.commons.dal.utils.reports;

import com.pl.plugins.commons.dal.dbo.JasperReportInputCommonDBO;
import com.pl.plugins.commons.ui.views.impl.JasperReportViewerForm;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 03.10.2008
 * Time: 15:22:09
 */

/**
 * Класс для работы с отчетами
 * use
 * reportAction.actionPerformed(new ActionEvent(((JPanel)getTopComponent()), 0, "Печать"));
 */
public class GenerateJasperReportAction extends AbstractAction {

    private JasperReportViewerForm reportViewerForm;
    private JasperReportInputCommonDBO jasperReportInputCommonDBO;

    public GenerateJasperReportAction(JasperReportInputCommonDBO jasperReportInputCommonDBO) {
        this.jasperReportInputCommonDBO = jasperReportInputCommonDBO;
    }

    protected void processReport(JasperPrint jasperPrint, JTabbedPane rootContainer) {
        reportViewerForm = new JasperReportViewerForm();
        if (!StringUtils.isEmpty(jasperReportInputCommonDBO.getReportViewerTitle())) {
            reportViewerForm.setName(jasperReportInputCommonDBO.getReportViewerTitle());
        }
        rootContainer.addTab(reportViewerForm.getName(), reportViewerForm);

//        rootContainer.add(reportViewerForm);
//        reportViewerForm.viewReport(jasperPrint);
        rootContainer.requestFocus();
        try {
            if (jasperPrint.getPages().isEmpty() && jasperReportInputCommonDBO.getCloseViewerAfterProcess()) {
                rootContainer.remove(reportViewerForm);
            }
        } catch (Exception ex) {
            rootContainer.remove(reportViewerForm);
        }
    }

    protected void generateReport(JTabbedPane rootContainer) {
        try {
            JasperPrint report = null;//PresentationManager.StubFactory.create(IJasperReportServiceFacade.class).generateJasperReport(jasperReportInputCommonDBO);
            if (report != null) {
                processReport(report, rootContainer);
                if (jasperReportInputCommonDBO.getPrintReport()) {
                    PrintReportService.printReport(report);
                }
            } else {
            }
        } catch (/*MissingResource*/Exception e) {

            JOptionPane.showMessageDialog(
                    null
                    , "Error"
                    , "Error while printing report"
                    , JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JTabbedPane) {
            JTabbedPane tc = (JTabbedPane) e.getSource();
            tc.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                generateReport(tc);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                tc.setCursor(Cursor.getDefaultCursor());
            }
        } else {
            generateReport(null);
        }
    }
}
