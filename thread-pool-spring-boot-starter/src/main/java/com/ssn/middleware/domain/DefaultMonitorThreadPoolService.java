package com.ssn.middleware.domain;

import com.ssn.middleware.domain.entity.ThreadPoolMonitorEntity;
import com.ssn.middleware.domain.key.ApplicationId;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @Author linchengdong
 * @Date 2024-05-17 14:01
 * @PackageName:com.ssn.middleware.config.domain
 * @ClassName: DefaultMonitorThreadPoolService
 * @Description: TODO
 * @Version 1.0
 */
public class DefaultMonitorThreadPoolService implements MonitorThreadPoolService {

    private final String applicationName;

    private final Map<String, ThreadPoolExecutor> threadPoolExecutorMap;

    private final String applicationKey;
    public DefaultMonitorThreadPoolService(String applicationName, Map<String, ThreadPoolExecutor> threadPoolExecutorMap, String applicationId) {
        this.applicationName = applicationName;
        this.threadPoolExecutorMap = threadPoolExecutorMap;
        this.applicationKey = applicationId;
    }

    @Override
    public List<ThreadPoolMonitorEntity> queryThreadPool() {
        Set<String> names = threadPoolExecutorMap.keySet();
        return names.stream().map(name -> {
            ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(name);
            ThreadPoolMonitorEntity monitor = new ThreadPoolMonitorEntity();
            monitor.setApplicationKey(applicationKey);
            monitor.setApplicationName(applicationName);
            monitor.setThreadPoolName(name);
            monitor.setCorePoolSize(threadPoolExecutor.getCorePoolSize());
            monitor.setMaximumPoolSize(threadPoolExecutor.getMaximumPoolSize());
            monitor.setActiveCount(threadPoolExecutor.getActiveCount());
            monitor.setPoolSize(threadPoolExecutor.getPoolSize());
            monitor.setQueueType(threadPoolExecutor.getQueue().getClass().getSimpleName());
            monitor.setQueueSize(threadPoolExecutor.getQueue().size());
            monitor.setRemainingCapacity(threadPoolExecutor.getQueue().remainingCapacity());
            return monitor;
        }).collect(Collectors.toList());
    }
}
