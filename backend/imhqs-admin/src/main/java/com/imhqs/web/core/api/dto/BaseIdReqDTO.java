package com.imhqs.web.core.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 主键通用请求类，用于根据ID查询
 */
@Data
@ApiModel(value="主键通用请求类", description="主键通用请求类")
public class BaseIdReqDTO extends BaseDTO {


    @ApiModelProperty(value = "主键ID", required=true)
    private String id;

    @JsonIgnore
    private String userId;

}
