package com.ssn.middleware.domain;

import com.ssn.middleware.domain.entity.ThreadPoolMonitorEntity;

import java.util.List;

/**
 * @Author linchengdong
 * @Date 2024-05-17 14:01
 * @PackageName:com.ssn.middleware.config.domain
 * @ClassName: MonitorThreadPoolService
 * @Description: TODO
 * @Version 1.0
 */
public interface MonitorThreadPoolService {


    List<ThreadPoolMonitorEntity>  queryThreadPool();

}
