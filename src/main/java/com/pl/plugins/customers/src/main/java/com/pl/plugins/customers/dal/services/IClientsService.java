package com.pl.plugins.customers.dal.services;

import java.util.Collection;
import com.pl.plugins.customers.dal.dbo.ClientDBO;
import com.pl.plugins.commons.dal.services.IEntityManager;
import org.springframework.dao.DataAccessException;

public interface IClientsService extends IEntityManager<ClientDBO>
{
	Collection<ClientDBO> getClientsByFIO(String fio) throws DataAccessException;    
}
