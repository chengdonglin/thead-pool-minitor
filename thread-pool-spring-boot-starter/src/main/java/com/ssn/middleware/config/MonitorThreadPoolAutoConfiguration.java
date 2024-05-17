package com.ssn.middleware.config;

import com.ssn.middleware.domain.DefaultMonitorThreadPoolService;
import com.ssn.middleware.domain.MonitorThreadPoolService;
import com.ssn.middleware.job.MonitorThreadPoolSchedule;
import com.ssn.middleware.report.ReportService;
import com.ssn.middleware.report.http.HttpReportService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author linchengdong
 * @Date 2024-05-17 15:06
 * @PackageName:com.ssn.middleware.config
 * @ClassName: MonitorThreadPoolAutoConfiguration
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "monitor.thead.pool.config",name = "enable",havingValue = "true",matchIfMissing = true)
@EnableConfigurationProperties(MonitorTheadPoolProperties.class)
@Slf4j
@EnableScheduling
public class MonitorThreadPoolAutoConfiguration {


    @Bean("monitorThreadPoolService")
    public MonitorThreadPoolService monitorThreadPoolService(ApplicationContext applicationContext, Map<String, ThreadPoolExecutor> threadPoolExecutorMap) {
        String applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");
        if (!StringUtils.hasText(applicationName)) {
            applicationName = "default";
            log.warn("动态线程池，启动提示。SpringBoot 应用未配置 spring.application.name 无法获取到应用名称！");
        }
        return new DefaultMonitorThreadPoolService(applicationName,threadPoolExecutorMap);
    }

    @Bean("httpClient")
    public OkHttpClient httpClient() {
        ConnectionPool pool = new ConnectionPool(5,60, TimeUnit.SECONDS);
        return new OkHttpClient.Builder().connectionPool(pool).build();
    }

    @Bean
    public ReportService reportService(MonitorTheadPoolProperties monitorTheadPoolProperties, OkHttpClient httpClient) {
        return new HttpReportService(monitorTheadPoolProperties,httpClient);
    }

    @Bean
    public MonitorThreadPoolSchedule monitorThreadPoolSchedule( MonitorThreadPoolService monitorThreadPoolService,ReportService reportService) {
        return new MonitorThreadPoolSchedule(monitorThreadPoolService,reportService);
    }

}
