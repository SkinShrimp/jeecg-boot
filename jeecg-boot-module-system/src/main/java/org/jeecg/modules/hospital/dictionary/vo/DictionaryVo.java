package org.jeecg.modules.hospital.dictionary.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DictionaryVo implements Serializable {
    private String name;
    private Integer bed;
    private Integer bedused;
}
