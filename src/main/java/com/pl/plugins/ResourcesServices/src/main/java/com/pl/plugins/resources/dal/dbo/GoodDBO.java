package com.pl.plugins.resources.dal.dbo;

import com.pl.plugins.commons.dal.dbo.BaseDBO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 17:26:20
 */

/**
 * Услуга
 */

public class GoodDBO extends BaseDBO {
    public static final String NAME ="name";
    public static final String NORMS ="norms";
    
    private String name;
    private List<NormDBO> norms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NormDBO> getNorms() {
        return norms;
    }

    public void setNorms(List<NormDBO> norms) {
        this.norms = norms;
    }
}
