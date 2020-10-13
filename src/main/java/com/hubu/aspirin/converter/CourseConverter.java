package com.hubu.aspirin.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.CourseModifiableDTO;
import com.hubu.aspirin.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseConverter {
    CourseConverter INSTANCE = Mappers.getMapper(CourseConverter.class);

    CourseDTO entityToDto(Course course);

    // NOTE: 这里返回值不能是IPage, 否则会报编译错误
    Page<CourseDTO> entityToDtoPage(IPage<Course> course);

    Course modifiableDtoToEntity(CourseModifiableDTO courseModifiableDTOPage);

    Page<Course> modifiableDtoToEntityPage(IPage<CourseModifiableDTO> modifiableCourseDTOPage);

    Course updateEntityFromModifiableDto(CourseModifiableDTO courseModifiableDTO, @MappingTarget Course course);
}
