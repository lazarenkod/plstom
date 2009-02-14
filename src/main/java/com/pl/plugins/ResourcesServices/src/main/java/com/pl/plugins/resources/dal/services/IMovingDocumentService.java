package com.pl.plugins.resources.dal.services;

import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.resources.dal.dbo.MovingDocumentDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 26.09.2008
 * Time: 11:22:42
 */
public interface IMovingDocumentService extends IEntityManager<MovingDocumentDBO> {
    void addMoving(MovingDocumentDBO moving);
    void postMoving(MovingDocumentDBO moving);
    void undoPostMoving(MovingDocumentDBO moving);
}
