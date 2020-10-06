package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.ClassroomDTO;
import com.hubu.aspirin.model.entity.Classroom;

import java.util.List;

public interface ClassroomService extends IService<Classroom> {
    /**
     * 获取所有教室信息
     */
    List<ClassroomDTO> getList();
}
