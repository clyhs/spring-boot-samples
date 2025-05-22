package org.abigfish.mcpserver.config;

import org.abigfish.mcpserver.service.GzhService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolCallbackProviderConfig {

    @Bean
    public ToolCallbackProvider gzhRecommendTools(GzhService gzhService) {
        return MethodToolCallbackProvider.builder().toolObjects(gzhService).build();
    }
}
