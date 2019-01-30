package com.cristikang.summer.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.Date;

/**
 * @Author: sunkang
 * @CreateTime: 2019-01-21 09:48
 * @Description: ${Description}
 */
@Data
@Builder
public class Email {
    /**
     * 邮件id
     */
    private String id;
    /**
     * 发件人
     */
    private String sender;
    /**
     * 邮件接收人（多个邮箱则用逗号","隔开）
     */
    private String Recipient;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 发送时间
     */
    private Date sentDate;
    /**
     * 抄送（多个邮箱则用逗号","隔开）
     */
    private String cc;
    /**
     * 密送（多个邮箱则用逗号","隔开）
     */
    private String bcc;
    /**
     * 状态
     */
    private String status;
    /**
     * 错误信息
     */
    private String error;
    /**
     * 附件
     */
    @JsonIgnore
    private File[] files;
}
