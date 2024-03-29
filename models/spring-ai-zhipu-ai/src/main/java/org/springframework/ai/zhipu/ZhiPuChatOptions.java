package org.springframework.ai.zhipu;

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
    @Override
    public Float getTemperature() {
        return null;
    }

    @Override
    public Float getTopP() {
        return null;
    }

    @Override
    public Integer getTopK() {
        return null;
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

        public Builder withFrequencyPenalty(Float frequencyPenalty) {
            this.options.frequencyPenalty = frequencyPenalty;
            return this;
        }

        public Builder withLogitBias(Map<String, Integer> logitBias) {
            this.options.logitBias = logitBias;
            return this;
        }

        public Builder withMaxTokens(Integer maxTokens) {
            this.options.maxTokens = maxTokens;
            return this;
        }

        public Builder withN(Integer n) {
            this.options.n = n;
            return this;
        }

        public Builder withPresencePenalty(Float presencePenalty) {
            this.options.presencePenalty = presencePenalty;
            return this;
        }

        public Builder withResponseFormat(ResponseFormat responseFormat) {
            this.options.responseFormat = responseFormat;
            return this;
        }

        public Builder withSeed(Integer seed) {
            this.options.seed = seed;
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

        public Builder withTools(List<FunctionTool> tools) {
            this.options.tools = tools;
            return this;
        }

        public Builder withToolChoice(String toolChoice) {
            this.options.toolChoice = toolChoice;
            return this;
        }

        public Builder withUser(String user) {
            this.options.user = user;
            return this;
        }

        public Builder withFunctionCallbacks(List<FunctionCallback> functionCallbacks) {
            this.options.functionCallbacks = functionCallbacks;
            return this;
        }

        public Builder withFunctions(Set<String> functionNames) {
            Assert.notNull(functionNames, "Function names must not be null");
            this.options.functions = functionNames;
            return this;
        }

        public Builder withFunction(String functionName) {
            Assert.hasText(functionName, "Function name must not be empty");
            this.options.functions.add(functionName);
            return this;
        }

        public ZhiPuChatOptions build() {
            return this.options;
        }

    }
}
