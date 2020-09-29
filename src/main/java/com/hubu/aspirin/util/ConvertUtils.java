package com.hubu.aspirin.util;

import com.hubu.aspirin.service.FacultyService;
import com.hubu.aspirin.service.SpecialtyService;

public class ConvertUtils {
    private static FacultyService facultyService = SpringContextUtils.getBean("FacultyServiceImpl", FacultyService.class);
    private static SpecialtyService specialtyService = SpringContextUtils.getBean("SpecialtyServiceImpl", SpecialtyService.class);

    public static String facultyGetNumberByName(String name) {
        return facultyService.getNumberByName(name);
    }

    public static String specialtyGetNumberByName(String name) {
        return specialtyService.getNumberByName(name);
    }

}
