package com.hubu.aspirin.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.ModifiableCourseDTO;
import com.hubu.aspirin.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseConverter {
    CourseConverter INSTANCE = Mappers.getMapper(CourseConverter.class);

    CourseDTO entityToDto(Course course);

    Page<CourseDTO> entityToDtoPage(IPage<Course> course);

    Course modifiableDtoToEntity(ModifiableCourseDTO modifiableCourseDTOPage);

    Page<Course> modifiableDtoToEntityPage(IPage<ModifiableCourseDTO> modifiableCourseDTOPage);

    Course updateEntityFromModifiableDto(ModifiableCourseDTO modifiableCourseDTO, @MappingTarget Course course);
}
