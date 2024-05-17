package com.ssn.middleware.domain.key;

import java.util.UUID;

/**
 * @Author linchengdong
 * @Date 2024-05-17 17:56
 * @PackageName:com.ssn.middleware.domain.key
 * @ClassName: RandomApplicationId
 * @Description: TODO
 * @Version 1.0
 */
public class RandomApplicationId implements ApplicationId{

    private static final ApplicationId INSTANCE = new RandomApplicationId();

    public static ApplicationId getInstance() {
        return INSTANCE;
    }

    @Override
    public String generate() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
