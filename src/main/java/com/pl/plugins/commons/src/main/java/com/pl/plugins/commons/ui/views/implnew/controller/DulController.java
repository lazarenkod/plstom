package com.pl.plugins.commons.ui.views.implnew.controller;

import com.pl.plugins.commons.ui.uinew.controller.AbstractController;
import com.pl.plugins.commons.ui.uinew.view.VisualForm;
import com.pl.plugins.commons.ui.uinew.core.error.ErrorManager;
import com.pl.plugins.commons.ui.views.implnew.view.DulTopComponent;
import com.pl.plugins.commons.dal.dbo.DulDBO;
import com.pl.plugins.commons.dal.dbo.HumanDBO;

/**
 * Created by IntelliJ IDEA.
 * User: �������������
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
     * ������ ������� ������ � �������.
     * ������������ ��� ��������� ������ � ����� ������ ��� �������.
     *
     * @param objectRequest ������, ����������� ��� �������� ������ �� ���������
     * @return ������, ����������� �� ���������
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
     * ����� ���������� �������������� ����� {@link #loadDataFromServer(Object)}.
     * � ��� ������ ���������� �������� ����������� � ������ ������������� ������
     *
     * @param response ������, ������� ��������� �� {@link #loadDataFromServer(Object)}
     */
    protected void afterLoad(Object response) {
        this.decorator = (DulDBO) response;
    }

    public DulDBO createDecorator(HumanDBO source) {
        DulDBO decorator = new DulDBO(source);
        return decorator;
    }


}
