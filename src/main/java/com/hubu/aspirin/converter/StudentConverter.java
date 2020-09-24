package com.hubu.aspirin.converter;

import com.hubu.aspirin.converter.common.EnumConverter;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentConverter extends EnumConverter {
    StudentConverter INSTANCE = Mappers.getMapper(StudentConverter.class);

    @Mapping(target = "gender", qualifiedByName = "convertGender", source = "gender")
    StudentDTO boToDto(StudentBO studentBO);
}
