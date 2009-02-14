package com.pl.plugins.commons.dal.dbo;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 23.09.2008
 * Time: 20:05:19
 */
public class StoreDBO extends BaseDBO implements IStore{
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreName() {
        return name;
    }

    public boolean isPhysicalStore() {
        return true;
    }
}
