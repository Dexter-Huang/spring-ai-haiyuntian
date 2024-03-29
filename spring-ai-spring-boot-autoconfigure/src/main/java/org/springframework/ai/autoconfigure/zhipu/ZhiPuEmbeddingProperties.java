package org.springframework.ai.autoconfigure.zhipu;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/3/29 16:01
 */
@ConfigurationProperties(ZhiPuEmbeddingProperties.CONFIG_PREFIX)
public class ZhiPuEmbeddingProperties extends ZhiPuParentProperties{
    public static final String CONFIG_PREFIX = "spring.ai.zhipu.embedding";

    public static final String DEFAULT_EMBEDDING_MODEL = "embedding-2";
}
