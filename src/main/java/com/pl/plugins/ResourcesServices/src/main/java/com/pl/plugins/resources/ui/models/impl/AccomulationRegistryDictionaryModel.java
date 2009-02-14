package com.pl.plugins.resources.ui.models.impl;

import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;
import com.pl.plugins.resources.dal.dbo.AccomulationRegistryDBO;
import com.pl.plugins.resources.dal.services.IAccomulationRegistryService;
import com.pl.plugins.resources.dal.services.impl.AccomulationRegistryService;

/**
 * Created by IntelliJ IDEA.
 * User: Костя
 * Date: 08.10.2008
 * Time: 1:45:53
 */
public class AccomulationRegistryDictionaryModel extends SimpleDictionaryModel<AccomulationRegistryDBO>{
    public AccomulationRegistryDictionaryModel() {
        //setEtityManager(AccomulationRegistryService.getInstance());
    }
}
