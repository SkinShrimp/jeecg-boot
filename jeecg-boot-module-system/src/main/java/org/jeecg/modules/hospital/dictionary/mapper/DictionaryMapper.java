package org.jeecg.modules.hospital.dictionary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.hospital.dictionary.entity.Dictionary;
import org.jeecg.modules.hospital.dictionary.vo.DictionaryVo;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 字典表
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0DictionaryMapper
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    List<DictionaryVo> selectListByToken(HashMap<String,String> map);

    Integer selectListByTokenCount(String token);
}
