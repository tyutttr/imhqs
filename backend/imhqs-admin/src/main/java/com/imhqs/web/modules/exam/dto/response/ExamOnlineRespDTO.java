package com.imhqs.web.modules.exam.dto.response;

import com.imhqs.web.modules.exam.dto.ExamDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
* 考试分页响应类
*/
@Data
@ApiModel(value="在线考试分页响应类", description="在线考试分页响应类")
public class ExamOnlineRespDTO extends ExamDTO {

    private static final long serialVersionUID = 1L;


}
