package com.cloud.auth.mapper;

import com.cloud.auth.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-31
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 通过角色ID查询权限
     *
     * @param roleIds Ids
     * @return
     */
    List<SysMenu> listPermissionsByRoleIds(String roleIds);

    /**
     * 通过角色编号查询菜单
     *
     * @param roleId 角色ID
     * @return
     */
    List<SysMenu> listMenusByRoleId(Integer roleId);
}
