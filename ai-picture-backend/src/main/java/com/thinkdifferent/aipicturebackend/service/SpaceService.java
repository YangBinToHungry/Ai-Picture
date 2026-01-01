package com.thinkdifferent.aipicturebackend.service;

import com.thinkdifferent.aipicturebackend.model.dto.space.SpaceAddRequest;
import com.thinkdifferent.aipicturebackend.model.entity.Space;
import com.baomidou.mybatisplus.extension.service.IService;
import com.thinkdifferent.aipicturebackend.model.entity.User;

/**
* @author yangbin
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-12-31 11:02:05
*/
public interface SpaceService extends IService<Space> {
    /**
     * 添加空间信息
     * @param spaceAddRequest
     * @param loginUser
     * @return
     */
    long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);
    /**
     * 校验空间信息
     * @param space
     * @param add
     */
    void validSpace(Space space, boolean add);
    /**
     * 根据空间等级设置空间容量、数量
     * @param space
     */
    void fillSpaceBySpaceLevel(Space space);
}
