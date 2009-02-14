package com.pl.plugins.commons.dal.utils.reports;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 03.10.2008
 * Time: 17:42:10
 */
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;


public class PrintReportService {

    private static PrintReportService instance;


    private PrintReportService() {
    }

    public static void printReport(final JasperPrint jasperPrint) {
        if (instance == null) {
            instance = new PrintReportService();
        }
        instance.print(jasperPrint);
    }

    private void print(final JasperPrint jasperPrint) {
        Thread thread =
            new Thread(
                new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            JasperPrintManager.printReport(jasperPrint, false);
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
            );

        thread.start();
    }
}
