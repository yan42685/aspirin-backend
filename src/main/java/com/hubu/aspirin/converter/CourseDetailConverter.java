package com.hubu.aspirin.converter;


import com.hubu.aspirin.model.dto.CourseAssignDTO;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.ElectiveDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseDetailConverter {
    CourseDetailConverter INSTANCE = Mappers.getMapper(CourseDetailConverter.class);

    CourseDetail assignDto2Entity(CourseAssignDTO courseAssignDTO);

    ElectiveDTO courseDetailDTO2ElectiveDto(CourseDetailDTO courseDetailDTO);

    List<ElectiveDTO> courseDetailDTOList2ElectiveDtos(List<CourseDetailDTO> courseDetailDTOs);

}
