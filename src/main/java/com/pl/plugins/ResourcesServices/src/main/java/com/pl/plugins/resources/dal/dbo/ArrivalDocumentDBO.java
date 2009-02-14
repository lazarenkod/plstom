package com.pl.plugins.resources.dal.dbo;

import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.commons.dal.dbo.IStore;
import com.pl.plugins.commons.dal.dbo.IDocument;
import com.pl.plugins.commons.dal.enums.DocumentStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 25.09.2008
 * Time: 10:33:14
 */

/**
 * поступление материалов на склад от поставщика
 */
public class ArrivalDocumentDBO extends BaseDBO implements IDocument {
    /**
     * дата
     */
    private Date date;

    /**
     * склад
     */
    private IStore store;

    /**
     * поставщик
     */
    private String supplier;

    /**
     * список материалов
     */
    private List<NormDBO> materials;

    /**
     * —татус документа
     */
    private DocumentStatus status;

    public ArrivalDocumentDBO() {
    }

    public ArrivalDocumentDBO(Date date, IStore store, String supplier, List<NormDBO> materials) {
        this.date = date;
        this.store = store;
        this.supplier = supplier;
        this.materials = materials;
    }

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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public List<NormDBO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<NormDBO> materials) {
        this.materials = materials;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ArrivalDocumentDBO that = (ArrivalDocumentDBO) o;

        if (!date.equals(that.date)) return false;
        if (materials != null ? !materials.equals(that.materials) : that.materials != null) return false;
        if (!store.equals(that.store)) return false;
        if (!supplier.equals(that.supplier)) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + store.hashCode();
        result = 31 * result + supplier.hashCode();
        result = 31 * result + (materials != null ? materials.hashCode() : 0);
        return result;
    }

    public String getDocumentName() {
        return null;
    }
}
