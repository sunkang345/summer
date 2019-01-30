package com.cristikang.summer.utils;

import com.alibaba.fastjson.JSONObject;
import com.cristikang.summer.constant.SysConstants;
import com.cristikang.summer.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @BelongsProject: summer
 * @BelongsPackage: com.cristikang.summer.utils.utils
 * @Author: sunkang
 * @CreateTime: 2019-01-11 13:13
 * @Description: ${Description}
 */
public class EmailUtils {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    /**
     * 发送简单邮件
     */


    /**
     * 发送复杂邮件
     */

    /**
     * 校验邮件
     */

    /**
     * 邮件是否发送成功
     */
    private static boolean isSendEmail(JSONObject result){
        if (result == null || result.isEmpty()) {
            throw new BusinessException("发送内容为空！");
        }
        String code = result.get("code").toString();
        if (code == SysConstants.ResponeseCode.SUCCESS) {
            return true;
        }
        throw new BusinessException("邮件发送失败，错误信息：" + result.get("info"));

    }

}
