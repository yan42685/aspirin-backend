package com.hubu.aspirin.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.dto.TeacherDTO;
import com.hubu.aspirin.model.dto.TeacherManagementDTO;
import com.hubu.aspirin.model.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherConverter  {
    TeacherConverter INSTANCE = Mappers.getMapper(TeacherConverter.class);

    TeacherDTO entityToDto(Teacher teacher);

    Page<TeacherDTO> entityToDtoPage(IPage<Teacher> teachers);

    Teacher managementDtoToEntity(TeacherManagementDTO teacherManagementDTO);

    void updateEntityFromManagementDto(TeacherManagementDTO teacherManagementDTO, @MappingTarget Teacher entity);
}
