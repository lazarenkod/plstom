package com.pl.plugins.resources.ui.models.impl;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.resources.dal.dbo.NormDBO;
import com.pl.plugins.resources.dal.dbo.ResourceDBO;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 01.10.2008
 * Time: 21:08:32
 */
public class NormsTableModel extends AbstractTableAdapter<NormDBO> {
    public NormsTableModel(ListModel listModel) {
        super(listModel);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        final NormDBO normItem = getRow(rowIndex);

        switch (columnIndex) {
            case 0:
                return normItem.getId();
            case 1:
                return normItem.getResource();
            case 2:
                return normItem.getCount();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return BaseDBO.getIdType();
            case 1:
                return ResourceDBO.class;
            case 2:
                return Double.class;
            default:
                return super.getColumnClass(columnIndex);
        }
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
            default:
                return false;
        }
    }
}
