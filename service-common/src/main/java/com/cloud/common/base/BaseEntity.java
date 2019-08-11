package com.cloud.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Description: 基础实体信息
 * Date:      2019/8/10 22:11
 * author     zhuwj
 * version    V1.0
 */
public class BaseEntity<T> extends Model<BaseEntity<T>> {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date created;
    /**
     * 创建者id
     */
    private Integer  createdId;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modified;
    /**
     * 更新者id
     */
    private Integer modifiedId;
    /**
     * 删除标志 0 正常 1 删除
     */
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Integer createdId) {
        this.createdId = createdId;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getModifiedId() {
        return modifiedId;
    }

    public void setModifiedId(Integer modifiedId) {
        this.modifiedId = modifiedId;
    }
}
