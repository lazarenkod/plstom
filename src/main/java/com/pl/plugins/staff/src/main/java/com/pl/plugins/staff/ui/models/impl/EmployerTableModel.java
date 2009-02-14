package com.pl.plugins.staff.ui.models.impl;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.staff.dal.dbo.EmployerDBO;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Mostovoy.Vladislav
 * Date: 23.09.2008
 * Time: 18:21:36
  */
public class EmployerTableModel extends AbstractTableAdapter<EmployerDBO> {

    private ResourceBundle bundle = ResourceBundle.getBundle("com.pl.plugins.staff.ui.views.StaffView");

    private final byte COLUMN_COUNT = 6;

    private final String[] COLUMN_NAMES = new String[]{bundle.getString("EmployerTableModel.id"),
                                                       bundle.getString("EmployerTableModel.lname"),
                                                       bundle.getString("EmployerTableModel.fname"),
                                                       bundle.getString("EmployerTableModel.patronymic"),
                                                       bundle.getString("EmployerTableModel.post"),
                                                       bundle.getString("EmployerTableModel.address")};

    public EmployerTableModel(ListModel listModel) {
        super(listModel);

    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int i) {
        return COLUMN_NAMES[i];
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex) {

        if (columnIndex == 0) {
            return BaseDBO.getIdType();
        } else {
            return super.getColumnClass(columnIndex);
        }
    }

    @SuppressWarnings({"RefusedBequest"})
    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
    }

    public Object getValueAt(final int rowIndex, final int columnIndex) {

        final EmployerDBO employerItem = getRow(rowIndex);

        switch (columnIndex) {
            case 0:
                return employerItem.getId();
            case 1:
                return employerItem.getLname();
            case 2:
                return employerItem.getFname();
            case 3:
                return employerItem.getPatronymic();
            case 4:
                return employerItem.getPost();
            case 5:
                return employerItem.getAddress();
            /*case 6:
                return employerItem.getBirthday();
            case 7:
                return employerItem.getGender();*/
            default:
                return null;
        }
    }
}

