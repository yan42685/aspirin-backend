package com.hubu.aspirin.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hubu.aspirin.model.entity.Administrator;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.service.FacultyService;
import com.hubu.aspirin.service.SpecialtyService;

public class ConvertUtils {
    private static FacultyService facultyService = SpringContextUtils.getBean("facultyServiceImpl", FacultyService.class);
    private static SpecialtyService specialtyService = SpringContextUtils.getBean("specialtyServiceImpl", SpecialtyService.class);
    private static AdministratorService administratorService = SpringContextUtils.getBean("administratorServiceImpl", AdministratorService.class);

    public static String facultyGetNumberByName(String name) {
        return facultyService.getNumberByName(name);
    }

    public static String specialtyGetNumberByName(String name) {
        return specialtyService.getNumberByName(name);
    }

    public static Administrator administratorGetByNumber(String number) {
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<Administrator>()
                .eq("number", number);
        return administratorService.getOne(queryWrapper);
    }
}
