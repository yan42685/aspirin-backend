package com.hubu.aspirin.converter;

import com.hubu.aspirin.converter.common.EnumConverter;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.dto.ModifiableAdministratorDTO;
import com.hubu.aspirin.model.entity.Administrator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

// 更新时忽略source的 null字段
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdministratorConverter extends EnumConverter {
    AdministratorConverter INSTANCE = Mappers.getMapper(AdministratorConverter.class);

    @Mapping(target = "gender", qualifiedByName = "genderIntegerToString", source = "gender")
    AdministratorDTO entityToDto(Administrator administrator);

    /**
     * 更新对象信息
     */
    @Mapping(target = "gender", qualifiedByName = "genderEnumToInteger", source = "gender")
    void updateEntityFromDto(ModifiableAdministratorDTO dto, @MappingTarget Administrator entity);
}
