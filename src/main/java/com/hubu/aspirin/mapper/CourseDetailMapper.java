package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.TeacherCourseDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDetailMapper extends BaseMapper<CourseDetail> {
    List<CourseDetailDTO> listByTeacherNumber(String teacherNumber);

    List<CourseDetailDTO> listByClassroomNumber(String classroomNumber);

    List<CourseDetailDTO> listBySpecialtyNumberAndSemesterAndCourseType(@Param("specialtyNumber") String specialtyNumber,
                                                                        @Param("semseter") Integer semester,
                                                                        @Param("courseType") Integer courseType);

    CourseDetailDTO getDtoById(Long id);

    List<TeacherCourseDTO> listTeacherCourseDtoByTeacherNumber(@Param("teacherNumber") String teacherNumber);
}
