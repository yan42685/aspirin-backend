package com.hubu.aspirin.converter;

import com.hubu.aspirin.model.dto.SpecialtyDTO;
import com.hubu.aspirin.model.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SpecialtyConverter {
    SpecialtyConverter INSTANCE = Mappers.getMapper(SpecialtyConverter.class);

    SpecialtyDTO entityToDto(Specialty specialty);

    List<SpecialtyDTO> entityToDtoList(List<Specialty> specialtyList);
}
