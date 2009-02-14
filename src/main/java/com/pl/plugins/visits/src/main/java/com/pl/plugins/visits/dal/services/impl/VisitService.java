package com.pl.plugins.visits.dal.services.impl;

import com.pl.plugins.commons.dal.enums.DocumentStatus;
import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import com.pl.plugins.resources.dal.dbo.NormDBO;
import com.pl.plugins.resources.dal.dbo.GoodDBO;
import com.pl.plugins.resources.dal.enums.RegistryOperation;
import com.pl.plugins.resources.dal.services.impl.AccomulationRegistryService;
import com.pl.plugins.visits.dal.dbo.VisitDocumentDBO;
import com.pl.plugins.visits.dal.services.IVisitService;

import java.util.Iterator;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: lazarenko.dmitry
 * Date: 24.09.2008
 * Time: 19:03:19
 */

/**
 * ������ ���������
 */
public class VisitService extends EntityManagementService<VisitDocumentDBO> implements IVisitService {

    public VisitService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    /**
     * �������� ���������
     */
    public void addVisit(VisitDocumentDBO visit) {
        visit.setStatus(DocumentStatus.NEW);
        addOrUpdate(visit);
        visit.setStatus(DocumentStatus.SAVED);
        postVisit(visit);
    }

    /**
     * �������� ���������
     */
    public void postVisit(VisitDocumentDBO visit) {
        //AccomulationRegistryService accService = AccomulationRegistryService.getInstance();
        //TODO ������ ������ ��� ������ ���� � ������� �� ������� ����������
        //TODO ���������� ��� �� ������� ����� ���������� � ����� ���
        //TODO ��� �� ���������� ����������

        try {
            for (Iterator<GoodDBO> iteratorServ = visit.getServices().iterator(); iteratorServ.hasNext();) {
                GoodDBO goodDBO = iteratorServ.next();
                for (Iterator<NormDBO> iterRes = goodDBO.getNorms().iterator(); iterRes.hasNext();) {
                    NormDBO normDBO = iterRes.next();
                    //��������� ������ �������� �������� � ������ � �������
                    //accService.releazeAccomulation(visit.getDate(), visit.getEmployer(), RegistryOperation.DECREMENT, normDBO.getResource(), normDBO.getCount(), visit);
                }
            }
            visit.setStatus(DocumentStatus.POSTED);
        } catch (Exception e) {
           //accService.undoReleazeAccomulation(visit);
            e.printStackTrace();
        }
    }


    /**
     * �������� ���������� ���������
     */
    public void undoPostVisit(VisitDocumentDBO visit) {
        //AccomulationRegistryService accService = AccomulationRegistryService.getInstance();
        //accService.undoReleazeAccomulation(visit);
        visit.setStatus(DocumentStatus.SAVED);
    }
}
