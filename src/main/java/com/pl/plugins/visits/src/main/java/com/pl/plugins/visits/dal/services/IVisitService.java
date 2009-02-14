package com.pl.plugins.visits.dal.services;

import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.visits.dal.dbo.VisitDocumentDBO;

/**
 * Created by IntelliJ IDEA.
 * User: lazarenko.dmitry
 * Date: 24.09.2008
 * Time: 15:40:15
 */
public interface IVisitService extends IEntityManager<VisitDocumentDBO> {
    void addVisit(VisitDocumentDBO visit);
    void postVisit(VisitDocumentDBO visit);
    void undoPostVisit(VisitDocumentDBO visit);
    

}
