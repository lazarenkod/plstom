package com.pl.plugins.commons.ui.views.implnew.controller;

import com.pl.plugins.commons.ui.uinew.controller.AbstractController;
import com.pl.plugins.commons.ui.uinew.view.VisualForm;
import com.pl.plugins.commons.ui.uinew.core.error.ErrorManager;
import com.pl.plugins.commons.ui.views.implnew.view.DulTopComponent;
import com.pl.plugins.commons.dal.dbo.DulDBO;
import com.pl.plugins.commons.dal.dbo.HumanDBO;

/**
 * Created by IntelliJ IDEA.
 * User: јдминистратор
 * Date: 01.03.2009
 * Time: 3:03:03
 */
public class DulController extends AbstractController {
    private DulDBO decorator;

    public DulController() {
    }

    public DulController(DulDBO decorator) {
        this.decorator = decorator;
    }

    @Override
    public VisualForm getVisualForm() {
        if (visualForm == null) {
            visualForm = new DulTopComponent();
            visualForm.setController(this);
        }
        return visualForm;
    }

    @Override
    public Object getDataObject() {
        return decorator;
    }

    /**
     * ѕример загруки данных с сервера.
     * используетс€ как хранилище данных и нужен только дл€ примера.
     *
     * @param objectRequest данные, необходимые дл€ загрузки данных из источника
     * @return данные, загруженные из источника
     */
    public Object loadDataFromServer(Object objectRequest) {
//         Collection<ProductCategory> response = DataLoader.createCategoriesData();
        return createDecorator((HumanDBO)objectRequest);
    }


    @Override
    protected void handleException(Exception e) {
        ErrorManager.getDefault().notify(e);
    }


    /**
     * ћетод вызываетс€ непосредствено после {@link #loadDataFromServer(Object)}.
     * ¬ это методе происходит создание декораторов и проча€ инициализаци€ модели
     *
     * @param response данные, которые вернулись из {@link #loadDataFromServer(Object)}
     */
    protected void afterLoad(Object response) {
        this.decorator = (DulDBO) response;
    }

    public DulDBO createDecorator(HumanDBO source) {
        DulDBO decorator = new DulDBO(source);
        return decorator;
    }


}
