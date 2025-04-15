package metaverse.chap01.section02.config;

import metaverse.chap01.section02.service.KaKaoPayGateway;
import metaverse.chap01.section02.service.NaverPayGateway;
import metaverse.chap01.section02.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public KaKaoPayGateway kaKaoPayGateway() {
        return new KaKaoPayGateway();
    }

    @Bean
    public NaverPayGateway naverPayGateway() {
        return new NaverPayGateway();
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(naverPayGateway());
    }
}
