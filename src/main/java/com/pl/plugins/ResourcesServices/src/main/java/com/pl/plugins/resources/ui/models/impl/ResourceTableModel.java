package com.pl.plugins.resources.ui.models.impl;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.resources.dal.dbo.ResourceDBO;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Костя
 * Date: 08.10.2008
 * Time: 2:36:00
 */
public class ResourceTableModel extends AbstractTableAdapter<ResourceDBO> {
    public ResourceTableModel(ListModel listModel) {
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
        final ResourceDBO resourceItem = getRow(rowIndex);

        switch (columnIndex) {
            case 0:
                return resourceItem.getId();
            case 1:
                return resourceItem.getName();
            case 2:
                return resourceItem.getMetrology();
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
