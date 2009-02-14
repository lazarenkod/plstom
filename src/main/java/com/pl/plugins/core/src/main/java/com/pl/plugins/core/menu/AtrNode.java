package com.pl.plugins.core.menu;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 29.09.2008
 * Time: 17:32:25
 */
public class AtrNode {
    private String name;
    private String stringvalue;

    public String getName() {
    return name;
}

    public void setName(String name) {
        this.name = name;
    }

    public String getStringvalue() {
        return stringvalue;
    }

    public void setStringvalue(String stringvalue) {
        this.stringvalue = stringvalue;
    }

    public String toString() {
        return "AtrNode{" +
                "name='" + name + '\'' +
                ", stringvalue='" + stringvalue + '\'' +
                '}'+"\n";
    }
}
