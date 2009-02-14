package com.pl.plugins.commons.dal.services.impl;

import com.pl.plugins.commons.dal.dbo.HumanDBO;
import com.pl.plugins.commons.dal.services.IHumanService;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 19.09.2008
 * Time: 16:39:59
 */
public class HumanService extends EntityManagementService<HumanDBO> implements IHumanService{

    public HumanService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public Collection<HumanDBO> getHumanByFIO(String fio) throws DataAccessException {
        return SessionHelper.getInstance().getSession().createQuery("SELECT c FROM HumanDBO c WHERE c.lname LIKE :lname").setString("lname",fio).list();
    }
}
