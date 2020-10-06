package com.hubu.aspirin.converter;


import com.hubu.aspirin.model.dto.FacultyDTO;
import com.hubu.aspirin.model.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FacultyConverter {
    FacultyConverter INSTANCE = Mappers.getMapper(FacultyConverter.class);

    FacultyDTO entityToDto(Faculty faculty);

    List<FacultyDTO> entityToDtoList(List<Faculty> facultyList);
}
