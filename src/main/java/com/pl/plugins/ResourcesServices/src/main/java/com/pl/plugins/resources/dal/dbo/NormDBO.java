package com.pl.plugins.resources.dal.dbo;

import com.pl.plugins.commons.dal.dbo.BaseDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 20.09.2008
 * Time: 14:55:03
 */
public class NormDBO extends BaseDBO {
    public static final String RESOURCE ="resource";
    public static final String COUNT ="count";
    
    private ResourceDBO resource;
    private Double count;

    /**
     * Getter for property 'resource'.
     *
     * @return Value for property 'resource'.
     */
    public ResourceDBO getResource() {
        return resource;
    }

    /**
     * Setter for property 'resource'.
     *
     * @param resource Value to set for property 'resource'.
     */
    public void setResource(ResourceDBO resource) {
        this.resource = resource;
    }

    /**
     * Getter for property 'count'.
     *
     * @return Value for property 'count'.
     */
    public Double getCount() {
        return count;
    }

    /**
     * Setter for property 'count'.
     *
     * @param count Value to set for property 'count'.
     */
    public void setCount(Double count) {
        this.count = count;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        NormDBO normDBO = (NormDBO) o;

        if (!count.equals(normDBO.count)) return false;
        if (!resource.equals(normDBO.resource)) return false;

        return true;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + resource.hashCode();
        result = 31 * result + count.hashCode();
        return result;
    }

    public String toString() {
        return "NormDBO{" +
                "resource=" + resource +
                ", count=" + count +
                '}';
    }
}
