package com.hubu.aspirin.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.dto.TeacherDTO;
import com.hubu.aspirin.model.dto.TeacherManagementDTO;
import com.hubu.aspirin.model.dto.TeacherModifiableDTO;
import com.hubu.aspirin.model.entity.Teacher;
import com.hubu.aspirin.util.ConvertUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherConverter {
    TeacherConverter INSTANCE = Mappers.getMapper(TeacherConverter.class);

    @Mapping(target = "faculty", qualifiedByName = "facultyNumberToName", source = "facultyNumber")
    TeacherDTO entityToDto(Teacher teacher);

    Page<TeacherDTO> entityToDtoPage(IPage<Teacher> teachers);

    Teacher managementDtoToEntity(TeacherManagementDTO teacherManagementDTO);

    void updateEntityFromManagementDto(TeacherManagementDTO teacherManagementDTO, @MappingTarget Teacher teacher);

    void updateEntityFromModifiableDto(TeacherModifiableDTO teacherModifiableDTO, @MappingTarget Teacher teacher);

    @Named("facultyNumberToName")
    default String facultyNameToNumber(String number) {
        return ConvertUtils.facultyGetNameByNumber(number);
    }
}
