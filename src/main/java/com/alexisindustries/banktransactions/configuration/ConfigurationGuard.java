package com.alexisindustries.banktransactions.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Component
public class ConfigurationGuard implements InitializingBean {
    @Value("${oer.appid:#{null}}")
    private String appId;

    @Override
    public void afterPropertiesSet() {
        if (appId == null || appId.isEmpty() || appId.equals("${oer.appid}")) {
            throw new NullPointerException("OER_APPID path variable must be configured");
        }
    }
}
