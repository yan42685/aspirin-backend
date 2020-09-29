package com.hubu.aspirin.converter;

import com.hubu.aspirin.converter.common.EnumConverter;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.dto.StudentManagementDTO;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.util.ConvertUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentConverter extends EnumConverter {
    StudentConverter INSTANCE = Mappers.getMapper(StudentConverter.class);

    @Mapping(target = "gender", qualifiedByName = "genderIntegerToString", source = "gender")
    StudentDTO boToDto(StudentBO studentBO);

    @Mapping(target = "facultyNumber", qualifiedByName = "facultyNameToNumber", source = "faculty")
    @Mapping(target = "specialtyNumber", qualifiedByName = "specialtyNameToNumber", source = "specialty")
    @Mapping(target = "gender", qualifiedByName = "genderEnumToInteger", source = "gender")
    Student managementDtoToEntity(StudentManagementDTO studentManagementDTO);

    @Mapping(target = "facultyNumber", qualifiedByName = "facultyNameToNumber", source = "faculty")
    @Mapping(target = "specialtyNumber", qualifiedByName = "specialtyNameToNumber", source = "specialty")
    @Mapping(target = "gender", qualifiedByName = "genderEnumToInteger", source = "gender")
    void updateEntityFromManagementDto(StudentManagementDTO dto, @MappingTarget Student entity);

    @Named("facultyNameToNumber")
    default String facultyNameToNumber(String name) {
        return ConvertUtils.facultyGetNumberByName(name);
    }
    @Named("specialtyNameToNumber")
    default String specialtyNameToNumber(String name) {
        return ConvertUtils.specialtyGetNumberByName(name);
    }
}
