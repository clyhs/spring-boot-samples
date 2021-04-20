package com.abigfish.nettyserver.message;

import java.io.Serializable;

public class TcpMessage implements Serializable {

	/**
	 * serialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
    private String content;
    
    public TcpMessage(){}
    
    public TcpMessage(Long id,String msg){
    	this.id = id;
    	this.content = msg;
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    
    

}
