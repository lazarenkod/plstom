package com.pl.plugins.resources.dal.dbo;

import com.pl.plugins.commons.dal.dbo.BaseDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 18:21:28
 */

/**
 * материал
 */
public class ResourceDBO extends BaseDBO {
    public static final String NAME ="name";
    public static final String METROLOGY ="metrology";

    private String name;
    private MetrologyDBO metrology;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetrologyDBO getMetrology() {
        return metrology;
    }

    public void setMetrology(MetrologyDBO metrology) {
        this.metrology = metrology;
    }

     public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ResourceDBO that = (ResourceDBO) o;

        if (metrology != null ? !metrology.equals(that.metrology) : that.metrology != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (metrology != null ? metrology.hashCode() : 0);
        return result;
    }
}
