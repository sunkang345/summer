package com.cristikang.summer.exception;

import lombok.Data;

/**
 * @BelongsProject: summer
 * @BelongsPackage: com.cristikang.summer.exception
 * @Author: sunkang
 * @CreateTime: 2019-01-11 14:29
 * @Description: ${Description}
 */
@Data
public class BusinessException extends RuntimeException {

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    public BusinessException(){
        super();
    }

}
