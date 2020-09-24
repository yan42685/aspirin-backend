package com.hubu.aspirin.converter.common;

import com.hubu.aspirin.enums.GenderEnum;
import com.hubu.aspirin.util.EnumUtils;
import org.mapstruct.Named;

public interface EnumConverter {
    /**
     * 转换性别
     */
    @Named("convertGender")
    default String convertGender(Integer gender) {
        return EnumUtils.getFieldByOrdinal(GenderEnum.class, "name", gender);
    }
}
