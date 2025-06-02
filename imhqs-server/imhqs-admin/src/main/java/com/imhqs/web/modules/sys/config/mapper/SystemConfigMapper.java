package com.imhqs.web.modules.sys.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imhqs.web.modules.sys.config.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;


/**
* 通用配置Mapper
*/
@Mapper
public interface SystemConfigMapper extends BaseMapper<SysConfig> {

}
