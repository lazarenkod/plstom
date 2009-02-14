package com.pl.plugins.commons.ui.views.impl;

import com.pl.plugins.commons.ui.controllers.ISimpleDictionaryController;
import com.pl.plugins.commons.ui.models.impl.SimpleDictionaryModel;
import com.pl.plugins.commons.ui.models.ISimpleDictionaryModel;
import com.pl.plugins.commons.ui.views.ISimpleDictionaryView;
import com.pl.plugins.commons.ui.views.ISimpleDictionaryToolBar;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 22.09.2008
 * Time: 16:16:05
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDictionaryView<M extends ISimpleDictionaryModel, C extends ISimpleDictionaryController>
                                    extends View<M, C> implements ISimpleDictionaryView<M, C> {

    public SimpleDictionaryView() {
        super(null, null);
    }

    public SimpleDictionaryView(M model, C controller) {
        super(model, controller);
    }

    public void attachDictionaryToolBar(ISimpleDictionaryToolBar simpleDictionaryToolBar) {

        simpleDictionaryToolBar.setNewAction(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                onNewAction();
            }
        });

        simpleDictionaryToolBar.setDeleteAction(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                onDeleteAction();
            }
        });

        simpleDictionaryToolBar.setEditAction(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                onEditAction();
            }
        });

        simpleDictionaryToolBar.setRefreshAction(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                onRefreshAction();
            }
        });

        simpleDictionaryToolBar.setFindAction(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                onFindAction();
            }
        });
    }

    public void onNewAction(){

    }

    public void onDeleteAction(){

    }

    public void onEditAction(){

    }

    public void onRefreshAction(){

    }

    public void onFindAction(){

    }
}
