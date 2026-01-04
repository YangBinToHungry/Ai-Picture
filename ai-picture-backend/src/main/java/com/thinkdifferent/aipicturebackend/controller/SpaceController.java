package com.thinkdifferent.aipicturebackend.controller;

import com.thinkdifferent.aipicturebackend.annotation.AuthCheck;
import com.thinkdifferent.aipicturebackend.common.BaseResponse;
import com.thinkdifferent.aipicturebackend.common.ResultUtils;
import com.thinkdifferent.aipicturebackend.constant.UserConstant;
import com.thinkdifferent.aipicturebackend.exception.BusinessException;
import com.thinkdifferent.aipicturebackend.exception.ErrorCode;
import com.thinkdifferent.aipicturebackend.exception.ThrowUtils;
import com.thinkdifferent.aipicturebackend.model.dto.space.SpaceAddRequest;
import com.thinkdifferent.aipicturebackend.model.dto.space.SpaceLevel;
import com.thinkdifferent.aipicturebackend.model.dto.space.SpaceUpdateRequest;
import com.thinkdifferent.aipicturebackend.model.entity.Space;
import com.thinkdifferent.aipicturebackend.model.entity.User;
import com.thinkdifferent.aipicturebackend.model.enums.SpaceLevelEnum;
import com.thinkdifferent.aipicturebackend.service.SpaceService;
import com.thinkdifferent.aipicturebackend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/space")
public class SpaceController {
    @Resource
    private SpaceService spaceService;
    @Resource
    private UserService userService;

    /**
     * 空间信息添加：所有人可用
     * @param spaceAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addSpace(@RequestBody SpaceAddRequest spaceAddRequest, HttpServletRequest request) {
        if (spaceAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        Long newSpaceId = spaceService.addSpace(spaceAddRequest,loginUser);
        ThrowUtils.throwIf(newSpaceId==-1L, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
    /**
     * 空间信息更新：只有管理员可更新
     * @param spaceUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateSpace(@RequestBody SpaceUpdateRequest spaceUpdateRequest) {
        if (spaceUpdateRequest == null || spaceUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 将实体类和 DTO 进行转换
        Space space = new Space();
        BeanUtils.copyProperties(spaceUpdateRequest, space);
        // 自动填充数据
        spaceService.fillSpaceBySpaceLevel(space);
        // 数据校验
        spaceService.validSpace(space, false);
        // 判断是否存在
        long id = spaceUpdateRequest.getId();
        Space oldSpace = spaceService.getById(id);
        ThrowUtils.throwIf(oldSpace == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = spaceService.updateById(space);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 查询空间额度列表信息
     * @return
     */
    @GetMapping("/list/level")
    public BaseResponse<List<SpaceLevel>> listSpaceLevel() {
        List<SpaceLevel> spaceLevelList = Arrays.stream(SpaceLevelEnum.values()) // 获取所有枚举
                .map(spaceLevelEnum -> new SpaceLevel(
                        spaceLevelEnum.getValue(),
                        spaceLevelEnum.getText(),
                        spaceLevelEnum.getMaxCount(),
                        spaceLevelEnum.getMaxSize()))
                .collect(Collectors.toList());
        return ResultUtils.success(spaceLevelList);
    }





}
