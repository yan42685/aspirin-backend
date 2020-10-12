package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.CourseDropDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import com.hubu.aspirin.model.entity.StudentCourseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCourseDetailMapper extends BaseMapper<StudentCourseDetail> {
    IPage<CourseDropDTO> pageCourseDropRecourd(Page<CourseDropDTO> page,
                                               @Param("studentNumber") String studentNumber,
                                               @Param("semester") Integer semester,
                                               @Param("dropped") Integer droppedStatus);
    List<CourseDetailDTO> listCourseDetailDtoByStudentNumberAndSemester(@Param("studentNumber") String studentNumber,
                                                                        @Param("semester") Integer semester,
                                                                        @Param("chosen") Integer chosen);
}
