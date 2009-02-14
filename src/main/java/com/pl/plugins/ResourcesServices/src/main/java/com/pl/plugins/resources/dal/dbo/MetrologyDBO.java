package com.pl.plugins.resources.dal.dbo;

import com.pl.plugins.commons.dal.dbo.BaseDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 18:19:30
 */

/**
 * Еденица измерения
 */
public class MetrologyDBO extends BaseDBO {
    public static final String LONG_NAME="longName";
    public static final String SHORT_NAME="shortName";
    
    private String longName;
    private String shortName;

    /**
     * Getter for property 'longName'.
     *
     * @return Value for property 'longName'.
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Setter for property 'longName'.
     *
     * @param longName Value to set for property 'longName'.
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * Getter for property 'shortName'.
     *
     * @return Value for property 'shortName'.
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Setter for property 'shortName'.
     *
     * @param shortName Value to set for property 'shortName'.
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
