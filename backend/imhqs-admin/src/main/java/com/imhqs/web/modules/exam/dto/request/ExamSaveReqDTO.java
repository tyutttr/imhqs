package com.imhqs.web.modules.exam.dto.request;

import com.imhqs.web.modules.exam.dto.ExamDTO;
import com.imhqs.web.modules.exam.dto.ext.ExamRepoExtDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* 考试保存请求类
*/
@Data
@ApiModel(value="考试保存请求类", description="考试保存请求类")
public class ExamSaveReqDTO extends ExamDTO {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "题库列表", required=true)
    private List<ExamRepoExtDTO> repoList;

    @ApiModelProperty(value = "考试部门列表", required=true)
    private List<String> departIds;

}
