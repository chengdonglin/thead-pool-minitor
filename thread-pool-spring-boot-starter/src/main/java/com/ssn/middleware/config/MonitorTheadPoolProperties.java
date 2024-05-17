package com.ssn.middleware.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author linchengdong
 * @Date 2024-05-17 15:05
 * @PackageName:com.ssn.middleware.config
 * @ClassName: MonitorTheadPoolProperties
 * @Description: TODO
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "monitor.thead.pool.config",ignoreInvalidFields = true)
@Data
public class MonitorTheadPoolProperties {

    private boolean enable;

    private String baseUrl;

    private String reportUrl;

    private String healthUrl;

}
