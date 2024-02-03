package UMC.WithYou.common.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import UMC.WithYou.common.resolver.MemberArgumentResolver;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;
@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer {
    private final MemberArgumentResolver memberArgumentResolver;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberArgumentResolver);

    }
}