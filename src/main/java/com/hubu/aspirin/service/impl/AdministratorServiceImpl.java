package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.ApplicationVariable;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.common.AspirinConstant;
import com.hubu.aspirin.converter.*;
import com.hubu.aspirin.enums.ApplicationSwitchEnum;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.mapper.AdministratorMapper;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.model.entity.*;
import com.hubu.aspirin.service.*;
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
    @Autowired
    CourseDetailService courseDetailService;

    @Override
    public AdministratorDTO getAdministratorDtoByNumber(String number) {
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<Administrator>()
                .eq("number", number);
        Administrator administrator = getOne(queryWrapper);
        return AdministratorConverter.INSTANCE.entityToDto(administrator);
    }

    @Override
    public AdministratorDTO getAdministratorInformation() {
        Administrator administrator = getCurrentAdministrator();
        return AdministratorConverter.INSTANCE.entityToDto(administrator);
    }

    @Override
    public AdministratorDTO updateAdministratorInformation(AdministratorModifiableDTO newInformation) {
        Administrator administrator = getCurrentAdministrator();
        AdministratorConverter.INSTANCE.updateEntityFromDto(newInformation, administrator);
        updateById(administrator);
        return AdministratorConverter.INSTANCE.entityToDto(getCurrentAdministrator());
    }

    @Override
    public IPage<TeacherDTO> pageTeacher(Integer current, Integer size, String numberOrRealName) {
        Page<Teacher> page = new Page<>(current, size);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<Teacher>()
                .like("number", numberOrRealName)
                .or()
                .like("real_name", numberOrRealName);
        IPage<Teacher> teachers = teacherService.page(page, queryWrapper);
        return TeacherConverter.INSTANCE.entityToDtoPage(teachers);
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
        String defaultRawPassword = AspirinConstant.DEFAULT_RAW_PASSWORD.getValue();
        String defaultPassword = UserUtils.generatePassword(teacher.getUsername(), defaultRawPassword);
        teacher.setPassword(defaultPassword);
        teacherService.save(teacher);
        return teacherService.getDtoByNumber(number);
    }

    @Override
    public TeacherDTO updateTeacher(String originalNumber, TeacherManagementDTO teacherManagementDTO) {
        String newNumber = teacherManagementDTO.getNumber();
        User user = UserUtils.getByNumberAndRole(newNumber, RoleEnum.TEACHER);
        if (!originalNumber.equals(newNumber) && user != null) {
            // 新编号已存在
            throw new KnownException(ExceptionEnum.NUMBER_EXISTS);
        }

        user = UserUtils.getByNumberAndRole(originalNumber, RoleEnum.TEACHER);
        if (!newNumber.equals(originalNumber) && user == null) {
            // 用户不存在
            throw new KnownException(ExceptionEnum.USER_NOT_EXISTS);
        }
        // 待更新teacher的id
        Long teacherId = user.getId();
        Teacher teacher = teacherService.getById(teacherId);
        TeacherConverter.INSTANCE.updateEntityFromManagementDto(teacherManagementDTO, teacher);
        teacherService.updateById(teacher);
        return teacherService.getDtoByNumber(teacher.getNumber());
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
    public IPage<StudentDTO> pageStudent(Integer current, Integer size, StudentQueryDTO studentQueryDTO) {
        return studentService.pageByQueryDto(current, size, studentQueryDTO);
    }

    @Override
    public StudentDTO addStudent( StudentManagementDTO dto) {
        String number = dto.getNumber();
        if (UserUtils.getByNumberAndRole(number, RoleEnum.STUDENT) != null) {
            throw new KnownException(ExceptionEnum.USERNAME_EXISTS);
        }
        Student student = StudentConverter.INSTANCE.managementDtoToEntity(dto);
        // 默认用户名为学号
        student.setUsername(number);
        // 设置默认密码
        String defaultRawPassword = AspirinConstant.DEFAULT_RAW_PASSWORD.getValue();
        String defaultPassword = UserUtils.generatePassword(student.getUsername(), defaultRawPassword);
        student.setPassword(defaultPassword);
        studentService.save(student);
        return studentService.getDtoByNumber(number);
    }

    @Override
    public StudentDTO updateStudent(String originalNumber, StudentManagementDTO dto) {
        String newNumber = dto.getNumber();
        User user = UserUtils.getByNumberAndRole(newNumber, RoleEnum.STUDENT);
        if (!originalNumber.equals(newNumber) && user != null) {
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
        return studentService.getDtoByNumber(student.getNumber());
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

    @Override
    public ApplicationSwitchDTO flipApplicationVariable(ApplicationSwitchEnum switchEnum) {
        ApplicationSwitchDTO switchDTO = new ApplicationSwitchDTO();
        switchDTO.setSwitchEnum(switchEnum);
        Boolean status = null;
        switch (switchEnum){
            case ELECT_SWITCH:
                status = !ApplicationVariable.enableElect;
                ApplicationVariable.enableElect = status;
                break;
            case MARK_SWITCH:
                status = !ApplicationVariable.enableMark;
                ApplicationVariable.enableMark = status;
                break;
            default:
                break;
        }
        switchDTO.setStatus(status);
        return switchDTO;
    }

    @Override
    public Boolean getApplicationSwitchStatus(ApplicationSwitchEnum switchEnum) {
        Boolean status = null;
        switch (switchEnum){
            case ELECT_SWITCH:
                status = ApplicationVariable.enableElect;
                break;
            case MARK_SWITCH:
                status = ApplicationVariable.enableMark;
                break;
            default:
                break;
        }
        return status;
    }


    public Administrator getCurrentAdministrator() {
        String username = UserUtils.getCurrentUsername();
        return getOne(new QueryWrapper<Administrator>().eq("username", username));
    }

}
