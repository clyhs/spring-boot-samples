package org.abigfish.rest;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.internal.Json;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.output.Response;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChatController {
	
	
	ChatLanguageModel chatLanguageModel;
	
	StreamingChatLanguageModel streamingChatLanguageModel;

	ChatController(ChatLanguageModel chatLanguageModel,StreamingChatLanguageModel streamingChatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
        this.streamingChatLanguageModel = streamingChatLanguageModel;
    }


    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
    	return Map.of("generation", chatLanguageModel.generate(message));
    }

    
    @GetMapping("/ai/generateStream")
    @ResponseBody
	public String generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
    	
    	CompletableFuture<Response<AiMessage>> future = new CompletableFuture<>();
    	try {
    		streamingChatLanguageModel.generate(message, new StreamingResponseHandler<AiMessage>() {

    			@Override
                public void onNext(String token) {
                    System.out.print(token);
                }

                @Override
                public void onComplete(Response<AiMessage> response) {
                	System.out.println(Json.toJson(response));
                	future.complete(response);
                }

                @Override
                public void onError(Throwable error) {
                	future.completeExceptionally(error);
                }
            });
    		System.out.println("wait");
    		future.join();
    		System.out.println("end");
    		System.out.println(future.get().content().text());
            return future.get().content().text();
    	}catch(Exception e) {
    		log.error("",e);
    	}
        return null;
    }

}
