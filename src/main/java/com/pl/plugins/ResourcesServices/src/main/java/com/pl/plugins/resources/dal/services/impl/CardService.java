package com.pl.plugins.resources.dal.services.impl;

import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import com.pl.plugins.commons.dal.services.impl.SessionHelper;
import com.pl.plugins.resources.dal.dbo.CardDBO;
import com.pl.plugins.resources.dal.services.ICardService;
import com.pl.plugins.customers.dal.dbo.ClientDBO;



import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate3.HibernateTemplate;


/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 19.09.2008
 * Time: 17:22:06
 */
public class CardService extends EntityManagementService<CardDBO> implements ICardService {

    public CardService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    @Transactional
    public Collection<CardDBO> getCardsByPercent(int percent) {
        return SessionHelper.getInstance().getSession().createQuery("SELECT c FROM CardDBO c WHERE c.percent=:percent").setInteger("percent",percent).list();
    }

    public CardDBO getCardByClient(ClientDBO client) {
        return (CardDBO) SessionHelper.getInstance().getSession().createQuery("SELECT c FROM CardDBO c WHERE c.client=:client").setParameter("client",client).list().get(0);
    }
  
}

