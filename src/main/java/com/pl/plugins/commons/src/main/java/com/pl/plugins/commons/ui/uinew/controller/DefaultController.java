package com.pl.plugins.commons.ui.uinew.controller;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 17:00:34
 */
public class DefaultController
    extends AbstractController {

    private Object backupObject;

    private Object dataObject;


    public Object getBackupObject() {
        return backupObject;
    }


    public void setBackupObject(Object backupObject) {
        this.backupObject = backupObject;
    }


    public Object getDataObject() {
        return dataObject;
    }


    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }

    /**
     * Клонирует данные в объект который был изначально загружен для редактирования.
     */
    public void cloneDataToBackup() {
        try {
            this.backupObject = cloneObject(dataObject);
        } catch (IOException ex) {
//            todo Exceptions.printStackTrace(ex);
        } catch (ClassNotFoundException ex) {
//            Exceptions.printStackTrace(ex);
        }
    }
}

