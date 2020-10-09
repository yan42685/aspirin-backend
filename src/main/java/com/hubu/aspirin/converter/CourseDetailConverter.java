package com.hubu.aspirin.converter;


import com.hubu.aspirin.model.dto.CourseAssignDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseDetailConverter {
    CourseDetailConverter INSTANCE = Mappers.getMapper(CourseDetailConverter.class);

    CourseDetail assignDto2Entity(CourseAssignDTO courseAssignDTO);
}
