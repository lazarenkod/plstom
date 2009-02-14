package com.pl.plugins.customers.dal.dbo;

import com.pl.plugins.commons.dal.dbo.HumanDBO;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 16:46:32
 */

/**
 *  лиент
 */ 
public class ClientDBO extends HumanDBO {
    /**
     * номер телефона
     */
    private String phoneNumber;
    /**
     * мобильник
     */
    private String mobilePhoneNumber;
    /**
     *дополнительный номер (дл€ нахождени€ карточек пациентов )
     */
    private int additionalId;
    
    /**
     * Getter for property 'phoneNumber'.
     *
     * @return Value for property 'phoneNumber'.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter for property 'phoneNumber'.
     *
     * @param phoneNumber Value to set for property 'phoneNumber'.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for property 'mobilePhoneNumber'.
     *
     * @return Value for property 'mobilePhoneNumber'.
     */
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    /**
     * Setter for property 'mobilePhoneNumber'.
     *
     * @param mobilePhoneNumber Value to set for property 'mobilePhoneNumber'.
     */
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    /**
     * Getter for property 'additionalId'.
     *
     * @return Value for property 'additionalId'.
     */
    public int getAdditionalId() {
        return additionalId;
    }

    /**
     * Setter for property 'additionalId'.
     *
     * @param additionalId Value to set for property 'additionalId'.
     */
    public void setAdditionalId(int additionalId) {
        this.additionalId = additionalId;
    }

    /**
     * Returns a string representation of the object. In general, the
     * <code>toString</code> method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p/>
     * The <code>toString</code> method for class <code>Object</code>
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `<code>@</code>', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
