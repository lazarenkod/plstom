package com.pl.plugins.resources.dal.services.impl;

import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import com.pl.plugins.commons.dal.services.impl.SessionHelper;
import com.pl.plugins.resources.dal.dbo.MetrologyDBO;
import com.pl.plugins.resources.dal.services.IMetrologyService;

import java.util.Collection;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 19.09.2008
 * Time: 18:23:04
 */
public class MetrologyService extends EntityManagementService<MetrologyDBO> implements IMetrologyService {

    private static MetrologyService instance;

    public MetrologyService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public Collection<MetrologyDBO> getMetrologiesByShortName(String shortName) {
        return SessionHelper.getInstance().getSession().createQuery("SELECT c FROM MetrologyDBO c WHERE c.shortName=:shortName").setString("shortName", shortName).list();
    }

}
