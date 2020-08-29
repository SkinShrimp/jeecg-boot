package org.jeecg.modules.hospital.dictionary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.hospital.dictionary.entity.Dictionary;
import org.jeecg.modules.hospital.dictionary.mapper.DictionaryMapper;
import org.jeecg.modules.hospital.dictionary.service.IDictionaryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 字典表
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    @Override
    public List<HashMap> queryDepartByToken(HashMap map) {
        return this.baseMapper.selectListByToken(map);
    }

    @Override
    public Integer queryDepartByTokenCount(String token) {
        return this.baseMapper.selectListByTokenCount(token);
    }
}
