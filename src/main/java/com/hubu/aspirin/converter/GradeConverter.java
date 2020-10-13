package com.hubu.aspirin.converter;


import com.hubu.aspirin.model.dto.MarkInputDTO;
import com.hubu.aspirin.model.dto.MarkUpdateDTO;
import com.hubu.aspirin.model.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GradeConverter {
    GradeConverter INSTANCE = Mappers.getMapper(GradeConverter.class);

    Grade markInputDTO2Grade(MarkInputDTO markInputDTO);
    List<Grade> markInputDTO2GradeList(List<MarkInputDTO> markInputDTOList);

    @Mapping(source = "gradeId", target = "id")
    Grade markUpdateDTO2Grade(MarkUpdateDTO markUpdateDTO);
    List<Grade> markUpdateDTO2GradeList(List<MarkUpdateDTO> markUpdateDTOList);

}
