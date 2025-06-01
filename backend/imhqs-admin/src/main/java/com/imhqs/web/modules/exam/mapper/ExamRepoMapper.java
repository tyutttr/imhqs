package com.imhqs.web.modules.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imhqs.web.modules.exam.dto.ext.ExamRepoExtDTO;
import com.imhqs.web.modules.exam.entity.ExamRepo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 考试题库Mapper
*/
@Mapper
public interface ExamRepoMapper extends BaseMapper<ExamRepo> {

    /**
     * 查找考试题库列表
     * @param examId
     * @return
     */
    List<ExamRepoExtDTO> listByExam(@Param("examId") String examId);
}
