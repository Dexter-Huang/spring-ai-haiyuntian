package org.springframework.ai.autoconfigure.zhipu;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
}
