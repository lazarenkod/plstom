package com.pl.plugins.commons.ui.uinew.controller;

/**
	 * Операции над моделью списка
 */
public enum Operation {
    /**
     * Добавить элемент
     */
    ADD,
    /**
		 * Редактировать
		 */
	EDIT,
    /**
     * Удалить элемент
     */
    REMOVE,
    /**
     * Отменить последнее действие
     */
    UNDO,
    /**
     * Сохранить
     */
    SAVE
}
