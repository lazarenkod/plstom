package com.pl.plugins.commons.ui.models.impl;

import com.pl.plugins.commons.dal.dbo.HumanDBO;
import com.pl.plugins.commons.dal.services.IHumanService;
import com.pl.plugins.commons.dal.services.impl.DulService;
import com.pl.plugins.commons.dal.services.impl.HumanService;

/**
 * Created by IntelliJ IDEA.
 * User: Vlad
 * Date: 04.10.2008
 * Time: 19:46:35
 * To change this template use File | Settings | File Templates.
 */
public class HumanDictionaryModel extends SimpleDictionaryModel<HumanDBO>{

    public HumanDictionaryModel() {
        //setEtityManager(HumanService.getInstance());
    }
}
