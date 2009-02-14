package com.pl.plugins.resources.dal.services.impl;

import com.pl.plugins.resources.dal.services.IGoodsService;
import com.pl.plugins.resources.dal.dbo.GoodDBO;
import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import com.pl.plugins.commons.dal.services.impl.SessionHelper;
import org.hibernate.mapping.Collection;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 01.10.2008
 * Time: 17:18:49
 */
public class GoodsService extends EntityManagementService<GoodDBO> implements IGoodsService {

    public GoodsService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public GoodDBO getServiceByName(String name) {
        return (GoodDBO) SessionHelper.getInstance().getSession().createQuery("SELECT c FROM GoodDBO c WHERE c.name LIKE :name").setString("name",name).list().get(0);
    }

}
