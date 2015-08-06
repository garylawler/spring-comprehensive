package jms.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.ConnectionFactory;
import java.util.UUID;

@Configuration
@PropertySource("classpath:jms.properties")
public class ActiveMQConfig {

    Logger logger = LoggerFactory.getLogger(ActiveMQConfig.class);

    @Autowired
    Environment env;

    @Bean
    public ConnectionFactory getConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(env.getProperty("broker.url"));
        connectionFactory.setClientID(UUID.randomUUID().toString());

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory);

        return cachingConnectionFactory;
    }
}
