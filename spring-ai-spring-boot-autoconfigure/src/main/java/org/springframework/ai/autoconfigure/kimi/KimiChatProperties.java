package org.springframework.ai.autoconfigure.kimi;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/4/1 10:31
 */
@ConfigurationProperties(KimiChatProperties.CONFIG_PREFIX)
public class KimiChatProperties extends KimiParentProperties{
    public static final String CONFIG_PREFIX = "spring.ai.kimi.chat";

    public static final String DEFAULT_CHAT_MODEL = "glm-4";

    private static final Double DEFAULT_TEMPERATURE = 0.7;

    private boolean enabled = true;
}
