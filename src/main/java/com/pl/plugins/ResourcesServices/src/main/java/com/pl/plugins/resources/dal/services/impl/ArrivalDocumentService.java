package com.pl.plugins.resources.dal.services.impl;

import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import com.pl.plugins.commons.dal.enums.DocumentStatus;
import com.pl.plugins.resources.dal.dbo.ArrivalDocumentDBO;
import com.pl.plugins.resources.dal.dbo.NormDBO;
import com.pl.plugins.resources.dal.services.IArrivalDocumentService;
import com.pl.plugins.resources.dal.enums.RegistryOperation;

import java.util.Iterator;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 25.09.2008
 * Time: 14:57:53
 */
public class ArrivalDocumentService extends EntityManagementService<ArrivalDocumentDBO> implements IArrivalDocumentService{

    public ArrivalDocumentService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    /**
     * ƒобавить поступление
     */
    public void addArrival(ArrivalDocumentDBO arrival) {
        arrival.setStatus(DocumentStatus.NEW);
        addOrUpdate(arrival);
        arrival.setStatus(DocumentStatus.SAVED);
        postArrival(arrival);
    }

    /**
     * провести поступление
     */
    public void postArrival(ArrivalDocumentDBO arrival) {
       //AccomulationRegistryService accService = AccomulationRegistryService.getInstance();
        try {
                for (Iterator<NormDBO> iterRes = arrival.getMaterials().iterator(); iterRes.hasNext();) {
                    NormDBO normDBO = iterRes.next();
                    //приходуем каждый материал вход€щий в список на нужный склад
                    //accService.releazeAccomulation(arrival.getDate(), arrival.getStore(), RegistryOperation.DECREMENT, normDBO.getResource(), normDBO.getCount(), arrival);
                }

            arrival.setStatus(DocumentStatus.POSTED);
        } catch (Exception e) {
           //accService.undoReleazeAccomulation(arrival);
            e.printStackTrace();
        }
    }

    /**
     * отменить проведение поступлени€
     */
    public void undoPostArrival(ArrivalDocumentDBO arrival) {
     //AccomulationRegistryService accService = AccomulationRegistryService.getInstance();
        //accService.undoReleazeAccomulation(arrival);
        arrival.setStatus(DocumentStatus.SAVED);
    }
}
