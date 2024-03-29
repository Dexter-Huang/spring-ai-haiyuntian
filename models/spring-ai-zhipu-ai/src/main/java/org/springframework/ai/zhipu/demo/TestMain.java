package org.springframework.ai.zhipu.demo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.service.v4.model.*;
import io.reactivex.Flowable;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/3/29 15:47
 */
public class TestMain {

    private static final ClientV4 client = (new ClientV4.Builder("ac1a3671a345ec15d8960e76dfcbf0e0.ZeJRjXbFlDW1zBrq")).build();
    private static final ObjectMapper mapper = defaultObjectMapper();
    public static void main(String[] args) {
        List<ChatMessage> messages = new ArrayList();
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), "ChatGLM和你哪个更强大");
        messages.add(chatMessage);
        String requestId = String.format("mycompany-%d", System.currentTimeMillis());
        List<ChatTool> chatToolList = new ArrayList();
        ChatTool chatTool = new ChatTool();
        chatTool.setType(ChatToolType.FUNCTION.value());
        ChatFunctionParameters chatFunctionParameters = new ChatFunctionParameters();
        chatFunctionParameters.setType("object");
        Map<String, Object> properties = new HashMap();
        properties.put("departure", new HashMap<String, Object>() {
            {
                this.put("type", "string");
                this.put("description", "出发城市或车站");
            }
        });
        properties.put("destination", new HashMap<String, Object>() {
            {
                this.put("type", "string");
                this.put("description", "目的地城市或车站");
            }
        });
        properties.put("date", new HashMap<String, Object>() {
            {
                this.put("type", "string");
                this.put("description", "要查询的车次日期");
            }
        });
        List<String> required = new ArrayList();
        required.add("departure");
        required.add("destination");
        required.add("date");
        chatFunctionParameters.setProperties(properties);
        ChatFunction chatFunction = ChatFunction.builder().name("query_train_info").description("根据用户提供的信息，查询对应的车次").parameters(chatFunctionParameters).required(required).build();
        chatTool.setFunction(chatFunction);
        chatToolList.add(chatTool);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder().model("GLM-4").stream(Boolean.TRUE).messages(messages).requestId(requestId).tools(chatToolList).toolChoice("auto").build();
        ModelApiResponse sseModelApiResp = client.invokeModelApi(chatCompletionRequest);
        if (sseModelApiResp.isSuccess()) {
            AtomicBoolean isFirst = new AtomicBoolean(true);
            Flowable var10000 = mapStreamToAccumulator(sseModelApiResp.getFlowable()).doOnNext((accumulator) -> {
                if (isFirst.getAndSet(false)) {
                    System.out.print("Response: ");
                }

                if (accumulator.getDelta() != null && accumulator.getDelta().getTool_calls() != null) {
                    String jsonString = mapper.writeValueAsString(accumulator.getDelta().getTool_calls());
                    System.out.println("tool_calls: " + jsonString);
                }

                if (accumulator.getDelta() != null && accumulator.getDelta().getContent() != null) {
                    System.out.print(accumulator.getDelta().getContent());
                }

            });
            PrintStream var10001 = System.out;
            var10001.getClass();
            ChatMessageAccumulator chatMessageAccumulator = (ChatMessageAccumulator)var10000.doOnComplete(var10001::println).lastElement().blockingGet();
            Choice choice = new Choice(chatMessageAccumulator.getChoice().getFinishReason(), 0L, chatMessageAccumulator.getDelta());
            List<Choice> choices = new ArrayList();
            choices.add(choice);
            ModelData data = new ModelData();
            data.setChoices(choices);
            data.setUsage(chatMessageAccumulator.getUsage());
            data.setId(chatMessageAccumulator.getId());
            data.setCreated(chatMessageAccumulator.getCreated());
            data.setRequestId(chatCompletionRequest.getRequestId());
            sseModelApiResp.setFlowable((Flowable)null);
            sseModelApiResp.setData(data);
        }

        System.out.println("model output:" + JSON.toJSONString(sseModelApiResp));
    }

    public static Flowable<ChatMessageAccumulator> mapStreamToAccumulator(Flowable<ModelData> flowable) {
        return flowable.map((chunk) -> {
            return new ChatMessageAccumulator(((Choice)chunk.getChoices().get(0)).getDelta(), (ChatMessage)null, (Choice)chunk.getChoices().get(0), chunk.getUsage(), chunk.getCreated(), chunk.getId());
        });
    }

    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.addMixIn(ChatFunction.class, ChatFunctionMixIn.class);
        mapper.addMixIn(ChatCompletionRequest.class, ChatCompletionRequestMixIn.class);
        mapper.addMixIn(ChatFunctionCall.class, ChatFunctionCallMixIn.class);
        return mapper;
    }
}
