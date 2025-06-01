package com.imhqs.web.modules.sys.depart.dto.response;

import com.imhqs.web.modules.sys.depart.dto.SysDepartDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* 部门树结构响应类
*/
@Data
@ApiModel(value="部门树结构响应类", description="部门树结构响应类")
public class SysDepartTreeDTO extends SysDepartDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "子列表", required=true)
    private List<SysDepartTreeDTO> children;

    
}
