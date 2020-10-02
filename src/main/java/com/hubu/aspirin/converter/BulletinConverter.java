package com.hubu.aspirin.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.dto.BulletinDTO;
import com.hubu.aspirin.model.entity.Bulletin;
import com.hubu.aspirin.util.ConvertUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BulletinConverter {
    BulletinConverter INSTANCE = Mappers.getMapper(BulletinConverter.class);

    @Mapping(target = "administratorNickname", qualifiedByName = "administratorGetNicknameByNumber", source = "administratorNumber")
    BulletinDTO entityToDto(Bulletin bulletin);

    Page<BulletinDTO> entityToDtoPage(IPage<Bulletin> bulletinPage);

    @Named("administratorGetNicknameByNumber")
    default String administratorGetNicknameByNumber(String number) {
        return ConvertUtils.administratorGetByNumber(number).getNickname();
    }
}
