package com.cloud.auth.controller;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.auth.entity.SysMenu;
import com.cloud.auth.entity.SysUser;
import com.cloud.auth.entity.SysUserRole;
import com.cloud.auth.service.ISysMenuService;
import com.cloud.auth.service.ISysUserRoleService;
import com.cloud.auth.service.ISysUserService;
import com.cloud.auth.vo.UserInfoVO;
import com.cloud.common.base.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: 前端用户信息
 * Date:      2019/8/31 19:27
 * author     zhuwj
 * version    V1.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysMenuService sysMenuService;

    @GetMapping(value = {"/info"})
    public UserInfoVO info(){
        SysUser sysUser = userService.getById(getUserId());
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setSysUser(sysUser);
        List<Integer> roleIds  = userRoleService.list(Wrappers.<SysUserRole>query()
                .lambda().eq(SysUserRole::getUserId, getUserId()))
                .stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());
        userInfoVO.setRoles(ArrayUtil.toArray(roleIds, Integer.class));

        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuService.listMenusByRoleId(roleId)
                    .stream()
                    .filter(sysMenu -> StringUtils.isNotEmpty(sysMenu.getPermission()))
                    .map(SysMenu::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfoVO.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return userInfoVO;
    }

}
