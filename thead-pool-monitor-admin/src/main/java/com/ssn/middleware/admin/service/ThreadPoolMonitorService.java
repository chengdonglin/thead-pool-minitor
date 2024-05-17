package com.ssn.middleware.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssn.middleware.admin.po.ThreadPoolPo;
import com.ssn.middleware.admin.request.ThreadPoolMonitorRequest;

import java.util.List;

/**
* @author DM
* @description 针对表【t_thread_pool(线程池信息)】的数据库操作Service
* @createDate 2024-05-17 18:08:22
*/
public interface ThreadPoolMonitorService extends IService<ThreadPoolPo> {


    void gather(List<ThreadPoolMonitorRequest> request);

    void down(String applicationId);
}
