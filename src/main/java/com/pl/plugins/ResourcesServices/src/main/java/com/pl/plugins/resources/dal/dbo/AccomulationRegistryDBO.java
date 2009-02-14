package com.pl.plugins.resources.dal.dbo;

import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.commons.dal.dbo.IStore;
import com.pl.plugins.commons.dal.dbo.IDocument;
import com.pl.plugins.resources.dal.enums.RegistryOperation;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 23.09.2008
 * Time: 16:01:44
 */

/**
 * Регистр накопления
 */
public class AccomulationRegistryDBO extends BaseDBO {
    /**
     * Дата операции
     */
    private Date date;
    /**
     * Склад накопления
     */
    private IStore store;
    /**
     * Операция- либо приход, либо расход
     */
    private RegistryOperation operation;
    /**
     * Материал
     */
    private ResourceDBO resource;
    /**
     * Количество
     */
    private Double amount;
    /**
     * Докумен-регистратор(Инициатор)  накопления
     */
    private IDocument document;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public IStore getStore() {
        return store;
    }

    public void setStore(IStore store) {
        this.store = store;
    }

    public RegistryOperation getOperation() {
        return operation;
    }

    public void setOperation(RegistryOperation operation) {
        this.operation = operation;
    }

    public ResourceDBO getResource() {
        return resource;
    }

    public void setResource(ResourceDBO resource) {
        this.resource = resource;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public IDocument getDocument() {
        return document;
    }

    public void setDocument(IDocument document) {
        this.document = document;
    }

    public AccomulationRegistryDBO() {
    }

    public AccomulationRegistryDBO(Date date, IStore store, RegistryOperation operation, ResourceDBO resource, Double amount, IDocument document) {
        this.date = date;
        this.store = store;
        this.operation = operation;
        this.resource = resource;
        this.amount = amount;
        this.document = document;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AccomulationRegistryDBO that = (AccomulationRegistryDBO) o;

        if (!amount.equals(that.amount)) return false;
        if (!date.equals(that.date)) return false;
        if (!document.equals(that.document)) return false;
        if (operation != that.operation) return false;
        if (!resource.equals(that.resource)) return false;
        if (!store.equals(that.store)) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + store.hashCode();
        result = 31 * result + operation.hashCode();
        result = 31 * result + resource.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + document.hashCode();
        return result;
    }
}
