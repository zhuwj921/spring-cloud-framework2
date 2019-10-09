package com.cloud.auth.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.auth.entity.SysUser;
import com.cloud.auth.service.ISysUserService;
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
 *  前端控制器
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("findPage")
    public ResponseResult findPage(Page page){
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize(), true);
        List<SysUser> list = sysUserService.list(Wrappers.query(new SysUser()));
        list.forEach(sysUser -> sysUser.setPassword(null));
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
        return ResponseResult.ok(pageInfo);
    }

    @PostMapping("save")
    public ResponseResult save(@RequestBody SysUser sysUser){
        LocalDateTime dateTime = LocalDateTime.now();
        sysUser.setUuid(getUuid());
        sysUser.setCreateTime(dateTime);
        sysUser.setUpdateTime(dateTime);
        sysUser.setCreateId(getUserId());
        sysUser.setUpdateId(getUserId());
        sysUser.setDeleted(NORMAL_FLAG_CODE);
        sysUser.setPassword(PasswordEncodeUtil.encode(sysUser.getPassword()));
        sysUserService.save(sysUser);
        return ResponseResult.ok("用户添加成功");
    }

    @PutMapping("update")
    public ResponseResult update(@RequestBody SysUser sysUser){
        LocalDateTime dateTime = LocalDateTime.now();
        sysUser.setUpdateTime(dateTime);
        sysUser.setUpdateId(getUserId());
        if(StringUtils.isNotBlank(sysUser.getPassword())){
            sysUser.setPassword(PasswordEncodeUtil.encode(sysUser.getPassword()));
        }
        sysUserService.updateById(sysUser);
        return ResponseResult.ok("用户修改成功");
    }

    @DeleteMapping("delete")
    public ResponseResult update(@RequestParam("id") Integer id){
        sysUserService.removeById(id);
        return ResponseResult.ok("用户删除成功");
    }
}

