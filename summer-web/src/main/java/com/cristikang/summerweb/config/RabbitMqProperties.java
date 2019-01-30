package com.cristikang.summerweb.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: sunkang
 * @CreateTime: 2019-01-30 11:53
 * @Description: ${Description}
 */
@Configuration
@Data
public class RabbitMqProperties {

    private SUMMER summer;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SUMMER extends BaseProps {

    }

    @Data
    static class BaseProps {
        /**
         * 是否启用，默认true
         */
        private boolean enabled = true;

        private String host = "158.213.139.100";

        private int port = 12567;

        private String username = "pms_dev_java";

        private String password = "pms_dev_java";

        private String virtualhost = "pms_dev_java";
    }
}
