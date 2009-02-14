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
 * Time: 11:51:35
 */
public class MovingDocumentDBO extends BaseDBO implements IDocument{
    /**
     * дата
     */
    private Date date;
    /**
     * склад источник
     */
    private IStore sourceStore;
    /**
     * склад назначение
     */
    private IStore destinationStore;
    /**
     * список материалов
     */
    private List<NormDBO> materials;

    /**
     * статус
     */
    private DocumentStatus status;


    public MovingDocumentDBO() {
    }

    public MovingDocumentDBO(Date date, IStore sourceStore, IStore destinationStore, List<NormDBO> materials) {
        this.date = date;
        this.sourceStore = sourceStore;
        this.destinationStore = destinationStore;
        this.materials = materials;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public IStore getSourceStore() {
        return sourceStore;
    }

    public void setSourceStore(IStore sourceStore) {
        this.sourceStore = sourceStore;
    }

    public IStore getDestinationStore() {
        return destinationStore;
    }

    public void setDestinationStore(IStore destinationStore) {
        this.destinationStore = destinationStore;
    }

    public List<NormDBO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<NormDBO> materials) {
        this.materials = materials;
    }

    public String getDocumentName() {
        return null;
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

        MovingDocumentDBO that = (MovingDocumentDBO) o;

        if (!date.equals(that.date)) return false;
        if (!destinationStore.equals(that.destinationStore)) return false;
        if (materials != null ? !materials.equals(that.materials) : that.materials != null) return false;
        if (!sourceStore.equals(that.sourceStore)) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + sourceStore.hashCode();
        result = 31 * result + destinationStore.hashCode();
        result = 31 * result + (materials != null ? materials.hashCode() : 0);
        return result;
    }
}
