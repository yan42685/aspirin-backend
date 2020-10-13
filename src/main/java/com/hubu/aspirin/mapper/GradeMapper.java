package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.dto.GradeDTO;
import com.hubu.aspirin.model.dto.MarkOutputDTO;
import com.hubu.aspirin.model.entity.Grade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface GradeMapper extends BaseMapper<Grade> {
    IPage<GradeDTO> pageDtoByStudentNumberAndSemester(Page<GradeDTO> page,
                                                      @Param("studentNumber") String studentNumber,
                                                      @Param("courseSemester") Integer courseSemester,
                                                      @Param("chosen") Integer chosen);

    IPage<MarkOutputDTO> pageMarkOutputDtoByCourseDetailId(Page<MarkOutputDTO> page,
                                                           @Param("courseDetailId") Long courseDetailId);
}
