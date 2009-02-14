package com.pl.plugins.resources.ui.models.impl;

import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;
import com.pl.plugins.resources.dal.dbo.MetrologyDBO;
import com.pl.plugins.resources.dal.services.IMetrologyService;
import com.pl.plugins.resources.dal.services.impl.MetrologyService;

/**
 * Created by IntelliJ IDEA.
 * User: Костя
 * Date: 08.10.2008
 * Time: 0:01:39
 */
public class MetrologyDictionaryModel extends SimpleDictionaryModel<MetrologyDBO>{
    public MetrologyDictionaryModel() {
        //setEtityManager(MetrologyService.getInstance());
    }
}
