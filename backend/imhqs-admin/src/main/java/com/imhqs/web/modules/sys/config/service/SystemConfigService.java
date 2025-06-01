package com.imhqs.web.modules.sys.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imhqs.web.modules.sys.config.dto.SysConfigDTO;
import com.imhqs.web.modules.sys.config.entity.SysConfig;


/**
* 通用配置业务类
*/
public interface SystemConfigService extends IService<SysConfig> {

    /**
     * 查找配置信息
     * @return
     */
    SysConfigDTO find();
}
