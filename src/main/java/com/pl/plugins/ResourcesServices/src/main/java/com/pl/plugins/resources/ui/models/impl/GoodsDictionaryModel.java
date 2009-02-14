package com.pl.plugins.resources.ui.models.impl;

import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;
import com.pl.plugins.resources.dal.dbo.GoodDBO;
import com.pl.plugins.resources.dal.services.IGoodsService;
import com.pl.plugins.resources.dal.services.impl.GoodsService;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 08.10.2008
 * Time: 18:00:11
 */
public class GoodsDictionaryModel extends SimpleDictionaryModel<GoodDBO>{
    public GoodsDictionaryModel() {
       // setEtityManager(GoodsService.getInstance());
    }
}
