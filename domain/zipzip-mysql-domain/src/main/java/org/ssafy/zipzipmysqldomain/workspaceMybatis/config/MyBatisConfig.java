package org.ssafy.zipzipmysqldomain.workspaceMybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "org.ssafy.zipzipmysqldomain.workspaceMybatis.mapper") // Mapper 경로 지정
public class MyBatisConfig {
    // 추가 설정이 필요한 경우 여기에 작성

}
