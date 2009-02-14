package com.pl.plugins.staff.ui.models.impl;

import com.pl.plugins.commons.ui.models.impl.SimpleModel;
import com.pl.plugins.commons.ui.models.impl.HumanModel;
import com.pl.plugins.staff.dal.dbo.EmployerDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Vlad
 * Date: 05.10.2008
 * Time: 22:53:50
 * To change this template use File | Settings | File Templates.
 */
public class EmployerModel extends SimpleModel<EmployerDBO> {

    private HumanModel humanModel = null;

    public EmployerModel(){

        humanModel = new HumanModel();
    }

    public HumanModel getHumanModel() {
        return humanModel;
    }

    public void setHumanModel(HumanModel humanModel) {
        this.humanModel = humanModel;
    }

    @Override
    public void setProperty(EmployerDBO property) {
        super.setProperty(property);

        humanModel.setProperty(property);
    }
}
