package com.pl.plugins.commons.dal.services;

import com.pl.plugins.commons.dal.dbo.HumanDBO;
import java.util.Collection;
import org.springframework.dao.DataAccessException;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 19.09.2008
 * Time: 16:29:34
 */
public interface IHumanService extends IEntityManager<HumanDBO> {
     Collection<HumanDBO> getHumanByFIO(String fio) throws DataAccessException;
    
}
