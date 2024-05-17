package com.ssn.middleware.domain.entity;

import lombok.Data;

/**
 * @Author linchengdong
 * @Date 2024-05-17 14:37
 * @PackageName:com.ssn.middleware.config.domain.domain
 * @ClassName: ThreadPoolMonitorEntity
 * @Description: TODO
 * @Version 1.0
 */
@Data
public class ThreadPoolMonitorEntity {

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 应用唯一标识
     */
    private String applicationKey;

    /**
     * 线程池名称
     */
    private String threadPoolName;

    /**
     * 核心线程数
     */
    private Integer corePoolSize;

    /**
     * 最大线程数
     */
    private Integer maximumPoolSize;

    /**
     * 线程池活跃线程数
     */
    private Integer activeCount;

    /**
     * 当前池中线程数
     */
    private Integer poolSize;

    /**
     * 队列类型
     */
    private String queueType;

    /**
     * 队列大小
     */
    private Integer queueSize;

    /**
     * 队列剩余任务数
     */
    private Integer remainingCapacity;
}
