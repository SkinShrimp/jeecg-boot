package org.jeecg.modules.hospital.utils;

public enum TaskState {
    TODO("待执行"), DOING("执行中"), DONE("已执行"), CANCELLED("已取消"),AUTHENTICATE("认证完成");

    private String value;

    private TaskState(String value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }

    public String getValue() {
        return this.value;
    }
    public static TaskState getTaskStateById(String task) {

        for(TaskState taskState : TaskState.values()){
            if (taskState.getName().equals(task)) {
                return taskState;
            }
        }
        return null;
    }
}
