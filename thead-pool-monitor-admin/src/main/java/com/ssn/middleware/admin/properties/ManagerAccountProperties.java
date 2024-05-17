package com.ssn.middleware.admin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author linchengdong
 * @Date 2024-05-17 17:43
 * @PackageName:com.ssn.middleware.admin.properties
 * @ClassName: ManagerAccountProperties
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "manager.account",ignoreInvalidFields = true)
public class ManagerAccountProperties {

    private String username;

    private String password;
}
