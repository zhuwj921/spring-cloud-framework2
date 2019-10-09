package com.cloud.auth.service.impl;

import com.cloud.auth.entity.SysMenu;
import com.cloud.auth.mapper.SysMenuMapper;
import com.cloud.auth.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-31
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<SysMenu> listPermissionsByRoleIds(List<Integer> roleIds) {
        String roleIdStr = StringUtils.join(roleIds,",");
        return baseMapper.listPermissionsByRoleIds(roleIdStr);
    }

    @Override
    public List<SysMenu> listMenusByRoleId(Integer roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }
}
