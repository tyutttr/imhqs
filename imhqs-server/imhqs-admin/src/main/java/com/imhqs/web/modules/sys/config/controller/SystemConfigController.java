package com.imhqs.web.modules.sys.config.controller;

import com.imhqs.web.core.api.ApiRest;
import com.imhqs.web.core.api.controller.BaseController;
import com.imhqs.web.core.api.dto.BaseIdRespDTO;
import com.imhqs.web.core.utils.BeanMapper;
import com.imhqs.web.modules.sys.config.dto.SysConfigDTO;
import com.imhqs.web.modules.sys.config.entity.SysConfig;
import com.imhqs.web.modules.sys.config.service.SystemConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* 通用配置控制器
*/
@Api(tags={"通用配置"})
@RestController
@RequestMapping("/exam/api/sys/config")
public class SystemConfigController extends BaseController {

    @Autowired
    private SystemConfigService baseService;

    /**
    * 添加或修改
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "添加或修改")
    @RequestMapping(value = "/save", method = { RequestMethod.POST})
    public ApiRest<BaseIdRespDTO> save(@RequestBody SysConfigDTO reqDTO) {
        //复制参数
        SysConfig entity = new SysConfig();
        BeanMapper.copy(reqDTO, entity);
        baseService.saveOrUpdate(entity);
        return super.success(new BaseIdRespDTO(entity.getId()));
    }

    /**
    * 查找详情
    * @return
    */
    @ApiOperation(value = "查找详情")
    @RequestMapping(value = "/detail", method = { RequestMethod.POST})
    public ApiRest<SysConfigDTO> find() {
        SysConfigDTO dto = baseService.find();
        return super.success(dto);
    }
}
