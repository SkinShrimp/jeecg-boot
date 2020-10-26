package org.jeecg.modules.hospital.spotchecktask.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.utils.TaskState;
import org.jeecg.modules.hospital.utils.TaskType;
import org.jeecgframework.poi.excel.annotation.Excel;

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

    @Excel(name = "住院类型", width = 20)
    private String outstatus;
    @Excel(name = "抽查状态", width = 20)
    private String extractstatus;
    @Excel(name = "姓名", width = 20)
    private String name;//姓名
    @Excel(name = "性别", width = 20)
    private Integer sex;//性别
    @Excel(name = "身份证号", width = 20)
    private String percode;//身份证号 dictName
    @Excel(name = "医院名称", width = 20)
    private String hospitalName;
    @Excel(name = "就诊科室", width = 20)
    private String dictName;
    @Excel(name = "入院日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date indate;
    @Excel(name = "抽查次数", width = 20)
    private Integer checkNum;//抽查次数
    @Excel(name = "未上传次数", width = 20)
    private Integer notUploadedNum;//未上传次数
    @Excel(name = "次数不通过次数", width = 20)
    private Integer notPassNum;//次数不通过次数





    private String status;

    private List hmIds;
    private List<Hospitalmonitor> hospitalmonitors;
    private String checkStatus;
    private Integer dateInterval;
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateStart;


    public String getOutstatus() {
        if("1".equals(this.outstatus)){
            return "在院";
        }else{
            return "出院";

        }
    }

    public void setOutstatus(String outstatus) {
        this.outstatus = outstatus;
    }

    public String getExtractstatus() {
        if("1".equals(this.extractstatus)){
            return "正在抽查";
        }else {
            return "已经抽查";
        }
    }

    public void setExtractstatus(String extractstatus) {
        this.extractstatus = extractstatus;
    }

    public String getSex() {
        if(this.sex !=null){
            if(this.sex==1){
                return "男";
            }else if(this.sex==2){
                return "女";
            }
        }
        return null;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
