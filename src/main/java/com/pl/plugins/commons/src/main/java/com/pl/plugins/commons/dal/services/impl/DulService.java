package com.pl.plugins.commons.dal.services.impl;

import com.pl.plugins.commons.dal.dbo.DulDBO;
import com.pl.plugins.commons.dal.services.IDulService;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Vlad
 * Date: 04.10.2008
 * Time: 12:54:03
 * To change this template use File | Settings | File Templates.
 */
public class DulService extends EntityManagementService<DulDBO> implements IDulService{

    public DulService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }
}
