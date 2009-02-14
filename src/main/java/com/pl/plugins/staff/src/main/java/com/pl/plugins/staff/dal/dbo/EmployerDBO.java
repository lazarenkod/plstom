package com.pl.plugins.staff.dal.dbo;

import com.pl.plugins.commons.dal.dbo.HumanDBO;
import com.pl.plugins.commons.dal.dbo.IStore;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 16:31:34
 */
public class EmployerDBO extends HumanDBO implements IStore{

    public static final String POST = "post";

    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getStoreName() {
        return getFname();//TODO сделать отдельный класс CustomerUtil,который будет строить ФИО пользователя

    }

    public boolean isPhysicalStore() {
        return false;
    }
}
