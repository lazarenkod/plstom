package com.pl.plugins.resources.dal.services;

import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.resources.dal.dbo.ArrivalDocumentDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 25.09.2008
 * Time: 14:16:27
 */
public interface IArrivalDocumentService extends IEntityManager<ArrivalDocumentDBO> {
    void addArrival(ArrivalDocumentDBO arrival);
    void postArrival(ArrivalDocumentDBO arrival);
    void undoPostArrival(ArrivalDocumentDBO arrival); 
}
