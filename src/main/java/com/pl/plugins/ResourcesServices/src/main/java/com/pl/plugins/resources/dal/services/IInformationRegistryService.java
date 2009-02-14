package com.pl.plugins.resources.dal.services;

import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.resources.dal.dbo.InformationRegistryDBO;
import com.pl.plugins.resources.dal.dbo.GoodDBO;

import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 23.09.2008
 * Time: 17:25:46
 */
public interface IInformationRegistryService extends IEntityManager<InformationRegistryDBO> {
    void addInformation(Date date, GoodDBO good, Double cost);
    Double getLatestCost(Date date, GoodDBO good);
    Map<GoodDBO,Double> getAllByDate(Date date);
    Map<GoodDBO,Double> getAllBetweenDates(Date date1,Date date2);
}
