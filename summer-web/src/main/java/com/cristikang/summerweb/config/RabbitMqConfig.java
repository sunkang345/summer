package com.cristikang.summerweb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 * @Author: sunkang
 * @CreateTime: 2019-01-30 11:44
 * @Description: ${Description}
 */
@Configuration
@Slf4j
@ConditionalOnClass({ConnectionFactory.class, RabbitTemplate.class})
@ConditionalOnProperty()
public class RabbitMqConfig {

    @Configuration
    @ConditionalOnProperty(value = "mq.summer.enable", havingValue = "true")
    static class summerRabbitMqConfiguration {

        @Resource
        RabbitMqProperties config;

        /**
         * CachingConnectionFactory类扩展自SingleConnectionFactory，主要用于提供缓存JMS资源功能。
         * 具体包括MessageProducer、MessageConsumer和Session的缓存功能。
         * 默认情况下，CachingConnectionFactory只缓存一个session{低并发应用场景}
         * PooledConnectionFactory的默认值是500
         *
         * @return
         */
        @Bean
        @Primary
        public CachingConnectionFactory pmjRabbitConnectionFactory() {
            RabbitMqProperties.SUMMER summer = config.getSummer();
            CachingConnectionFactory ccf = new CachingConnectionFactory();
            ccf.setHost(summer.getHost());
            ccf.setPort(summer.getPort());
            ccf.setUsername(summer.getUsername());
            ccf.setPassword(summer.getPassword());
            ccf.setVirtualHost(summer.getVirtualhost());
            //需要显示调用，才能进行消息的回调
            ccf.setPublisherReturns(true);
            return ccf;
        }

        /**
         * 消息最终一致性解决方案之RabbitMQ实现
         * http://blog.51cto.com/4925054/2096781?utm_source=oschina-app
         *
         * @return
         */
        @Bean(name = "summerRabbitTemplate")
        public RabbitTemplate summerRabbitTemplate() {
            RabbitTemplate rabbitTemplate = new RabbitTemplate(pmjRabbitConnectionFactory());
            // 默认使用事务模式提交
            rabbitTemplate.setChannelTransacted(true);

            rabbitTemplate.setReturnCallback((message, replyCode, replyText, tmpExchange,
                                              tmpRoutingKey) -> log.error("summer mq queue receive message error,message= " + message + " replyCode"
                    + replyCode + " replyText " + replyText + " tmpExchange " + tmpExchange + " tmpRoutingKey "
                    + tmpRoutingKey));
            return rabbitTemplate;
        }

        @Bean
        public SimpleRabbitListenerContainerFactory summerRabbitListenerContainerFactory(
                SimpleRabbitListenerContainerFactoryConfigurer configurer) {
            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
            factory.setMaxConcurrentConsumers(10);
            configurer.configure(factory, pmjRabbitConnectionFactory());
            return factory;
        }

    }
}
