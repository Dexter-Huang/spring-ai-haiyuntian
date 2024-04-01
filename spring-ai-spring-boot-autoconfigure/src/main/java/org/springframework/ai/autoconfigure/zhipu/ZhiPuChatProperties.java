package org.springframework.ai.autoconfigure.zhipu;

import org.springframework.ai.zhipu.ZhiPuChatOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/3/29 16:01
 */
@ConfigurationProperties(ZhiPuChatProperties.CONFIG_PREFIX)
public class ZhiPuChatProperties extends ZhiPuParentProperties{
    public static final String CONFIG_PREFIX = "spring.ai.zhipu.chat";

    public static final String DEFAULT_CHAT_MODEL = "glm-4";

    private static final Double DEFAULT_TEMPERATURE = 0.7;

    private boolean enabled = true;

    @NestedConfigurationProperty
    private ZhiPuChatOptions options = ZhiPuChatOptions.builder()
            .withModel(DEFAULT_CHAT_MODEL)
            .withTemperature(DEFAULT_TEMPERATURE.floatValue())
            .build();

    public ZhiPuChatOptions getOptions() {
        return options;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
