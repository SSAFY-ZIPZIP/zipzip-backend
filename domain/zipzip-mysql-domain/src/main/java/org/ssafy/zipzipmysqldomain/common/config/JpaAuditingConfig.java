package org.ssafy.zipzipmysqldomain.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "org.ssafy.zipzipmysqldomain")
@EnableJpaRepositories(basePackages = "org.ssafy.zipzipmysqldomain")
public class JpaAuditingConfig {
}
