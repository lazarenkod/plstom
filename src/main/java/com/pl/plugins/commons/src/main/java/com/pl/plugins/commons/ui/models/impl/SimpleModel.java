package com.pl.plugins.commons.ui.models.impl;

import com.pl.plugins.commons.ui.models.ISimpleModel;
import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.commons.helpers.impl.CloneHelper;
import org.apache.log4j.Logger;


/**
 * Created by IntelliJ IDEA.
 * User: Vlad
 * Date: 01.10.2008
 * Time: 0:43:16
 */
public class SimpleModel<T extends BaseDBO> extends CommonModel implements ISimpleModel<T> {

    private final Logger log = Logger.getLogger(SimpleDictionaryModel.class);

    /**
     * Поле хранит значене свойства модели.
     */
    private T property = null;

    /**
     * Поле хранит значени поумолчанию для свойства модели.
     */
    private T memento = null;

    public SimpleModel(){
        super();
    }

    public T getProperty(){
        return property;
    }

    public void setProperty(T property){

        this.property = property;

        if(property != null){

            memento = (T) CloneHelper.cloneObject(property);
        }
        
        notifySubscribers();
    }

    public void reset() {

        if(memento != null){
            property = (T) CloneHelper.cloneObject(memento);
        }

        notifySubscribers();
    }

    public T getMemento() {
        return memento;
    }
    
}
