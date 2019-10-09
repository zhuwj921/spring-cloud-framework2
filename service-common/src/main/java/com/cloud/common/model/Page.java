package com.cloud.common.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description: 分页参数
 * Date:      2019/9/1 0:08
 * author     zhuwj
 * version    V1.0
 */
@Data
public class Page {

   private int currentPage;

   private int pageSize;



    public int getPageSize() {
        if(pageSize == 0){
            return 10;
        }
        return pageSize;
    }
}
