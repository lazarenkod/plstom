package com.pl.plugins.resources.ui.models.impl;

import com.pl.plugins.resources.dal.dbo.InformationRegistryDBO;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.jgoodies.binding.adapter.AbstractTableAdapter;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 08.10.2008
 * Time: 11:23:45
 */
public class InformationRegistryTableModel extends AbstractTableAdapter<InformationRegistryDBO> {
    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param    rowIndex    the row whose value is to be queried
     * @param    columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
          final InformationRegistryDBO informationRegistryItem = getRow(rowIndex);

        switch (columnIndex) {
            case 0:
                return informationRegistryItem.getId();
            case 1:
                return informationRegistryItem.getDate();
            case 2:
                return informationRegistryItem.getService();
            case 3:
                return informationRegistryItem.getCost();
            default:
                return null;
        }
    }

    public InformationRegistryTableModel(ListModel listModel) {
        super(listModel);
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
