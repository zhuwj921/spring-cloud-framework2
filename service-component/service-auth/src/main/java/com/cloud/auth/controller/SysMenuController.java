package com.cloud.auth.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.cloud.auth.entity.SysMenu;
import com.cloud.auth.service.ISysMenuService;
import com.cloud.auth.util.TreeUtil;
import com.cloud.auth.vo.MenuTree;
import com.cloud.common.util.WebContextUtil;
import com.netflix.discovery.CommonConstants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.cloud.common.base.BaseController;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/sysMenu")
@AllArgsConstructor
public class SysMenuController extends BaseController {

    private final ISysMenuService sysMenuService;

    /**
     * 返回当前用户的树形菜单集合
     *
     * @return 当前用户的树形菜单
     */
    @GetMapping("getUserMenu")
    public List<MenuTree> getUserMenu() {
        // 获取符合条件的菜单
        Set<SysMenu> all = new HashSet<>();
        WebContextUtil.getRoles()
                .forEach(roleId -> all.addAll(sysMenuService.listMenusByRoleId(roleId)));
        List<MenuTree> menuTreeList = all.stream()
                .filter(menuVo -> MENU.equals(menuVo.getType()))
                .map(MenuTree::new)
                .sorted(Comparator.comparingInt(MenuTree::getSort))
                .collect(Collectors.toList());
        return TreeUtil.buildByLoop(menuTreeList, DEFAULT_PID);
    }


    /**
     *  获取所有菜单
     * @return
     */
    @GetMapping("getMenuAll")
    public List<MenuTree> getMenuAll() {
        List<SysMenu> sysMenuList = sysMenuService.list();
        List<MenuTree> menuTreeList = sysMenuList.stream()
                .filter(menuVo -> MENU.equals(menuVo.getType()))
                .map(MenuTree::new)
                .sorted(Comparator.comparingInt(MenuTree::getSort))
                .collect(Collectors.toList());
        return TreeUtil.buildByLoop(menuTreeList, DEFAULT_PID);
    }

}

