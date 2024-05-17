package com.ssn.middleware.job;

import com.ssn.middleware.domain.MonitorThreadPoolService;
import com.ssn.middleware.domain.entity.ThreadPoolMonitorEntity;
import com.ssn.middleware.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @Author linchengdong
 * @Date 2024-05-17 16:13
 * @PackageName:com.ssn.middleware.job
 * @ClassName: ThreadPoolSchedule
 * @Description: TODO
 * @Version 1.0
 */
@Slf4j
public class MonitorThreadPoolSchedule {

    private final MonitorThreadPoolService monitorThreadPoolService;
    private final ReportService reportService;

    public MonitorThreadPoolSchedule(MonitorThreadPoolService monitorThreadPoolService, ReportService reportService) {
        this.monitorThreadPoolService = monitorThreadPoolService;
        this.reportService = reportService;
    }


    @Scheduled(cron = "0/60 * * * * ?")
    public void reportSchedule() {
        log.info("开始上报线程池信息数据");
         List<ThreadPoolMonitorEntity> list = monitorThreadPoolService.queryThreadPool();
         reportService.report(list);
    }

}
