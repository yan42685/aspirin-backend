package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.ApplicationVariable;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (!ApplicationVariable.enableMark) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
        Page<MarkOutputDTO> page = new Page<>(current, size);
        return gradeMapper.pageMarkOutputDtoByCourseDetailId(page, courseDetailId);
    }

    @Override
    public boolean markCourseList(List<MarkInputDTO> dtoList) {
        List<Grade> gradeList = GradeConverter.INSTANCE.markInputDTO2GradeList(dtoList);
        gradeList.forEach((grade -> grade.setSubmitted(false)));
        gradeService.saveBatch(gradeList);
        return true;
    }

    @Override
    public boolean updateMarkCourseList(List<MarkUpdateDTO> dtoList) {
        dtoList.forEach((dto -> {
            LambdaUpdateWrapper<Grade> updateWrapper = new LambdaUpdateWrapper<Grade>()
                    .eq(Grade::getId, dto.getGradeId())
                    // 只用未提交才能更新
                    .eq(Grade::getSubmitted, false)
                    .set(Grade::getRegularScores, dto.getRegularScores())
                    .set(Grade::getExamScores, dto.getExamScores());
            gradeService.update(updateWrapper);
        }));
        return true;
    }

    @Override
    public boolean submitMarkList(List<Long> gradeIdList) {
        List<Grade> gradeList = new ArrayList<>();
        for (Long id : gradeIdList) {
            Grade grade = new Grade();
            grade.setId(id)
                    .setSubmitted(true);
            gradeList.add(grade);
        }
        gradeService.updateBatchById(gradeList);
        return true;
    }

    private Teacher getCurrentTeacher() {
        String currentUsername = UserUtils.getCurrentUsername();
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<Teacher>().eq("username", currentUsername);
        return getOne(queryWrapper);
    }
}
