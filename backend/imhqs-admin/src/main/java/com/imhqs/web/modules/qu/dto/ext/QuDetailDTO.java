package com.imhqs.web.modules.qu.dto.ext;

import com.imhqs.web.modules.qu.dto.QuAnswerDTO;
import com.imhqs.web.modules.qu.dto.QuDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* 问题题目请求类
*/
@Data
@ApiModel(value="问题题目详情", description="问题题目详情")
public class QuDetailDTO extends QuDTO {

    private static final long serialVersionUID = 1L;
    
    
    @ApiModelProperty(value = "备选项列表", required=true)
    private List<QuAnswerDTO> answerList;

    @ApiModelProperty(value = "题库列表", required=true)
    private List<String> repoIds;

    
}
