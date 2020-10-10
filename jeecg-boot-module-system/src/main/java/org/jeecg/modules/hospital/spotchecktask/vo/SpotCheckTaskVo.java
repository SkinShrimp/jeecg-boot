package org.jeecg.modules.hospital.spotchecktask.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.utils.TaskState;
import org.jeecg.modules.hospital.utils.TaskType;

import java.util.Date;
import java.util.List;

@Data
public class SpotCheckTaskVo {
    private String id;

    private TaskState taskState;

    private Long startTime;

    private Date createTime;

    private Date updateTime;

    private TaskType taskType;

    private String hmId;

    private Date startTimeReal;

    private Hospitalmonitor hospitalmonitor;


    private String extractstatus;

    private String status;

    private List hmIds;
    private List<Hospitalmonitor> hospitalmonitors;
    private String checkStatus;
    private Integer dateInterval;
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateStart;
}
