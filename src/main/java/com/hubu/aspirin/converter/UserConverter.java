package com.hubu.aspirin.converter;


import com.hubu.aspirin.model.dto.UserDTO;
import com.hubu.aspirin.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserDTO entity2Dto(User user);
}
