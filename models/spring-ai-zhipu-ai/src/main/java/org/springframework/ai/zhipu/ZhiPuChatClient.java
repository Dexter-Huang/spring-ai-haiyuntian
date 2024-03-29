package org.springframework.ai.zhipu;

import com.zhipu.oapi.service.v4.model.ChatCompletionRequest;
import com.zhipu.oapi.service.v4.model.ChatCompletionResult;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.StreamingChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.function.AbstractFunctionCallSupport;
import org.springframework.ai.model.function.FunctionCallbackContext;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.ai.zhipu.api.ZhiPuApi;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/3/29 14:58
 */
public class ZhiPuChatClient extends
        AbstractFunctionCallSupport<com.zhipu.oapi.service.v4.model.ChatMessage, com.zhipu.oapi.service.v4.model.ChatCompletionRequest, ResponseEntity<com.zhipu.oapi.service.v4.model.ChatCompletionResult>>
        implements ChatClient, StreamingChatClient {
    private static final Logger logger = LoggerFactory.getLogger(ZhiPuChatClient.class);
    private ZhiPuChatOptions defaultOptions;
    public final RetryTemplate retryTemplate;
    private final ZhiPuApi zhiPuApi;

    public ZhiPuChatClient(ZhiPuApi openAiApi) {
        this(openAiApi,
                ZhiPuChatOptions.builder().withModel(ZhiPuApi.DEFAULT_CHAT_MODEL).withTemperature(0.7f).build());
    }

    public ZhiPuChatClient(ZhiPuApi openAiApi, ZhiPuChatOptions options) {
        this(openAiApi, options, null, RetryUtils.DEFAULT_RETRY_TEMPLATE);
    }

    public ZhiPuChatClient(ZhiPuApi zhiPuApi, ZhiPuChatOptions options,
                            FunctionCallbackContext functionCallbackContext, RetryTemplate retryTemplate) {
        super(functionCallbackContext);
        Assert.notNull(zhiPuApi, "ZhiPuApi must not be null");
        Assert.notNull(options, "Options must not be null");
        Assert.notNull(retryTemplate, "RetryTemplate must not be null");
        this.zhiPuApi = zhiPuApi;
        this.defaultOptions = options;
        this.retryTemplate = retryTemplate;
    }
    @Override
    public ChatResponse call(Prompt prompt) {
        return null;
    }

    @Override
    public Flux<ChatResponse> stream(Prompt prompt) {
        return null;
    }

    @Override
    protected ChatCompletionRequest doCreateToolResponseRequest(ChatCompletionRequest previousRequest, ChatMessage responseMessage, List<ChatMessage> conversationHistory) {
        return null;
    }

    @Override
    protected List<ChatMessage> doGetUserMessages(ChatCompletionRequest request) {
        return null;
    }

    @Override
    protected ChatMessage doGetToolResponseMessage(ResponseEntity<ChatCompletionResult> response) {
        return null;
    }

    @Override
    protected ResponseEntity<ChatCompletionResult> doChatCompletion(ChatCompletionRequest request) {
        return null;
    }

    @Override
    protected boolean isToolFunctionCall(ResponseEntity<ChatCompletionResult> response) {
        return false;
    }
}
