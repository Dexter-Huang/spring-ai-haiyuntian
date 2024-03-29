package org.springframework.ai.zhipu.api;

import org.springframework.ai.retry.RetryUtils;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Predicate;
// @formatter:off
/**
 * Single class implementation of the ZhiPu Chat Completion API: https://maas.aminer.cn/dev/api#glm-4 and
 * ZhiPu Embedding API: https://maas.aminer.cn/dev/api#text_embedding.
 * @description: TODO
 * @date 2024/3/29 13:58
 */
public class ZhiPuApi {
    public static final String DEFAULT_CHAT_MODEL = ChatModel.GLM_4.getValue();
    public static final String DEFAULT_EMBEDDING_MODEL = EmbeddingModel.EMBEDDING_2.getValue();
    private static final Predicate<String> SSE_DONE_PREDICATE = "[stop_word1]"::equals;

    private final RestClient restClient;

    private final WebClient webClient;

    public ZhiPuApi(String zhiPuAiToken) {
        this(ApiUtils.DEFAULT_BASE_URL, zhiPuAiToken);
    }

    public ZhiPuApi(String baseUrl, String zhiPuAiToken) {
        this(baseUrl, zhiPuAiToken, RestClient.builder());
    }

    public ZhiPuApi(String baseUrl, String zhiPuAiToken, RestClient.Builder restClientBuilder) {
        this(baseUrl, zhiPuAiToken, restClientBuilder, RetryUtils.DEFAULT_RESPONSE_ERROR_HANDLER);
    }

    public ZhiPuApi(String baseUrl, String zhiPuAiToken, RestClient.Builder restClientBuilder, ResponseErrorHandler responseErrorHandler) {

        this.restClient = restClientBuilder
                .baseUrl(baseUrl)
                .defaultHeaders(ApiUtils.getJsonContentHeaders(zhiPuAiToken))
                .defaultStatusHandler(responseErrorHandler)
                .build();

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(ApiUtils.getJsonContentHeaders(zhiPuAiToken))
                .build();
    }

    public enum ChatModel {
        GLM_4("glm-4"),
        GLM_4V("glm-4v"),
        GLM_3_Turbo("glm-3-turbo");

        public final String value;

        ChatModel(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum EmbeddingModel {
        EMBEDDING_2("embedding-2");
        public final String value;

        EmbeddingModel(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


}
// @formatter:on