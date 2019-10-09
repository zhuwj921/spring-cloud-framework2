package com.cloud.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description: 基础实体信息
 * Date:      2019/8/10 22:11
 * author     zhuwj
 * version    V1.0
 */
@Data
public class BaseEntity<T> extends Model<BaseEntity<T>> {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 通用唯一识别码
     */
    private String uuid;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
    /**
     * 创建者id
     */
    private Integer  createId;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;
    /**
     * 更新者id
     */
    private Integer updateId;
    /**
     * 删除标志 0 正常 1 删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 版本号 默认0
     */
    private Integer version;


}
