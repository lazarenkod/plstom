package com.pl.plugins.resources.dal.services.impl;

import com.pl.plugins.resources.dal.services.IResourceService;
import com.pl.plugins.resources.dal.dbo.ResourceDBO;
import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Костя
 * Date: 08.10.2008
 * Time: 2:20:47
 */
public class ResourceService extends EntityManagementService<ResourceDBO> implements IResourceService {

    public ResourceService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }
}
