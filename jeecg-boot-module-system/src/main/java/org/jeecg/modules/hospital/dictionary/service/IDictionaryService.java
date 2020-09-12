package org.jeecg.modules.hospital.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.hospital.dictionary.entity.Dictionary;
import org.jeecg.modules.hospital.dictionary.vo.DictionaryVo;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 字典表
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
public interface IDictionaryService extends IService<Dictionary> {

    List<DictionaryVo> queryDepartByToken(HashMap map);

    Integer queryDepartByTokenCount(String token);
}
