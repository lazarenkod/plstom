package com.pl.plugins.commons.dal.utils.reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.transaction.SystemException;
import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * Copyright 2006 OTR Company.
 *
 * Licensed under the OTR License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.otr.ru/licenses/GBA/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * JasperReportServiceFacade.java
 *
 * Created on 18 march 2007
 *
 */


/**
 * @author bilyalov.marat
 */
//public class JasperReportService {}
public class JasperReportService implements InitializingBean, IJasperReportService {

    private Log log = LogFactory.getLog(getClass());
    public static final String SUBREPORT_TEMPLATE_PARAM_NAME = "SUBREPORT_TEMPLATE";
    private Map<String, String> jasperConfig;
    private String reportTemplateFolder;

    public JasperReportService() {
    }


    //    @Transactional(propagation = Propagation.REQUIRED)
    public JasperPrint generateReport(String reportTemplate, Map parameters, Collection dataBeans) throws SystemException {
        try {
            JasperReport jasperReport = loadAndCompileReport(reportTemplate);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataBeans);
            log.debug("dataBeans = " + ReflectionToStringBuilder.reflectionToString(dataBeans));
            log.debug("dataSource = " + ReflectionToStringBuilder.reflectionToString(dataSource));
            log.debug("reportParams = " + parameters);
            log.debug("generateReport");
            return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        } catch (JRException e) {
            log.error("some exception occured", e);
            throw new SystemException(e.getMessage());
        }
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    public JasperPrint generateReportWithSubreport(String reportTemplate,
//                                                   Map parameters,
//                                                   Collection dataBeans,
//                                                   String subReportTemplate,
//                                                   String subReportDSParamName,
//                                                   Collection subReportDataBeans) throws SystemException {
//
//        try {
//            JasperReport subReport = loadAndCompileReport(subReportTemplate);
//            JRBeanCollectionDataSource subReportDataSource = uinew JRBeanCollectionDataSource(subReportDataBeans);
//            log.debug("subReportDataBeans = " + ReflectionToStringBuilder.reflectionToString(subReportDataBeans));
//            log.debug("subReportDataSource = " + ReflectionToStringBuilder.reflectionToString(subReportDataSource));
//
//            parameters.put(subReportDSParamName, subReportDataSource);
//            parameters.put(SUBREPORT_TEMPLATE_PARAM_NAME, subReport);
//            return generateReport(reportTemplate, parameters, dataBeans);
//        } catch (JRException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        }
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public JasperPrint generateReportWithSubreports(String reportTemplate,
//                                                    Map parameters,
//                                                    Collection dataBeans,
//                                                    List<JasperReportInputCommonDBO> subReports) throws SystemException {
//
//        try {
//            int i = 0;
//            for (JasperReportInputCommonDBO entity : subReports) {
//                String rtDto = uinew String();
//                BeanUtils.copyProperties(rtDto, entity.getReportTemplate());
//                JasperReport subReport = loadAndCompileReport(rtDto);
//                JRBeanCollectionDataSource subReportDataSource =
//                        uinew JRBeanCollectionDataSource(
//                                entity.getSubReportDataSource_beanCollection());
//                log.debug("subReportDataBeans = "
//                        + ReflectionToStringBuilder.reflectionToString(
//                        entity.getSubReportDataSource_beanCollection()));
//                log.debug("subReportDataSource = "
//                        + ReflectionToStringBuilder.reflectionToString(
//                        subReportDataSource));
//
//                parameters.put(entity.getSubReportDataSourceName(), subReportDataSource);
//                parameters.put("SUBREPORT_TEMPLATE" + uinew Integer(i++).toString(), subReport);
//            }
//            return generateReport(reportTemplate, parameters, dataBeans);
//        } catch (JRException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        } catch (InvocationTargetException e) {
//            log.error(e);
//            throw uinew SystemException(e.getMessage());
//        } catch (IllegalAccessException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        }
//    }


    private JasperReport loadAndCompileReport(String reportTemplate) throws JRException, SystemException {
        String reportXML = getXMLTemplate(reportTemplate);

        if (reportXML == null) {
            throw new SystemException("Report not found: [" + reportTemplate + "]");
        }

        try {
            JasperReport jasperReport;

            JasperDesign jd = JRXmlLoader.load(new ByteArrayInputStream(reportXML.getBytes("UTF-8")));
            jd.setWhenNoDataType(JasperDesign.WHEN_NO_DATA_TYPE_ALL_SECTIONS_NO_DETAIL);
            jasperReport = JasperCompileManager.compileReport(jd);
            return jasperReport;
        } catch (UnsupportedEncodingException e) {
            log.error(e);
            throw new SystemException(e.getMessage());
        }
    }

    private String getXMLTemplate(String reportTemplateFileName) {
        String fileName = reportTemplateFolder + "/" + reportTemplateFileName;
        BufferedReader in;
        String template = "";
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while (in.ready()) {
                template += in.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return template;
    }

//    public Collection buildJasperReportData(Object bean) throws SystemException {
//        try {
//            JasperReportInputCommonDBO dto = (JasperReportInputCommonDBO) bean;
//            Collection coll = Collections.EMPTY_LIST;
//            Method[] methods = this.getClass().getDeclaredMethods();
//            Object obj = null;
//            for (Method m : methods) {
//                if (m.getName().compareTo("get" + dto.getReportHelperName()) == 0) {
//                    obj = m.invoke(this, uinew Object[]{});
//                    break;
//                }
//            }
//            if (obj != null) {
//                Method serviceMethodName = obj.getClass().getMethod(dto.getReportHelperMethodName(),
//                        uinew Class[]{Class.forName("java.lang.Object")}
//                );
//                coll = (Collection) serviceMethodName.invoke(obj, uinew Object[]{dto});
//            } else {
//                throw uinew ClassNotFoundException("Report helper not found: " + dto.getReportHelperName());
////                log.error("JasperReportService.buildJasperReportData: Can't complete action due to No ReportHelper found.");
//            }
//            return coll;
//        } catch (IllegalAccessException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        } catch (InvocationTargetException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        } catch (ClassNotFoundException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        } catch (NoSuchMethodException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        }
//
//    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    public JasperPrint generateJasperReport(JasperReportInputCommonDBO dto) throws SystemException {
//        try {
//            JasperPrint result = null;
//
//            dto.setSessionUserId(SecurityProfile.getCurrentUser().getId());
//            String reportTemplate = uinew String();
//            BeanUtils.copyProperties(reportTemplate, dto);
//            Collection beans = buildJasperReportData(dto);
//
//            if (dto.getReportParameters() == null) {
//                dto.setReportParameters(uinew HashMap<String, Object>());
//            }
//
//            if (dto.getSubReports() != null && dto.getSubReports().size() != 0) {
//
//                List<JasperReportInputCommonDBO> subReports = uinew LinkedList<JasperReportInputParamDTO>();
//
//                for (JasperReportInputCommonDBO subDTO : dto.getSubReports()) {
//                    String subReportTemplate = uinew String();
//                    JasperReportInputCommonDBO p = uinew JasperReportInputCommonDBO();
//
//                    if (StringUtils.isNotEmpty(subDTO.getReportHelperName()) && StringUtils.isNotEmpty(subDTO.getReportHelperMethodName())) {
//						Collection subReportBeans = buildJasperReportData(subDTO);
//						p.setSubReportDataSource_beanCollection(subReportBeans);
//						log.debug("subReportBeans = " + subReportBeans);
//					}
//
//                    BeanUtils.copyProperties(subReportTemplate, subDTO);
//                    p.setReportTemplate(subReportTemplate);
//                    p.setSubReportDataSourceName(subDTO.getSubReportDataSourceName());
//                    subReports.add(p);
//                }
//
//                result = generateReportWithSubreports(reportTemplate, dto.getReportParameters(), beans, subReports);
//
//            } else {
//                result = generateReport(reportTemplate, dto.getReportParameters(), beans);
//            }
//
//            return result;
//        } catch (IllegalAccessException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        } catch (InvocationTargetException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        }
//    }
//
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public List<JasperPrint> generateJasperReports(JasperReportInputCommonDBO dto) throws SystemException {
//        try {
//            List<JasperPrint> reports = uinew LinkedList<JasperPrint>();
//            List<JasperReportInputCommonDBO> inputs = dto.getInputs();
//            Long sessionUserId = null;
//            sessionUserId = SecurityProfile.getCurrentUser().getId();
//            if (inputs != null && inputs.size() != 0) {
//                for (JasperReportInputCommonDBO dto2 : inputs) {
//                    String reportTemplate = uinew String();
//                    JasperPrint result = null;
//                    dto2.setSessionUserId(sessionUserId);
//                    BeanUtils.copyProperties(reportTemplate, dto2);
//                    Collection beans = buildJasperReportData(dto2);
//                    result = generateReport(reportTemplate, dto2.getReportParameters(), beans);
//                    reports.add(result);
//                }
//            }
//            return reports;
//        } catch (IllegalAccessException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        } catch (InvocationTargetException e) {
//            log.error("some exception occured", e);
//            throw uinew SystemException(e.getMessage());
//        }
//    }

    public Map<String, String> getJasperConfig() {
        return jasperConfig;
    }

    public void setJasperConfig(Map<String, String> jasperConfig) {
        this.jasperConfig = jasperConfig;
    }

    public void afterPropertiesSet() throws Exception {
        Properties systemProperties = System.getProperties();
        systemProperties.putAll(jasperConfig);
    }

    public String getReportTemplateFolder() {
        return reportTemplateFolder;
    }

    public void setReportTemplateFolder(String reportTemplateFolder) {
        this.reportTemplateFolder = reportTemplateFolder;
    }

}
