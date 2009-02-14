package com.pl.plugins.staff.ui.models.impl;

import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;
import com.pl.plugins.core.CorePlugin;
import com.pl.plugins.staff.dal.dbo.EmployerDBO;
import com.pl.plugins.staff.dal.services.IStaffService;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 23.09.2008
 * Time: 17:40:15
 * To change this template use File | Settings | File Templates.
 */
public class EmployerDictionaryModel extends SimpleDictionaryModel<EmployerDBO>{

    public EmployerDictionaryModel() {
        setEtityManager((IStaffService)CorePlugin.getAppContext().getBean("staffService"));
    }
}
