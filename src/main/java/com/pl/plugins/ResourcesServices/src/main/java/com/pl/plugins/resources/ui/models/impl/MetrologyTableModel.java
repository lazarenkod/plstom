package com.pl.plugins.resources.ui.models.impl;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.pl.plugins.resources.dal.dbo.MetrologyDBO;
import com.pl.plugins.commons.dal.dbo.BaseDBO;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Костя
 * Date: 08.10.2008
 * Time: 0:47:40
 */
public class MetrologyTableModel extends AbstractTableAdapter<MetrologyDBO>{
    public MetrologyTableModel(ListModel listModel) {
        super(listModel);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
         final MetrologyDBO metrologyItem = getRow(rowIndex);

        switch (columnIndex) {
            case 0:
                return metrologyItem.getId();
            case 1:
                return metrologyItem.getShortName();
            case 2:
                return metrologyItem.getLongName();
            default:
                return null;
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
         if (columnIndex == 0) {
            return BaseDBO.getIdType();
        } else {
            return super.getColumnClass(columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
