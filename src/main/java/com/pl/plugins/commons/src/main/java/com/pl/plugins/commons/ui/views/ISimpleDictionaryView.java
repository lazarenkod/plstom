package com.pl.plugins.commons.ui.views;

import com.pl.plugins.commons.ui.controllers.ISimpleDictionaryController;
import com.pl.plugins.commons.ui.models.ISubscriber;
import com.pl.plugins.commons.ui.models.ISimpleDictionaryModel;
import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 22.09.2008
 * Time: 12:09:21
 * To change this template use File | Settings | File Templates.
 */
public interface ISimpleDictionaryView<M extends ISimpleDictionaryModel, C extends ISimpleDictionaryController> extends IView<M, C>{

    void attachDictionaryToolBar(ISimpleDictionaryToolBar simpleDictionaryToolBar);

    void onNewAction();

    void onDeleteAction();

    void onEditAction();

    void onRefreshAction();

    void onFindAction();
}
