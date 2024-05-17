package com.ssn.middleware.report.http;

import com.ssn.middleware.config.MonitorTheadPoolProperties;
import com.ssn.middleware.domain.entity.ThreadPoolMonitorEntity;
import com.ssn.middleware.domain.gson.GsonService;
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

    public static final MediaType JSON = MediaType.get("application/json");

    public HttpReportService(MonitorTheadPoolProperties monitorTheadPoolProperties, OkHttpClient httpClient) {
        this.monitorTheadPoolProperties = monitorTheadPoolProperties;
        this.httpClient = httpClient;
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
    public void health() {
        Request request = new Request.Builder().url(monitorTheadPoolProperties.getHealthUrl()).get().build();
        try {
            Response response = this.httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("上报服务健康失败");
            }
        } catch (Exception e) {
            log.error("健康检查响应异常",e);
        }

    }
}
