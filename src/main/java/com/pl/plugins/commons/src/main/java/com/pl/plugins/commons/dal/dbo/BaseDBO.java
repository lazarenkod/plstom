package com.pl.plugins.commons.dal.dbo;

import org.jdesktop.beans.AbstractBean;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 13:39:29
 */
public class BaseDBO extends AbstractBean {

    protected int id = -1;

	public int getId() {
		return id;
	}

	public void setId(int value) {
		id = value;
	}

	public boolean isNew()
	{
		if(id == -1)
			return true;
		else
			return false;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDBO)) return false;

        BaseDBO baseDBO = (BaseDBO) o;

        if (id != baseDBO.id) return false;

        return true;
    }

    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    /**
    * Метод возвращает тип данных для идентификатора  в BaseDBO.
    */
    public static Class<?> getIdType(){

       return int.class;
    }
}
