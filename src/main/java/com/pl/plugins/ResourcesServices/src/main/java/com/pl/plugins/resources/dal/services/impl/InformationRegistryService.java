package com.pl.plugins.resources.dal.services.impl;

import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import com.pl.plugins.commons.dal.services.impl.SessionHelper;
import com.pl.plugins.resources.dal.dbo.InformationRegistryDBO;
import com.pl.plugins.resources.dal.dbo.GoodDBO;
import com.pl.plugins.resources.dal.services.IInformationRegistryService;

import java.util.*;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: јдминистратор
 * Date: 24.09.2008
 * Time: 1:14:33
 */

/**
 * –егистр сведений
 */
public class InformationRegistryService extends EntityManagementService<InformationRegistryDBO> implements IInformationRegistryService {

    public InformationRegistryService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    /**
     * ƒобавить данные в регистр сведений
     */
    public void addInformation(Date date, GoodDBO good, Double cost) {
        InformationRegistryDBO info = new InformationRegistryDBO(date, good, cost);
        Collection<InformationRegistryDBO> infList = SessionHelper.getInstance().getSession().createQuery("SELECT c FROM InformationRegistryDBO c WHERE c.date=:date AND c.good=:good").setDate("date", date).setEntity("good", good).list();
        if (infList != null && !infList.isEmpty())
            addOrUpdate(info);
    }

    /**
     * получить срез последней цены на указанную дату
     */
    public Double getLatestCost(Date date, GoodDBO good) {
        List<InformationRegistryDBO> infList = SessionHelper.getInstance().getSession().createQuery("SELECT c FROM InformationRegistryDBO c WHERE c.date<=:date AND c.good=:good").setDate("date", date).setEntity("good", good).list();
        if (infList != null && !infList.isEmpty())
            return infList.get(infList.size() - 1).getCost();
        return null;
    }

    /**
     * ѕолучить срезы цен всех услуг на указанную дату
     */
    public Map<GoodDBO, Double> getAllByDate(Date date) {
        List<InformationRegistryDBO> infList = SessionHelper.getInstance().getSession().createQuery("SELECT c FROM InformationRegistryDBO c WHERE c.date<=:date").setDate("date", date).list();
        Map<GoodDBO, Double> result = new HashMap<GoodDBO, Double>();
        for (int i = 0; i < infList.size(); i++) {
            InformationRegistryDBO inf = infList.get(i);
            result.put(inf.getService(), inf.getCost());
        }
        return result;
    }

    /**
     * ѕолучить срезы цен всех услуг между указанными датами
     * @param date1
     * @param date2
     * @return
     */
    public Map<GoodDBO, Double> getAllBetweenDates(Date date1, Date date2) {
        List<InformationRegistryDBO> infList = SessionHelper.getInstance().getSession().createQuery("SELECT c FROM InformationRegistryDBO c WHERE c.date<=:date2 AND c.date>=date1").setDate("date1", date1).setDate("date2",date2).list();
        Map<GoodDBO, Double> result = new HashMap<GoodDBO, Double>();
        for (int i = 0; i < infList.size(); i++) {
            InformationRegistryDBO inf = infList.get(i);
            result.put(inf.getService(), inf.getCost());
        }
        return result;
    }
   
}
