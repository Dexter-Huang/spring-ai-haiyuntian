package org.springframework.ai.autoconfigure.zhipu;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/3/29 16:33
 */
@ConfigurationProperties(ZhiPuConnectionProperties.CONFIG_PREFIX)
public class ZhiPuConnectionProperties extends ZhiPuParentProperties{
    public static final String CONFIG_PREFIX = "spring.ai.zhipu";

    public static final String DEFAULT_BASE_URL = "https://open.bigmodel.cn/api/paas";

    public ZhiPuConnectionProperties() {super.setBaseUrl(DEFAULT_BASE_URL);}
}
