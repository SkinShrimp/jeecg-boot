package org.jeecg.modules.hospital.utils;

public enum TaskType {
    END_SPOTCHECK_HOSPITAL("结束在线抽查"), //

    ;

    private String value;

    private TaskType(String value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }

    public String getValue() {
        return this.value;
    }

    public static long getStartTime(TaskType taskType) {
        long startTime = 0L;
        return startTime;
    }
}
