package com.pl.plugins.customers.dal.services.impl;

import com.pl.plugins.customers.dal.dbo.ClientDBO;
import com.pl.plugins.customers.dal.services.IClientsService;
import com.pl.plugins.commons.dal.services.impl.SessionHelper;
import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Collection;

public class ClientsService extends EntityManagementService<ClientDBO> implements IClientsService
{
    public ClientsService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public Collection<ClientDBO> getClientsByFIO(String lname) throws DataAccessException {
        return SessionHelper.getInstance().getSession().createQuery("SELECT c FROM ClientDBO c WHERE c.lname LIKE :lname").setString("lname",lname).list();
    }
}
