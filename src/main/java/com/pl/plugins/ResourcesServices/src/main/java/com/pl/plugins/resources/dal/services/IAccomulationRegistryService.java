package com.pl.plugins.resources.dal.services;

import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.commons.dal.dbo.IStore;
import com.pl.plugins.commons.dal.dbo.IDocument;
import com.pl.plugins.resources.dal.dbo.AccomulationRegistryDBO;
import com.pl.plugins.resources.dal.dbo.ResourceDBO;
import com.pl.plugins.resources.dal.enums.RegistryOperation;

import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 23.09.2008
 * Time: 17:01:34
 */
public interface IAccomulationRegistryService extends IEntityManager<AccomulationRegistryDBO> {
    void releazeAccomulation(Date date, IStore store, RegistryOperation operation, ResourceDBO resource,Double amount, IDocument document);
    void releazeAccomulation(AccomulationRegistryDBO accomulation);
    Double getAmount(Date date,IStore store, ResourceDBO resource);
    Double getAmountByMonthBegin(Date date,IStore store, ResourceDBO resource);
    Double getAmountByMonthEnd(Date date,IStore store, ResourceDBO resource);
    void undoReleazeAccomulation(IDocument document);
    Map<ResourceDBO,Double> getRemainderByStore(Date date,IStore store); 
}
