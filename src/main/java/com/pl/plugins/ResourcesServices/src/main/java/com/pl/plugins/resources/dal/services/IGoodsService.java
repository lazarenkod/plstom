package com.pl.plugins.resources.dal.services;

import com.pl.plugins.commons.dal.services.IEntityManager;
import com.pl.plugins.resources.dal.dbo.GoodDBO;

/**
 * Created by IntelliJ IDEA.
 * User: �������������
 * Date: 20.09.2008
 * Time: 14:58:37
 * To change this template use File | Settings | File Templates.
 */

/**
 * ������ ��� ������ � ��������
 */
public interface IGoodsService extends IEntityManager<GoodDBO> {
    GoodDBO getServiceByName(String name);
}
