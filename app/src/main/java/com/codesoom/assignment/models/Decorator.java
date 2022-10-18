package com.codesoom.assignment.models;

public abstract class Decorator extends BaseTask{

    private BaseTask baseTask;

    @Override
    public Long getId() {
        return baseTask.getId();
    }

    @Override
    public String getTitle() {
        return baseTask.getTitle();
    }

    protected BaseTask getBaseTask() {
        return baseTask;
    }

    protected void setBaseTask(BaseTask baseTask) {
        this.baseTask = baseTask;
    }

    protected boolean hasBaskTask() {
        return baseTask != null;
    }
}
