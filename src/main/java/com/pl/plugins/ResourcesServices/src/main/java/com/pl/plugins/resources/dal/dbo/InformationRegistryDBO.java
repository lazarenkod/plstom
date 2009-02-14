package com.pl.plugins.resources.dal.dbo;

import com.pl.plugins.commons.dal.dbo.BaseDBO;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 23.09.2008
 * Time: 16:35:46
 */

/**
 * Регистр сведений. Дата и Услуга идентифицируют однозначно запись
 * в регистре. Поле Цена отражает цену услуги на заданную дату
 */
public class InformationRegistryDBO extends BaseDBO {
    /**
     * Дата
     */
    private Date date;
    /**
     * Услуга
     */
    private GoodDBO good;
    /**
     * Цена
     */
    private Double cost;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public GoodDBO getService() {
        return good;
    }

    public void setService(GoodDBO good) {
        this.good = good;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public InformationRegistryDBO() {
    }

    public InformationRegistryDBO(Date date, GoodDBO good, Double cost) {
        this.date = date;
        this.good = good;
        this.cost = cost;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        InformationRegistryDBO that = (InformationRegistryDBO) o;

        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        if (!date.equals(that.date)) return false;
        if (!good.equals(that.good)) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + good.hashCode();
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}