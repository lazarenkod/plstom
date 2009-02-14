package com.pl.plugins.commons.ui.uinew.util.runner;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 14:46:21
 */
public enum TaskRunnerKindVisualBehavior {
    /**блокировать весь главный фрейм без возможности отмены.*/
    MAIN_FRAME,

    /**блокировать главный фрейм с возможностью отмены.*/
    MAIN_FRAMTE_WITH_CANCEL,

    /** блокировать только один TopComponent.*/
    TOP_COMPOENT,

    /**никого не блокировать. просто рисовать бегунок внизу.*/
    NO_BLOCKING;
}
