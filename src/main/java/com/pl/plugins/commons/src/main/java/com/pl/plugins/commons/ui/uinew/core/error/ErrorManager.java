package com.pl.plugins.commons.ui.uinew.core.error;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 13.02.2009
 * Time: 12:16:40
 */
public class ErrorManager {
    public final static String WARNING="WARNING"; 
    private static ErrorManager instance;
    private ErrorManager(){
        
    }
    public static ErrorManager getDefault(){
        if (instance==null)
            instance=new ErrorManager();
        return instance;
    }
    public void log(String type,String message){
               //TODO Сделать ErrorManager 
    }
}
