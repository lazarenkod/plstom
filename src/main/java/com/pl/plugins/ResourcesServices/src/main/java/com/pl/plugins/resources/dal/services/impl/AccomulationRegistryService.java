package com.pl.plugins.resources.dal.services.impl;

import com.pl.plugins.commons.dal.dbo.IStore;
import com.pl.plugins.commons.dal.dbo.IDocument;
import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import com.pl.plugins.commons.dal.services.impl.SessionHelper;
import com.pl.plugins.resources.dal.dbo.AccomulationRegistryDBO;
import com.pl.plugins.resources.dal.dbo.ResourceDBO;
import com.pl.plugins.resources.dal.enums.RegistryOperation;
import com.pl.plugins.resources.dal.services.IAccomulationRegistryService;

import java.util.*;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 23.09.2008
 * Time: 18:06:49
 */
public class AccomulationRegistryService extends EntityManagementService<AccomulationRegistryDBO> implements IAccomulationRegistryService {

    public AccomulationRegistryService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }


    public void releazeAccomulation(Date date, IStore store, RegistryOperation operation, ResourceDBO resource, Double amount, IDocument document) {
        AccomulationRegistryDBO accum = new AccomulationRegistryDBO(date, store, operation, resource, amount, document);
        addOrUpdate(accum);
    }

    public void releazeAccomulation(AccomulationRegistryDBO accomulation) {
        addOrUpdate(accomulation);
    }

    public Double getAmount(Date date, IStore store, ResourceDBO resource) {
        Collection<AccomulationRegistryDBO> accumList = SessionHelper.getInstance().getSession().createQuery("SELECT c FROM AccomulationRegistryDBO c WHERE c.date<=:date AND c.store=:store AND c.resource=:resource").setDate("date", date).setEntity("store", store).setEntity("resource", resource).list();
        Double ammount = 0.0;
        for (Iterator<AccomulationRegistryDBO> accomulationRegistryDBOIterator = accumList.iterator(); accomulationRegistryDBOIterator.hasNext();) {
            AccomulationRegistryDBO accum = accomulationRegistryDBOIterator.next();
            ammount += accum.getAmount();
        }
        return ammount;
    }

    public Double getAmountByMonthBegin(Date date, IStore store, ResourceDBO resource) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getAmount(calendar.getTime(), store, resource);
    }

    public Double getAmountByMonthEnd(Date date, IStore store, ResourceDBO resource) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getAmount(calendar.getTime(), store, resource);
    }

    /**
     *  проводит отмену вставки данных в регистр
     * @param document Документ, для которого нуюно отменить проведение
     */
    public void undoReleazeAccomulation(IDocument document) {
       List<AccomulationRegistryDBO> accumList = SessionHelper.getInstance().getSession().createQuery("SELECT c FROM AccomulationRegistryDBO c WHERE c.document=:document").setEntity("document", document).list();
        for (Iterator<AccomulationRegistryDBO> accomulationRegistryDBOIterator = accumList.iterator(); accomulationRegistryDBOIterator.hasNext();) {
            AccomulationRegistryDBO accomulationRegistryDBO = accomulationRegistryDBOIterator.next();
            remove(accomulationRegistryDBO);    
        }
    }

    public Map<ResourceDBO, Double> getRemainderByStore(Date date, IStore store) {
        Collection<AccomulationRegistryDBO> accumList = SessionHelper.getInstance().getSession().createQuery("SELECT c FROM AccomulationRegistryDBO c WHERE c.date<=:date AND c.store=:store").setDate("date", date).setEntity("store", store).list();
        HashMap<ResourceDBO, Double> remainders = new HashMap<ResourceDBO, Double>();
        for (Iterator<AccomulationRegistryDBO> accomulationRegistryDBOIterator = accumList.iterator(); accomulationRegistryDBOIterator.hasNext();) {
            AccomulationRegistryDBO accum = accomulationRegistryDBOIterator.next();
            remainders.put(accum.getResource(), accum.getAmount());
        }
        return remainders;
    }

}
