package com.hubu.aspirin.converter;


import com.hubu.aspirin.model.dto.MarkInputDTO;
import com.hubu.aspirin.model.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GradeConverter {
    GradeConverter INSTANCE = Mappers.getMapper(GradeConverter.class);

    @Mapping(source = "gradeId", target = "id")
    Grade markInputDTO2Grade(MarkInputDTO markInputDTO);

}
