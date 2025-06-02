package com.imhqs.web.modules.user.exam.dto.request;

import com.imhqs.web.modules.user.exam.dto.UserExamDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 考试记录数据传输类
*/
@Data
@ApiModel(value="考试记录", description="考试记录")
public class UserExamReqDTO extends UserExamDTO {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考试名称", required=true)
    private String title;

    @ApiModelProperty(value = "人员名称", required=true)
    private String realName;


}
