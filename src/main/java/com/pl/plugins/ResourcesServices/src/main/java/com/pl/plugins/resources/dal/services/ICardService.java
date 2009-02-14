package com.pl.plugins.resources.dal.services;

import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.resources.dal.dbo.CardDBO;
import com.pl.plugins.customers.dal.dbo.ClientDBO;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 19.09.2008
 * Time: 16:44:44
 */
public interface ICardService extends IEntityManager<CardDBO> {
    Collection<CardDBO> getCardsByPercent(int percent);
    CardDBO getCardByClient(ClientDBO client);
}
