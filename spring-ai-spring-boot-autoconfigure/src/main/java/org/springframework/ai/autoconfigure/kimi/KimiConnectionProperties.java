package org.springframework.ai.autoconfigure.kimi;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/4/1 10:32
 */
@ConfigurationProperties(KimiConnectionProperties.CONFIG_PREFIX)
public class KimiConnectionProperties extends KimiParentProperties{
    public static final String CONFIG_PREFIX = "spring.ai.kimi";

    public static final String DEFAULT_BASE_URL = "https://api.moonshot.cn";

    public KimiConnectionProperties() {super.setBaseUrl(DEFAULT_BASE_URL);}
}
