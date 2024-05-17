package com.ssn.middleware.admin.controller;

import com.ssn.middleware.admin.request.ThreadPoolMonitorRequest;
import com.ssn.middleware.admin.service.ThreadPoolMonitorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author linchengdong
 * @Date 2024-05-17 17:48
 * @PackageName:com.ssn.middleware.admin.controller
 * @ClassName: MonitorController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("monitor")
public class MonitorController {

    private final ThreadPoolMonitorService threadPoolMonitorService;

    public MonitorController(ThreadPoolMonitorService threadPoolMonitorService) {
        this.threadPoolMonitorService = threadPoolMonitorService;
    }

    @PostMapping("data")
    public void gather(@RequestBody List<ThreadPoolMonitorRequest> request) {
        threadPoolMonitorService.gather(request);
    }

    @GetMapping("down/{applicationId}")
    public void down(@PathVariable String applicationId) {
        threadPoolMonitorService.down(applicationId);
    }
}
