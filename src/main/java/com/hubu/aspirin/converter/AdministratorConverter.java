package com.hubu.aspirin.converter;

import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.dto.ModifiableAdministratorDTO;
import com.hubu.aspirin.model.entity.Administrator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // 更新时忽略source的 null字段
public interface AdministratorConverter  {
    AdministratorConverter INSTANCE = Mappers.getMapper(AdministratorConverter.class);

    AdministratorDTO entityToDto(Administrator administrator);

    void updateEntityFromDto(ModifiableAdministratorDTO dto, @MappingTarget Administrator entity);
}
