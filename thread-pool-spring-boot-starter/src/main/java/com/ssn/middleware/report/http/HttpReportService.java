package com.ssn.middleware.report.http;

import com.ssn.middleware.config.MonitorTheadPoolProperties;
import com.ssn.middleware.domain.entity.ThreadPoolMonitorEntity;
import com.ssn.middleware.domain.gson.GsonService;
import com.ssn.middleware.domain.key.ApplicationId;
import com.ssn.middleware.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.List;

/**
 * @Author linchengdong
 * @Date 2024-05-17 15:02
 * @PackageName:com.ssn.middleware.report
 * @ClassName: HttpReportService
 * @Description: TODO
 * @Version 1.0
 */
@Slf4j
public class HttpReportService implements ReportService {

    private final MonitorTheadPoolProperties monitorTheadPoolProperties;

    private final OkHttpClient httpClient;

    private final GsonService gsonService = GsonService.getInstance();

    private final String applicationId;

    public static final MediaType JSON = MediaType.get("application/json");

    public HttpReportService(MonitorTheadPoolProperties monitorTheadPoolProperties, OkHttpClient httpClient, String applicationId) {
        this.monitorTheadPoolProperties = monitorTheadPoolProperties;
        this.httpClient = httpClient;
        this.applicationId = applicationId;
    }

    @Override
    public void report(List<ThreadPoolMonitorEntity> datas) {
        if (datas.isEmpty()) {
            return;
        }
        String jsonStr = gsonService.toJsonStr(datas);
        String url = monitorTheadPoolProperties.getBaseUrl() + monitorTheadPoolProperties.getReportUrl();
        RequestBody body = RequestBody.create(jsonStr,JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = this.httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("上报线程池信息失败");
            }
        } catch (Exception e) {
            log.error("上报线程池信息异常",e);
        }
    }

    @Override
    public void down() {
        Request request = new Request.Builder().url(monitorTheadPoolProperties.getBaseUrl() + monitorTheadPoolProperties.getHealthUrl() + "/" + applicationId).get().build();
        try {
            Response response = this.httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("上报服务健康失败");
            }
        } catch (Exception e) {
            log.error("健康检查响应异常",e);
        }

    }

    @Override
    public void destroy() throws Exception {
        log.info("监听服务销毁");
        down();
    }
}
