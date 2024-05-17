package com.ssn.middleware.report;

import com.ssn.middleware.domain.entity.ThreadPoolMonitorEntity;
import org.springframework.beans.factory.DisposableBean;

import java.util.List;

/**
 * @Author linchengdong
 * @Date 2024-05-17 15:01
 * @PackageName:com.ssn.middleware.report
 * @ClassName: Report
 * @Description: 上报数据服务
 * @Version 1.0
 */
public interface ReportService extends DisposableBean {

    void report(List<ThreadPoolMonitorEntity>  datas);


    void down();
}
