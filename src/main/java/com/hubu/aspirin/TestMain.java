package com.hubu.aspirin;

import cn.hutool.core.util.EnumUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.model.entity.Student;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(RoleEnum.STUDENT.name());
        System.out.println(RoleEnum.valueOf("ADMINISTRATOR"));
        System.out.println(RoleEnum.STUDENT.ordinal());
        System.out.println(EnumUtil.getNames(RoleEnum.class));
    }
}
