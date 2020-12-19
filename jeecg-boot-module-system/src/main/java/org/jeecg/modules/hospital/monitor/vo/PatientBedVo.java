package org.jeecg.modules.hospital.monitor.vo;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.text.DecimalFormat;

@Data
public class PatientBedVo {
    @Excel(name = "医院名称", width = 15)
    private String hospitalName;
    @Excel(name = "医院编码", width = 15)
    private String hospitalCode;
    @Excel(name = "科室", width = 15)
    private String department;
    @Excel(name = "审批床位", width = 15)
    private String beds;//审批床位
    @Excel(name = "在院人数", width = 15)
    private String numPeopleInhos;//在院人数
    private Double occupationRate; //在院人数/审批人数
    @Excel(name = "占床率", width = 15)
    private String occupationRateStr; //在院人数/审批人数

    public String getOccupationRateStr() {
        if(this.occupationRate!=null) {
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(this.occupationRate)+"%";
        }
        return occupationRateStr;
    }

    public void setOccupationRateStr(String occupationRateStr) {
        this.occupationRateStr = occupationRateStr;
    }
}
