package com.ssn.middleware.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssn.middleware.admin.po.ThreadPoolPo;
import com.ssn.middleware.admin.request.ThreadPoolMonitorRequest;
import com.ssn.middleware.admin.service.ThreadPoolMonitorService;
import com.ssn.middleware.admin.mapper.ThreadPoolMonitorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author DM
* @description 针对表【t_thread_pool(线程池信息)】的数据库操作Service实现
* @createDate 2024-05-17 18:08:22
*/
@Service
public class ThreadPoolMonitorServiceImpl extends ServiceImpl<ThreadPoolMonitorMapper, ThreadPoolPo>
    implements ThreadPoolMonitorService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void gather(List<ThreadPoolMonitorRequest> request) {
        if (request.isEmpty()) {
            return;
        }
        ThreadPoolMonitorRequest monitorRequest = request.get(0);
        List<ThreadPoolPo> threadPoolPo = findTheadPoolServerByApplicationId(monitorRequest.getApplicationKey());
        if (threadPoolPo ==null || threadPoolPo.isEmpty()) {
            List<ThreadPoolPo> pos = request.stream().map(threadPoolMonitorRequest -> {
                ThreadPoolPo po = new ThreadPoolPo();
                po.setApplicationId(threadPoolMonitorRequest.getApplicationKey());
                po.setApplicationName(threadPoolMonitorRequest.getApplicationName());
                po.setThreadPoolName(threadPoolMonitorRequest.getThreadPoolName());
                po.setCorePoolSize(threadPoolMonitorRequest.getCorePoolSize());
                po.setMaximumPoolSize(threadPoolMonitorRequest.getMaximumPoolSize());
                po.setActiveCount(threadPoolMonitorRequest.getActiveCount());
                po.setQueueSize(threadPoolMonitorRequest.getQueueSize());
                po.setQueueType(threadPoolMonitorRequest.getQueueType());
                po.setRemainingCapacity(threadPoolMonitorRequest.getRemainingCapacity());
                po.setCreateTime(new Date());
                po.setActiveTime(new Date());
                po.setDown(0);
                return po;
            }).collect(Collectors.toList());
            this.saveBatch(pos);
        } else {
            Map<String, ThreadPoolMonitorRequest> map = request.stream().collect(Collectors.toMap(threadPoolMonitorRequest -> threadPoolMonitorRequest.getApplicationKey() + ":" + threadPoolMonitorRequest.getThreadPoolName(), threadPoolMonitorRequest -> threadPoolMonitorRequest));
            threadPoolPo.forEach(threadPool -> {
               String key =threadPool.getApplicationId() + ":" + threadPool.getThreadPoolName();
                ThreadPoolMonitorRequest poolMonitorRequest = map.get(key);
                threadPool.setApplicationId(poolMonitorRequest.getApplicationKey());
                threadPool.setApplicationName(poolMonitorRequest.getApplicationName());
                threadPool.setThreadPoolName(poolMonitorRequest.getThreadPoolName());
                threadPool.setCorePoolSize(poolMonitorRequest.getCorePoolSize());
                threadPool.setMaximumPoolSize(poolMonitorRequest.getMaximumPoolSize());
                threadPool.setActiveCount(poolMonitorRequest.getActiveCount());
                threadPool.setQueueSize(poolMonitorRequest.getQueueSize());
                threadPool.setQueueType(poolMonitorRequest.getQueueType());
                threadPool.setRemainingCapacity(poolMonitorRequest.getRemainingCapacity());
                threadPool.setActiveTime(new Date());
                threadPool.setDown(0);
            });
            this.updateBatchById(threadPoolPo);
        }

    }

    @Override
    public void down(String applicationId) {
        List<ThreadPoolPo> pos = findTheadPoolServerByApplicationId(applicationId);
        if (pos == null || pos.isEmpty()) {
            return;
        }
        pos.forEach(threadPoolPo -> threadPoolPo.setDown(1));
        this.updateBatchById(pos);
    }


    private List<ThreadPoolPo> findTheadPoolServerByApplicationId(String applicationId) {
        LambdaQueryWrapper<ThreadPoolPo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ThreadPoolPo::getApplicationId,applicationId);
        List<ThreadPoolPo> pos = this.list(wrapper);
        if (pos.isEmpty()) {
            return null;
        }
        return pos;
    }

}




