package com.pl.plugins.resources.ui.models.impl;

import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;
import com.pl.plugins.resources.dal.dbo.ResourceDBO;
import com.pl.plugins.resources.dal.services.IResourceService;
import com.pl.plugins.resources.dal.services.impl.ResourceService;

/**
 * Created by IntelliJ IDEA.
 * User: Костя
 * Date: 08.10.2008
 * Time: 2:17:14
 */
public class ResourceDictionaryModel extends SimpleDictionaryModel<ResourceDBO>{
    public ResourceDictionaryModel() {
        //setEtityManager(ResourceService.getInstance());
    }
}
