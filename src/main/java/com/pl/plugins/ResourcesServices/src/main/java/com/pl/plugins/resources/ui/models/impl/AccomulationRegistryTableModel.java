package com.pl.plugins.resources.ui.models.impl;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.resources.dal.dbo.AccomulationRegistryDBO;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Костя
 * Date: 08.10.2008
 * Time: 2:04:10
 */
public class AccomulationRegistryTableModel extends AbstractTableAdapter<AccomulationRegistryDBO> {
    public AccomulationRegistryTableModel(ListModel listModel) {
        super(listModel);
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        final AccomulationRegistryDBO accomulationRegistryItem = getRow(rowIndex);

        switch (columnIndex) {
            case 0:
                return accomulationRegistryItem.getId();
            case 1:
                return accomulationRegistryItem.getDate();
            case 2:
                return accomulationRegistryItem.getDocument();
            case 3:
                return accomulationRegistryItem.getStore();
            case 4:
                return accomulationRegistryItem.getOperation();
            case 5:
                return accomulationRegistryItem.getResource();
            case 6:
                return accomulationRegistryItem.getAmount();
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
