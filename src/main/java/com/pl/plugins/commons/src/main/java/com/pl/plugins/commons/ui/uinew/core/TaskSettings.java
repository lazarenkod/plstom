package com.pl.plugins.commons.ui.uinew.core;

import javax.swing.JComponent;

import com.pl.plugins.commons.ui.uinew.util.runner.TaskRunnerKindVisualBehavior;
import com.pl.plugins.commons.ui.uinew.util.runner.Cancellable;

/**
 * <pre>Description</pre>
 * <p/>
 * <br>
 * $$Rev$
 * <p/>
 * $$LastChangedDate::                    $
 * <p/>
 *
 * @author <a href="mailto:shaigorodskiy.vadim@otr.ru" >Vadim Shaigorodskiy</a>
 * @since 1.9
 */
public class TaskSettings {

    private TaskRunnerKindVisualBehavior behaviour;

    private JComponent componentToBlock;

    private String taskName;

    private Cancellable cancellable;


    public TaskSettings() {
    }


    public TaskSettings(TaskRunnerKindVisualBehavior behaviour, JComponent componentToBlock, String taskName) {
        this.behaviour = behaviour;
        this.componentToBlock = componentToBlock;
        this.taskName = taskName;
    }


    public TaskRunnerKindVisualBehavior getBehaviour() {
        return behaviour;
    }


    public void setBehaviour(TaskRunnerKindVisualBehavior behaviour) {
        this.behaviour = behaviour;
    }


    public JComponent getComponentToBlock() {
        return componentToBlock;
    }


    public void setComponentToBlock(JComponent componentToBlock) {
        this.componentToBlock = componentToBlock;
    }


    public String getTaskName() {
        return taskName;
    }


    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }


    public Cancellable getCancellable() {
        return cancellable;
    }


    public void setCancellable(Cancellable cancellable) {
        this.cancellable = cancellable;
    }
}
