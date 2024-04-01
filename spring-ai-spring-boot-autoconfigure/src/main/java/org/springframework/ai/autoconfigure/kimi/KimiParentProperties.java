package org.springframework.ai.autoconfigure.kimi;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/4/1 10:32
 */
public class KimiParentProperties {
    private String apiKey;

    private String baseUrl;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
