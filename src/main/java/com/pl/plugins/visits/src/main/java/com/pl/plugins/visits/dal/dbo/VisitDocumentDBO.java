package com.pl.plugins.visits.dal.dbo;

import com.pl.plugins.commons.dal.dbo.BaseDBO;
import com.pl.plugins.commons.dal.enums.DocumentStatus;
import com.pl.plugins.commons.dal.dbo.IDocument;
import com.pl.plugins.customers.dal.dbo.ClientDBO;
import com.pl.plugins.staff.dal.dbo.EmployerDBO;
import com.pl.plugins.visits.dal.enums.ClientVisitType;
import com.pl.plugins.visits.dal.enums.PayType;
import com.pl.plugins.resources.dal.dbo.CardDBO;
import com.pl.plugins.resources.dal.dbo.GoodDBO;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 24.09.2008
 * Time: 13:23:23
 */

/**
 * Посещение клиента
 */
public class VisitDocumentDBO extends BaseDBO implements IDocument{
    /**
     * клиент
     */
    private ClientDBO client;
    /**
     * врач
     */
    private EmployerDBO employer;
    /**
     * Дата посещения
     */
    private Date date;
    /**
     * Карта скидки
     */
    private CardDBO card;
    /**
     * № посещения
     */
    private ClientVisitType clientVisit;
    /**
     * Тип оплаты
     */
    private PayType payType;
    /**
     * Статус документа
     */
    private DocumentStatus status;
    /**
     * Сумма без скидки
     */
    private Double summWODiscount;
    /**
     * Сумма со скидкой
     */
    private Double summWithDiscount;
    /**
     * Сумма оплаченная
     */
    private Double summPayed;
    /**
     * Список оказанных услуг
     */
    private List<GoodDBO> goods;

    public VisitDocumentDBO() {
    }

    public VisitDocumentDBO(ClientDBO client, EmployerDBO employer, Date date, CardDBO card, ClientVisitType clientVisit, PayType payType, DocumentStatus status) {
        this.client = client;
        this.employer = employer;
        this.date = date;
        this.card = card;
        this.clientVisit = clientVisit;
        this.payType = payType;
        this.status = status;
    }

    public VisitDocumentDBO(ClientDBO client, EmployerDBO employer, Date date, CardDBO card, ClientVisitType clientVisit, PayType payType, DocumentStatus status, Double summWODiscount, Double summWithDiscount, Double summPayed, List<GoodDBO> goods) {
        this.client = client;
        this.employer = employer;
        this.date = date;
        this.card = card;
        this.clientVisit = clientVisit;
        this.payType = payType;
        this.status = status;
        this.summWODiscount = summWODiscount;
        this.summWithDiscount = summWithDiscount;
        this.summPayed = summPayed;
        this.goods = goods;
    }

    public ClientDBO getClient() {
        return client;
    }

    public void setClient(ClientDBO client) {
        this.client = client;
    }

    public EmployerDBO getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerDBO employer) {
        this.employer = employer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CardDBO getCard() {
        return card;
    }

    public void setCard(CardDBO card) {
        this.card = card;
    }

    public ClientVisitType getClientVisit() {
        return clientVisit;
    }

    public void setClientVisit(ClientVisitType clientVisit) {
        this.clientVisit = clientVisit;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public Double getSummWODiscount() {
        return summWODiscount;
    }

    public void setSummWODiscount(Double summWODiscount) {
        this.summWODiscount = summWODiscount;
    }

    public Double getSummWithDiscount() {
        return summWithDiscount;
    }

    public void setSummWithDiscount(Double summWithDiscount) {
        this.summWithDiscount = summWithDiscount;
    }

    public Double getSummPayed() {
        return summPayed;
    }

    public void setSummPayed(Double summPayed) {
        this.summPayed = summPayed;
    }

    public List<GoodDBO> getServices() {
        return goods;
    }

    public void setServices(List<GoodDBO> goods) {
        this.goods = goods;
    }

    public String getDocumentName() {
        return String.format("Посещение № % от %");//TODO  сделать нормальным вывод имени документа 
    }
}
