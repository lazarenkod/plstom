package com.pl.plugins.resources.dal.services;

import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.resources.dal.dbo.MetrologyDBO;


import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 19.09.2008
 * Time: 18:20:53
 */
public interface IMetrologyService extends IEntityManager<MetrologyDBO> {
    Collection<MetrologyDBO> getMetrologiesByShortName(String shortName);
}
