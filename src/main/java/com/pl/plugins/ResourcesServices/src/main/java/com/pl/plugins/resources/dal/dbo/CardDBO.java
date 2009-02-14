package com.pl.plugins.resources.dal.dbo;

import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.customers.dal.dbo.ClientDBO;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 09.09.2008
 * Time: 17:17:10
 */

/**
 * Карта скидки
 */
public class CardDBO extends BaseDBO {
    public static final String PERCENT="percent";
    public static final String ISSUE_DATE="issueDate";
    public static final String CARD_NUMBER="cardNumber";
    public static final String CLIENT="clientDBO";
    
    private int persent;
    private Date issueDate;
    private String cardNumber;
    private ClientDBO clientDBO;


    public ClientDBO getClientDBO() {
        return clientDBO;
    }

    public void setClientDBO(ClientDBO clientDBO) {
        this.clientDBO = clientDBO;
    }

    public int getPersent() {
        return persent;
    }

    public void setPersent(int persent) {
        this.persent = persent;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
