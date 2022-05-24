package lv.bluma.ordersys;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfiguration {

    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("username");
        factory.setPassword("password");
        return factory;
    }

}
