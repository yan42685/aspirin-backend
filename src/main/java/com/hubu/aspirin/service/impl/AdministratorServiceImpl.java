package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.constant.AccountConstant;
import com.hubu.aspirin.converter.AdministratorConverter;
import com.hubu.aspirin.converter.BulletinConverter;
import com.hubu.aspirin.converter.StudentConverter;
import com.hubu.aspirin.converter.TeacherConverter;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.mapper.AdministratorMapper;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.model.entity.*;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.service.BulletinService;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.service.TeacherService;
import com.hubu.aspirin.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @Autowired
    BulletinService bulletinService;

    @Override
    public AdministratorDTO getAdministratorInformation() {
        Administrator administrator = getCurrentAdministrator();
        return AdministratorConverter.INSTANCE.entityToDto(administrator);
    }

    @Override
    public boolean updateAdministratorInformation(ModifiableAdministratorDTO newInformation) {
        Administrator administrator = getCurrentAdministrator();
        AdministratorConverter.INSTANCE.updateEntityFromDto(newInformation, administrator);
        updateById(administrator);
        return true;
    }

    @Override
    public IPage<TeacherDTO> pageTeacherByNumberOrRealName(Integer current, Integer size, String queryString) {
        Page<Teacher> page = new Page<>(current, size);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<Teacher>()
                .like("number", queryString)
                .or()
                .like("real_name", queryString);
        IPage<Teacher> teachers = teacherService.page(page, queryWrapper);
        return TeacherConverter.INSTANCE.entityToDtoPage(teachers);
    }

    @Override
    public TeacherDTO getTeacher(String number) {
        return teacherService.getInformationByNumber(number);
    }


    @Override
    public TeacherDTO addTeacher(TeacherManagementDTO dto) {
        String number = dto.getNumber();
        if (UserUtils.getByNumberAndRole(number, RoleEnum.TEACHER) != null) {
            throw new KnownException(ExceptionEnum.USERNAME_EXISTS);
        }
        Teacher teacher = TeacherConverter.INSTANCE.managementDtoToEntity(dto);
        // 默认用户名为教师编号
        teacher.setUsername(number);
        // 设置默认密码
        String defaultRawPassword = AccountConstant.DEFAULT_RAW_PASSWORD.getValue();
        String defaultPassword = UserUtils.generatePassword(teacher.getUsername(), defaultRawPassword);
        teacher.setPassword(defaultPassword);
        teacherService.save(teacher);
        return getTeacher(number);
    }

    @Override
    public TeacherDTO updateTeacher(String originalNumber, TeacherManagementDTO teacherManagementDTO) {
        String newNumber = teacherManagementDTO.getNumber();
        User user = UserUtils.getByNumberAndRole(newNumber, RoleEnum.TEACHER);
        if (user != null) {
            // 新编号已存在
            throw new KnownException(ExceptionEnum.NUMBER_EXISTS);
        }

        user = UserUtils.getByNumberAndRole(originalNumber, RoleEnum.TEACHER);
        if (user == null) {
            // 用户不存在
            throw new KnownException(ExceptionEnum.USER_NOT_EXISTS);
        }
        // 待更新teacher的id
        Long teacherId = user.getId();
        Teacher teacher = teacherService.getById(teacherId);
        TeacherConverter.INSTANCE.updateEntityFromManagementDto(teacherManagementDTO, teacher);
        teacherService.updateById(teacher);
        return getTeacher(teacher.getNumber());
    }

    @Override
    public boolean deleteTeacher(String number) {
        User user = UserUtils.getByNumberAndRole(number, RoleEnum.TEACHER);
        if (user == null) {
            throw new KnownException(ExceptionEnum.USER_NOT_EXISTS);
        }
        teacherService.removeById(user.getId());
        return true;
    }

    @Override
    public IPage<StudentDTO> pageStudentByNumberOrRealName(Integer current, Integer size, String queryString) {
        IPage<StudentBO> studentBoPage = studentService.pageBoByNumberOrRealName(current, size, queryString);
        return StudentConverter.INSTANCE.boToDtoPage(studentBoPage);
    }

    @Override
    public StudentDTO getStudent(String number) {
        return studentService.getInformationByNumber(number);
    }

    @Override
    public StudentDTO addStudent(StudentManagementDTO dto) {
        String number = dto.getNumber();
        if (UserUtils.getByNumberAndRole(number, RoleEnum.STUDENT) != null) {
            throw new KnownException(ExceptionEnum.USERNAME_EXISTS);
        }
        Student student = StudentConverter.INSTANCE.managementDtoToEntity(dto);
        // 默认用户名为学号
        student.setUsername(number);
        // 设置默认密码
        String defaultRawPassword = AccountConstant.DEFAULT_RAW_PASSWORD.getValue();
        String defaultPassword = UserUtils.generatePassword(student.getUsername(), defaultRawPassword);
        student.setPassword(defaultPassword);
        studentService.save(student);
        return getStudent(number);
    }

    @Override
    public StudentDTO updateStudent(StudentManagementDTO dto, String originalNumber) {
        String newNumber = dto.getNumber();
        User user = UserUtils.getByNumberAndRole(newNumber, RoleEnum.STUDENT);
        if (user != null) {
            // 新编号已存在
            throw new KnownException(ExceptionEnum.NUMBER_EXISTS);
        }

        user = UserUtils.getByNumberAndRole(originalNumber, RoleEnum.STUDENT);
        if (user == null) {
            // 用户不存在
            throw new KnownException(ExceptionEnum.USER_NOT_EXISTS);
        }
        // 待更新学生的id
        Long studentId = user.getId();
        Student student = studentService.getById(studentId);
        StudentConverter.INSTANCE.updateEntityFromManagementDto(dto, student);
        studentService.updateById(student);
        return getStudent(student.getNumber());
    }

    @Override
    public boolean deleteStudent(String number) {
        User user = UserUtils.getByNumberAndRole(number, RoleEnum.TEACHER);
        if (user == null) {
            throw new KnownException(ExceptionEnum.USER_NOT_EXISTS);
        }
        studentService.removeById(user.getId());
        return true;
    }

    @Override
    public boolean sendBulletin(String title, String content) {
        String administratorNumber = getCurrentAdministrator().getNumber();
        Bulletin bulletin = new Bulletin();
        bulletin.setTitle(title)
                .setContent(content)
                .setAdministratorNumber(administratorNumber);
        bulletinService.save(bulletin);
        return true;
    }

    @Override
    public BulletinDTO updateBulletin(Long id, String title, String content) {
        Administrator currentAdministrator = getCurrentAdministrator();
        UpdateWrapper<Bulletin> updateWrapper = new UpdateWrapper<Bulletin>().eq("id", id)
                .set("title", title)
                .set("content", content)
                .set("administratorNumber", currentAdministrator.getNumber());
        bulletinService.update(updateWrapper);
        Bulletin newBulletin = bulletinService.getById(id);
        return BulletinConverter.INSTANCE.entityToDto(newBulletin);
    }

    @Override
    public boolean deleteBulletin(Long id) {
        bulletinService.removeById(id);
        return true;
    }

    public Administrator getCurrentAdministrator() {
        String username = UserUtils.getCurrentUsername();
        return getOne(new QueryWrapper<Administrator>().eq("username", username));
    }

}
