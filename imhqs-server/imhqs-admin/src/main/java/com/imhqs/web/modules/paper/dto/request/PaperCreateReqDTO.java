package com.imhqs.web.modules.paper.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imhqs.web.core.api.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="试卷创建请求类", description="试卷创建请求类")
public class PaperCreateReqDTO extends BaseDTO {

    @JsonIgnore
    private String userId;

    @ApiModelProperty(value = "考试ID", required=true)
    private String examId;

}
