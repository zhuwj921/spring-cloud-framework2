package com.cloud.auth.service;

import com.cloud.auth.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-31
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenu> listPermissionsByRoleIds(List<Integer> roleIds);

    List<SysMenu> listMenusByRoleId(Integer roleId);

}
