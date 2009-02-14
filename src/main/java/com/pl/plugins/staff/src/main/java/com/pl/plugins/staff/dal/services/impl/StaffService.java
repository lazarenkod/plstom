package com.pl.plugins.staff.dal.services.impl;

import com.pl.plugins.commons.dal.services.impl.EntityManagementService;
import com.pl.plugins.commons.dal.services.impl.HumanService;
import com.pl.plugins.staff.dal.dbo.EmployerDBO;
import com.pl.plugins.staff.dal.services.IStaffService;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: mostovoy.vladislav
 * Date: 18.09.2008
 * Time: 17:37:47
 * To change this template use File | Settings | File Templates.
 */
public class StaffService extends EntityManagementService<EmployerDBO> implements IStaffService{

    public StaffService(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }
}
