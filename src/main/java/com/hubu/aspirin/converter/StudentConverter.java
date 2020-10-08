package com.hubu.aspirin.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.dto.StudentManagementDTO;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.util.ConvertUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentConverter  {
    StudentConverter INSTANCE = Mappers.getMapper(StudentConverter.class);

    @Mapping(target = "facultyNumber", qualifiedByName = "facultyNameToNumber", source = "faculty")
    @Mapping(target = "specialtyNumber", qualifiedByName = "specialtyNameToNumber", source = "specialty")
    Student managementDtoToEntity(StudentManagementDTO managementDTO);

    @Mapping(target = "facultyNumber", qualifiedByName = "facultyNameToNumber", source = "faculty")
    @Mapping(target = "specialtyNumber", qualifiedByName = "specialtyNameToNumber", source = "specialty")
    void updateEntityFromManagementDto(StudentManagementDTO managementDTO, @MappingTarget Student student);

    @Named("facultyNameToNumber")
    default String facultyNameToNumber(String name) {
        return ConvertUtils.facultyGetNumberByName(name);
    }

    @Named("specialtyNameToNumber")
    default String specialtyNameToNumber(String name) {
        return ConvertUtils.specialtyGetNumberByName(name);
    }
}
