package com.pl.plugins.resources.ui.models.impl;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.pl.plugins.resources.dal.dbo.CardDBO;
import com.pl.plugins.commons.dal.dbo.BaseDBO;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 04.10.2008
 * Time: 9:57:52
 */
public class CardTableModel extends AbstractTableAdapter<CardDBO>{
    public Object getValueAt(int rowIndex, int columnIndex) {
         final CardDBO cardItem = getRow(rowIndex);

        switch (columnIndex) {
            case 0:
                return cardItem.getId();
            case 1:
                return cardItem.getCardNumber();
            case 2:
                return cardItem.getPersent();
            case 3:
                return cardItem.getIssueDate();
            case 4:
                return cardItem.getClientDBO();
            default:
                return null;
        }
    }

    public CardTableModel(ListModel listModel) {
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
