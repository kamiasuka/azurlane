package ask.azurlane.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：设置自动扫描
 */
@Configuration
@MapperScan("ask.azurlane.dao.mapper")
public class MyBatisConfig {
}
