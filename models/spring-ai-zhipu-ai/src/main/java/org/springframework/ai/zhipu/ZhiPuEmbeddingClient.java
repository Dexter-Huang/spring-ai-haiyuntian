package org.springframework.ai.zhipu;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.AbstractEmbeddingClient;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;

import java.util.List;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/3/29 14:58
 */
public class ZhiPuEmbeddingClient extends AbstractEmbeddingClient {
    @Override
    public EmbeddingResponse call(EmbeddingRequest request) {
        return null;
    }

    @Override
    public List<Double> embed(Document document) {
        return null;
    }
}
