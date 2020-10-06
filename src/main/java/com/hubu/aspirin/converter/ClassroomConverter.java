package com.hubu.aspirin.converter;


import com.hubu.aspirin.model.dto.ClassroomDTO;
import com.hubu.aspirin.model.entity.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClassroomConverter {
    ClassroomConverter INSTANCE = Mappers.getMapper(ClassroomConverter.class);

    ClassroomDTO entityToDto(Classroom classroom);

    List<ClassroomDTO> entityToDtoList(List<Classroom> classroomList);

}
