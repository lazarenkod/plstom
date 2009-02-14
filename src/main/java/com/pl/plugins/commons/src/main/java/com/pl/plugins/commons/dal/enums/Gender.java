package com.pl.plugins.commons.dal.enums;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 17:07:06
 */
public class Gender {

    /**
     * Значение.
     */
    private boolean value;

    /**
     * Название
     */
    private String name;

    public static final Gender WOMAN = new Gender(false,ResourceBundle.
                        getBundle("com.pl.plugins.commons.ui.views.CommonViews").getString("Gender.Woman"));
    public static final Gender MAN = new Gender(true,ResourceBundle.
                        getBundle("com.pl.plugins.commons.ui.views.CommonViews").getString("Gender.Man"));

    private Gender(boolean value, String name){
        this.value = value;
        this.name = name;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Gender> getAll(){
        ArrayList<Gender> instancesList = new ArrayList<Gender>();

        instancesList.add(MAN);
        instancesList.add(WOMAN);

        return instancesList;
    }

    public String toString() {
        return name;
    }
}
