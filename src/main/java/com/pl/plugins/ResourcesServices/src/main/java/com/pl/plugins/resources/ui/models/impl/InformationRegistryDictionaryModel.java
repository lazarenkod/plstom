package com.pl.plugins.resources.ui.models.impl;

import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;
import com.pl.plugins.resources.dal.dbo.InformationRegistryDBO;
import com.pl.plugins.resources.dal.services.IInformationRegistryService;
import com.pl.plugins.resources.dal.services.impl.InformationRegistryService;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 08.10.2008
 * Time: 11:14:27
 */
public class InformationRegistryDictionaryModel extends SimpleDictionaryModel<InformationRegistryDBO>{
    public InformationRegistryDictionaryModel() {
        //setEtityManager(InformationRegistryService.getInstance());
    }
}
