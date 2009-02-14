package com.pl.plugins.commons.dal.utils.reports;

import net.sf.jasperreports.engine.JasperPrint;

import javax.transaction.SystemException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 03.10.2008
 * Time: 11:00:28
 */
public interface IJasperReportService {
    JasperPrint generateReport(String reportTemplate, Map parameters, Collection dataBeans) throws SystemException;
//    Collection buildJasperReportData(Object bean) throws SystemException;
    
//    @Transactional(propagation = Propagation.REQUIRED)
//    JasperPrint generateReportWithSubreport(String reportTemplate,
//                                                   Map parameters,
//                                                   Collection dataBeans,
//                                                   String subReportTemplate,
//                                                   String subReportDSParamName,
//                                                   Collection subReportDataBeans) throws SystemException;
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    JasperPrint generateReportWithSubreports(String reportTemplate,
//                                                    Map parameters,
//                                                    Collection dataBeans,
//                                                    List<JasperReportInputCommonDBO> subReports) throws SystemException;



//    @Transactional(propagation = Propagation.REQUIRED)
//    JasperPrint generateJasperReport(JasperReportInputCommonDBO dto) throws SystemException;

//    @Transactional(propagation = Propagation.REQUIRED)
//    List<JasperPrint> generateJasperReports(JasperReportInputCommonDBO dto) throws SystemException;
}
