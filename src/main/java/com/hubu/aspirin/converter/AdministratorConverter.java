package com.hubu.aspirin.converter;

import com.hubu.aspirin.converter.common.EnumConverter;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.entity.Administrator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdministratorConverter extends EnumConverter {
    AdministratorConverter INSTANCE = Mappers.getMapper(AdministratorConverter.class);

    @Mapping(target = "gender", qualifiedByName = "convertGender", source = "gender")
    AdministratorDTO entityToDto(Administrator administrator);

}
