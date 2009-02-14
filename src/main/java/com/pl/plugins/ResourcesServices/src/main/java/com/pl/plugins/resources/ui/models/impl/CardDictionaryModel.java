package com.pl.plugins.resources.ui.models.impl;

import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;
import com.pl.plugins.resources.dal.dbo.CardDBO;
import com.pl.plugins.resources.dal.services.ICardService;
import com.pl.plugins.resources.dal.services.impl.CardService;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 06.10.2008
 * Time: 17:02:11
 */
public class CardDictionaryModel extends SimpleDictionaryModel<CardDBO>{
    public CardDictionaryModel() {
        //setEtityManager(CardService.getInstance());
    }
}
