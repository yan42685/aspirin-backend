package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.ApplicationSwtich;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.converter.GradeConverter;
import com.hubu.aspirin.converter.TeacherConverter;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.mapper.GradeMapper;
import com.hubu.aspirin.mapper.TeacherMapper;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.model.entity.Grade;
import com.hubu.aspirin.model.entity.Teacher;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.service.GradeService;
import com.hubu.aspirin.service.TeacherService;
import com.hubu.aspirin.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    GradeMapper gradeMapper;
    @Autowired
    GradeService gradeService;

    @Override
    public TeacherDTO getDtoByNumber(String number) {
        User user = UserUtils.getByNumberAndRole(number, RoleEnum.TEACHER);
        if (user == null) {
            throw new KnownException(ExceptionEnum.USER_NOT_EXISTS);
        }
        Teacher teacher = getById(user.getId());
        return TeacherConverter.INSTANCE.entityToDto(teacher);
    }

    @Override
    public TeacherDTO getInformation() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Long id = user.getId();
        String number = getById(id).getNumber();
        return getDtoByNumber(number);
    }

    @Override
    public TeacherDTO updateInformation(TeacherModifiableDTO dto) {
        Teacher currentTeacher = getCurrentTeacher();
        TeacherConverter.INSTANCE.updateEntityFromModifiableDto(dto, currentTeacher);
        updateById(currentTeacher);
        return getDtoByNumber(currentTeacher.getNumber());
    }

    @Override
    public IPage<MarkOutputDTO> getMarkStudentPage(Integer current, Integer size, Long courseDetailId) {
        if (!ApplicationSwtich.enableMark) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
        Page<MarkOutputDTO> page = new Page<>(current, size);
        return gradeMapper.pageMarkOutputDtoByCourseDetailId(page, courseDetailId);
    }

    @Override
    public boolean markCourse(MarkInputDTO input) {
        Grade grade = GradeConverter.INSTANCE.markInputDTO2Grade(input);
        grade.setSubmitted(false);
        return gradeService.save(grade);
    }

    @Override
    public boolean updateMarkCourse(MarkUpdateDTO input) {
        LambdaUpdateWrapper<Grade> updateWrapper = new LambdaUpdateWrapper<Grade>()
                .eq(Grade::getId, input.getGradeId())
                // 只用未提交才能更新
                .eq(Grade::getSubmitted, false)
                .set(Grade::getRegularScores, input.getRegularScores())
                .set(Grade::getExamScores, input.getExamScores());
        return gradeService.update(updateWrapper);
    }

    @Override
    public boolean submitMark(Long gradeId) {
        Grade grade = new Grade()
                .setId(gradeId)
                .setSubmitted(true);
        return gradeService.updateById(grade);
    }

    private Teacher getCurrentTeacher() {
        String currentUsername = UserUtils.getCurrentUsername();
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<Teacher>().eq("username", currentUsername);
        return getOne(queryWrapper);
    }
}
