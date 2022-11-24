package ru.kav.zonzon.order.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories("ru.kav.zonzon.order.domain.repository")
public class JpaConfig {
}
