package org.springframework.ai.zhipu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallingOptions;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/3/29 17:16
 */
public class ZhiPuChatOptions implements FunctionCallingOptions, ChatOptions {
    private String model;
    private List<com.zhipu.oapi.service.v4.model.ChatMessage> messages;
    private @JsonProperty("request_id") String requestId;
    private @JsonProperty("do_sample") Boolean doSample;
    private Boolean stream;
    private Float temperature;
    private @JsonProperty("top_p") Float topP;
    private @JsonProperty("max_tokens") Integer maxTokens;
    private List<String> stop;
    private @JsonProperty("sensitive_word_check") com.zhipu.oapi.service.v4.model.SensitiveWordCheckRequest sensitiveWordCheck;
    private List<com.zhipu.oapi.service.v4.model.ChatTool> tools;
    private @JsonProperty("tool_choice") Object toolChoice;
    private @JsonProperty("invoke_method") String invokeMethod;

    @Override
    public Float getTemperature() {
        return this.temperature;
    }

    @Override
    public Float getTopP() {
        return this.topP;
    }

    @Override
    @JsonIgnore
    public Integer getTopK() {
        throw new UnsupportedOperationException("Unimplemented method 'getTopK'");
    }

    @Override
    public List<FunctionCallback> getFunctionCallbacks() {
        return null;
    }

    @Override
    public void setFunctionCallbacks(List<FunctionCallback> functionCallbacks) {

    }

    @Override
    public Set<String> getFunctions() {
        return null;
    }

    @Override
    public void setFunctions(Set<String> functions) {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected ZhiPuChatOptions options;

        public Builder() {
            this.options = new ZhiPuChatOptions();
        }

        public Builder(ZhiPuChatOptions options) {
            this.options = options;
        }

        public Builder withModel(String model) {
            this.options.model = model;
            return this;
        }

        public Builder withStop(List<String> stop) {
            this.options.stop = stop;
            return this;
        }

        public Builder withTemperature(Float temperature) {
            this.options.temperature = temperature;
            return this;
        }

        public Builder withTopP(Float topP) {
            this.options.topP = topP;
            return this;
        }

        public Builder withTools(List<com.zhipu.oapi.service.v4.model.ChatTool> tools) {
            this.options.tools = tools;
            return this;
        }

        public Builder withToolChoice(String toolChoice) {
            this.options.toolChoice = toolChoice;
            return this;
        }

        public ZhiPuChatOptions build() {
            return this.options;
        }

    }
}
