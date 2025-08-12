package org.abigfish.mcpserver.service;
public interface GzhService {

    String recommendGzhInfo();

    String bestContext();
    
    public String getWeatherByCity(String city);
}
