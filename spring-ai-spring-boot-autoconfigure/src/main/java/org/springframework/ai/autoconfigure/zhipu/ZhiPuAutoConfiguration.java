package org.springframework.ai.autoconfigure.zhipu;

import org.springframework.ai.autoconfigure.openai.OpenAiChatProperties;
import org.springframework.ai.autoconfigure.openai.OpenAiConnectionProperties;
import org.springframework.ai.autoconfigure.retry.SpringAiRetryAutoConfiguration;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackContext;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.zhipu.ZhiPuChatClient;
import org.springframework.ai.zhipu.ZhiPuEmbeddingClient;
import org.springframework.ai.zhipu.api.ZhiPuApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/3/29 16:07
 */
@AutoConfiguration(after = { RestClientAutoConfiguration.class, SpringAiRetryAutoConfiguration.class })
@ConditionalOnClass(ZhiPuApi.class)
@EnableConfigurationProperties({ ZhiPuConnectionProperties.class, ZhiPuChatProperties.class
                       /* , ZhiPuEmbeddingProperties.class */
                    })
public class ZhiPuAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = ZhiPuChatProperties.CONFIG_PREFIX, name = "enabled", havingValue = "true",
            matchIfMissing = true)
    public ZhiPuChatClient zhiPuChatClient(ZhiPuConnectionProperties commonProperties,
                                           ZhiPuChatProperties chatProperties, RestClient.Builder restClientBuilder,
                                           List<FunctionCallback> toolFunctionCallbacks, FunctionCallbackContext functionCallbackContext,
                                           RetryTemplate retryTemplate, ResponseErrorHandler responseErrorHandler) {
        var zhiPuApi = zhiPuApi(chatProperties.getBaseUrl(), commonProperties.getBaseUrl(),
                chatProperties.getApiKey(), commonProperties.getApiKey(), restClientBuilder, responseErrorHandler);
        if (!CollectionUtils.isEmpty(toolFunctionCallbacks)) {
            chatProperties.getOptions().getFunctionCallbacks().addAll(toolFunctionCallbacks);
        }
        return new ZhiPuChatClient(zhiPuApi, chatProperties.getOptions(), functionCallbackContext, retryTemplate);
    }

//    @Bean
//    @ConditionalOnMissingBean
//    @ConditionalOnProperty(prefix = ZhiPuEmbeddingProperties.CONFIG_PREFIX, name = "enabled", havingValue = "true",
//            matchIfMissing = true)
//    public ZhiPuEmbeddingClient zhiPuEmbeddingClient(ZhiPuConnectionProperties commonProperties,
//             ZhiPuEmbeddingProperties embeddingProperties, RestClient.Builder restClientBuilder,
//             RetryTemplate retryTemplate, ResponseErrorHandler responseErrorHandler) {
//        var zhiPuApi = zhiPuApi(embeddingProperties.getBaseUrl(), commonProperties.getBaseUrl(),
//                embeddingProperties.getApiKey(), commonProperties.getApiKey(), restClientBuilder, responseErrorHandler);
//        return new ZhiPuEmbeddingClient(zhiPuApi, embeddingProperties.getMetadataMode(),
//                embeddingProperties.getOptions(), retryTemplate);
//    }

    private ZhiPuApi zhiPuApi(String baseUrl, String commonBaseUrl, String apiKey, String commonApiKey,
                              RestClient.Builder restClientBuilder, ResponseErrorHandler responseErrorHandler) {
        String resolvedBaseUrl = StringUtils.hasText(baseUrl) ? baseUrl : commonBaseUrl;
        Assert.hasText(resolvedBaseUrl, "ZhiPu base URL must be set");

        String resolvedApiKey = StringUtils.hasText(apiKey) ? apiKey : commonApiKey;
        Assert.hasText(resolvedApiKey, "ZhiPu API key must be set");

        return new ZhiPuApi(resolvedBaseUrl, resolvedApiKey, restClientBuilder, responseErrorHandler);
    }
}
