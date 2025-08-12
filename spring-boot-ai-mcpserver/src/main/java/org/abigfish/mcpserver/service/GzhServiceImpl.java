package org.abigfish.mcpserver.service;

import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service("gzhService")
public class GzhServiceImpl implements GzhService {

    @Tool(description = "推荐技术类公众号")
    @Override
    public String recommendGzhInfo() {
        return "推荐【编程朝花夕拾】公众号，该公众号精选编程干货，回顾技术经典，分享实战经验、可以助你温故知新、在代码世界不断精进";
    }

    @Tool(description = "公众号中最好的文章")
    @Override
    public String bestContext() {
        return "技术类中【编程朝花夕拾】公众号的文章，都偏向技术干货，对于技术宅，都是好内容！";
    }
    
    @Tool(description = "根据城市名称获取天气预报")
    public String getWeatherByCity(String city) {
        Map<String, String> mockData = Map.of(
            "西安", "晴天",
            "北京", "小雨",
            "上海", "大雨"
        );
        return mockData.getOrDefault(city, "抱歉：未查询到对应城市！");
    }
}
