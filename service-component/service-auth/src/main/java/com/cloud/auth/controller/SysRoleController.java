package com.cloud.auth.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.auth.entity.SysRole;
import com.cloud.auth.entity.SysUser;
import com.cloud.auth.service.ISysRoleService;
import com.cloud.common.model.Page;
import com.cloud.common.response.ResponseResult;
import com.cloud.common.util.PasswordEncodeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cloud.common.base.BaseController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("findPage")
    public ResponseResult findPage(Page page){
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize(), true);
        List<SysRole> list = sysRoleService.list(Wrappers.query(new SysRole()));
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
        return ResponseResult.ok(pageInfo);
    }

    @PostMapping("save")
    public ResponseResult save(@RequestBody SysRole sysRole){
        LocalDateTime dateTime = LocalDateTime.now();
        sysRole.setUuid(getUuid());
        sysRole.setCreateTime(dateTime);
        sysRole.setUpdateTime(dateTime);
        sysRole.setCreateId(getUserId());
        sysRole.setUpdateId(getUserId());
        sysRole.setDeleted(NORMAL_FLAG_CODE);
        sysRoleService.save(sysRole);
        return ResponseResult.ok("角色添加成功");
    }

    @PutMapping("update")
    public ResponseResult update(@RequestBody SysRole sysRole){
        LocalDateTime dateTime = LocalDateTime.now();
        sysRole.setUpdateTime(dateTime);
        sysRole.setUpdateId(getUserId());
        sysRoleService.updateById(sysRole);
        return ResponseResult.ok("角色修改成功");
    }

    @DeleteMapping("delete")
    public ResponseResult update(@RequestParam("id") Integer id){
        sysRoleService.removeById(id);
        return ResponseResult.ok("角色删除成功");
    }

}

