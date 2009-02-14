package com.pl.plugins.commons.dal.dbo;

import com.pl.plugins.commons.dal.enums.Gender;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 13:43:01
 */

/**
 * Человек
 */
public class HumanDBO extends BaseDBO {

    /**
     * Названия полей для биндинга.
     */
    public static final String FMANE = "fname";
    public static final String LMANE = "lname";
    public static final String PATRONYMIC = "patronymic";
    public static final String BIRTHDAY = "birthday";
    public static final String ADDRESS = "address";
    public static final String DUL = "duls";
    public static final String GENDER = "gender";

    /**
     * Имя
     */
    private String fname;
    /**
     * Фамилия
     */
    private String lname;
    /**
     * Отчество
     */
    private String patronymic;
    /**
     * Дата рождения
     */
    private Date birthday;
    /**
     * адрес
     */
    private String address;
    /**
     * Ссылка на ДУЛы
     */
    private Collection<DulDBO> duls = new ArrayList<DulDBO>();
    /**
     * пол человека
     */
    private boolean gender;

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Collection<DulDBO> getDuls() {
        return duls;
    }

    public void setDuls(Collection<DulDBO> duls) {
        this.duls = duls;
    }

    public String getFIO() {
        if (lname!=null && fname!=null && patronymic!=null)
            return String.format("%s %s. %s.", lname, fname.substring(0, 1), patronymic.substring(0, 1));
        else
            return "EmptyHuman";
    }

    public String toString() {
        return getFIO();
    }
}
