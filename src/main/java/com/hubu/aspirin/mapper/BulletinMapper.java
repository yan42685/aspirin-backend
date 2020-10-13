package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.entity.Bulletin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BulletinMapper extends BaseMapper<Bulletin> {
    IPage<Bulletin> page(Page<Bulletin> page, @Param("titleOrContent") String titleOrContent);

}
