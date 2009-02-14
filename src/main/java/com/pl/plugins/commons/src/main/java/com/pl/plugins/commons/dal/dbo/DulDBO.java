package com.pl.plugins.commons.dal.dbo;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 17:03:37
 */

/**
 * Документ удостоверяющий личность
 */
public class DulDBO extends BaseDBO {

    /**
     * Названия полей для биндинга.
     */
    public static final String NUM ="num";
    public static final String SERIES ="series";
    public static final String TYPE ="type";
    public static final String ISSUE_DATE ="issue_date";
    public static final String ISSUER ="issuer";
    public static final String ISSUE_DIVISION ="issue_division";

    private String num = "";
    private String series = "";
    private String type = "";
    private Date issue_date = new Date();
    private String issuer = "";
    private String issue_division = "";
    private HumanDBO human = null;

    public DulDBO(HumanDBO human) {
        this.human = human;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssue_division() {
        return issue_division;
    }

    public void setIssue_division(String issue_division) {
        this.issue_division = issue_division;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DulDBO dulDBO = (DulDBO) o;

        if (!issue_date.equals(dulDBO.issue_date)) return false;
        if (issue_division != null ? !issue_division.equals(dulDBO.issue_division) : dulDBO.issue_division != null)
            return false;
        if (issuer != null ? !issuer.equals(dulDBO.issuer) : dulDBO.issuer != null) return false;
        if (!num.equals(dulDBO.num)) return false;
        if (!series.equals(dulDBO.series)) return false;
        if (!type.equals(dulDBO.type)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = num.hashCode();
        result = 31 * result + series.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + issue_date.hashCode();
        result = 31 * result + (issuer != null ? issuer.hashCode() : 0);
        result = 31 * result + (issue_division != null ? issue_division.hashCode() : 0);
        return result;
    }

    public HumanDBO getHuman() {
        return human;
    }

    public void setHuman(HumanDBO human) {
        this.human = human;
    }

    @Override
    public String toString() {
        return "DulDBO{" +
                "num='" + num + '\'' +
                ", series='" + series + '\'' +
                ", type='" + type + '\'' +
                ", issue_date=" + issue_date +
                ", issuer='" + issuer + '\'' +
                ", issue_division='" + issue_division + '\'' +
                ", human=" + human +
                '}';
    }
}
