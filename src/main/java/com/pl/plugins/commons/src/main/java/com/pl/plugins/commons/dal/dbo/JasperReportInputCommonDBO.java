package com.pl.plugins.commons.dal.dbo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Collection;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 03.10.2008
 * Time: 16:51:40
 */

/**
 * базовый класс для печати отчетов
 */
public class JasperReportInputCommonDBO extends BaseDBO {

    private String name;
    private String version;
    private Map reportParameters;
    private Object reportInputDTO;
    private Collection reportDataSource_beanCollection;
    private Long sessionUserId;
    private String whenNoData;
    private Boolean printReport = Boolean.FALSE;
    private Boolean closeViewerAfterProcess = Boolean.FALSE;
    private String reportViewerTitle;

    private static final long serialVersionUID = 896890936778209655L;

    public Boolean getPrintReport() {
		return printReport;
	}

	public void setPrintReport(Boolean printReport) {
		this.printReport = printReport;
	}

	/**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the reportDataSource_bean
     */
    public Object getReportInputDTO() {
        return reportInputDTO;
    }

    /**
     * @param reportInputDTO the reportDataSource_bean to set
     */
    public void setReportInputDTO(Object reportInputDTO) {
        this.reportInputDTO = reportInputDTO;
    }

    /**
     * @return the reportDataSource_beanCollection
     */
    public Collection getReportDataSource_beanCollection() {
        return reportDataSource_beanCollection;
    }

    /**
     * @param reportDataSource_beanCollection
     *         the reportDataSource_beanCollection to set
     */
    public void setReportDataSource_beanCollection(
        Collection reportDataSource_beanCollection) {
        this.reportDataSource_beanCollection = reportDataSource_beanCollection;
    }

    /**
     * @return the reportParameters
     */
    public Map getReportParameters() {
        return reportParameters;
    }

    /**
     * @param reportParameters the reportParameters to set
     */
    public void setReportParameters(Map reportParameters) {
        this.reportParameters = reportParameters;
    }

    /**
     * @return the sessionUserId
     */
    public Long getSessionUserId() {
        return sessionUserId;
    }

    /**
     * @param sessionUserId the sessionUserId to set
     */
    public void setSessionUserId(Long sessionUserId) {
        this.sessionUserId = sessionUserId;
    }

    public String getWhenNoData() {
        return whenNoData;
    }

    public void setWhenNoData(String whenNoData) {
        this.whenNoData = whenNoData;
    }

    public String toString() {
        return new ToStringBuilder(this).
        append("name", name).
        append("version", version).
        append("reportParameters", reportParameters).
        append("reportInputDTO", reportInputDTO).
        append("reportDataSource_beanCollection", reportDataSource_beanCollection).
        append("sessionUserId", sessionUserId).
        append("printReport", printReport).
        append("whenNoData", whenNoData).
        toString();
    }

	public String getReportViewerTitle() {
		return reportViewerTitle;
	}

	public void setReportViewerTitle(String reportViewerTitle) {
		this.reportViewerTitle = reportViewerTitle;
	}

	public Boolean getCloseViewerAfterProcess() {
		return closeViewerAfterProcess;
	}

	public void setCloseViewerAfterProcess(Boolean closeViewerAfterProcess) {
		this.closeViewerAfterProcess = closeViewerAfterProcess;
	}
}
