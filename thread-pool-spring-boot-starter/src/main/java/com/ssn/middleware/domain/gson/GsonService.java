package com.ssn.middleware.domain.gson;

import com.google.gson.Gson;

/**
 * @Author linchengdong
 * @Date 2024-05-17 15:59
 * @PackageName:com.ssn.middleware.domain.gson
 * @ClassName: GsonService
 * @Description: TODO
 * @Version 1.0
 */
public class GsonService {

    private static final GsonService INSTANCE = new GsonService();

    private  GsonService() {

    }

    public static GsonService getInstance() {
        return INSTANCE;
    }

    private static final Gson gson = new Gson();

    public String toJsonStr(Object data) {
        return gson.toJson(data);
    }
}
