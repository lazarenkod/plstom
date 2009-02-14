package com.pl.plugins.resources.dal.services.impl;

import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import com.pl.plugins.commons.dal.enums.DocumentStatus;
import com.pl.plugins.resources.dal.dbo.MovingDocumentDBO;
import com.pl.plugins.resources.dal.dbo.NormDBO;
import com.pl.plugins.resources.dal.services.IMovingDocumentService;
import com.pl.plugins.resources.dal.enums.RegistryOperation;

import java.util.Iterator;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 26.09.2008
 * Time: 11:47:45
 */

/**
 * Документ перемещение материалов со склада на склад
 */
public class MovingDocumentService extends EntityManagementService<MovingDocumentDBO> implements IMovingDocumentService{

    public MovingDocumentService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public void addMoving(MovingDocumentDBO moving) {
      moving.setStatus(DocumentStatus.NEW);
      addOrUpdate(moving);
      moving.setStatus(DocumentStatus.SAVED);
      postMoving(moving);
    }

    public void postMoving(MovingDocumentDBO moving) {
        //AccomulationRegistryService accService = AccomulationRegistryService.getInstance();
        try {
                for (Iterator<NormDBO> iterRes = moving.getMaterials().iterator(); iterRes.hasNext();) {
                    NormDBO normDBO = iterRes.next();
                    //приходуем каждый материал входящий в список на нужный склад
                    //accService.releazeAccomulation(moving.getDate(), moving.getSourceStore(), RegistryOperation.DECREMENT, normDBO.getResource(), normDBO.getCount(), moving);
                    //accService.releazeAccomulation(moving.getDate(), moving.getDestinationStore(), RegistryOperation.INCREMENT, normDBO.getResource(), normDBO.getCount(), moving);
                }

            moving.setStatus(DocumentStatus.POSTED);
        } catch (Exception e) {
           //accService.undoReleazeAccomulation(moving);
            e.printStackTrace();
        }
    }

    public void undoPostMoving(MovingDocumentDBO moving) {
        //AccomulationRegistryService accService = AccomulationRegistryService.getInstance();
        //accService.undoReleazeAccomulation(moving);
        moving.setStatus(DocumentStatus.SAVED);
    }
}
