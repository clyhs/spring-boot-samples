# mqtt

> MQTT是一个轻量级的消息发布/订阅协议
>
> 实现MQTT协议的中间件有很多，我用的是Apollo服务器，如何搭建MQTT服务器，请查阅其他资料



## mqttConfig

配置类

## mqttGateway接口类

在sendToMqtt(String data,@Header(MqttHeaders.TOPIC)String topic)接口中，data为发送的消息内容，topic为主题。指定topic，则我们的接口可以根据需要，向不同的主题发送消息，方便灵活应用。如果不指定，则使用默认配置的主题

如果我要配置多个client，配置多个通道即可



        //mqtt入站消息处理配置
    	@Bean
        public MessageChannel mqttInputChannelTow() {
            return new DirectChannel();
        }
    	
    	//配置client,监听的topic 
    	@Bean
        public MessageProducer inboundTow() {
            if (adapter == null) {
                adapter = new MqttPahoMessageDrivenChannelAdapter(
                        mqttProperties.getClientId() + "_inbound2",
                        mqttClientFactory(),
                        "hello3", "hello4");
            }
            adapter.setCompletionTimeout(3000);
            adapter.setConverter(new DefaultPahoMessageConverter());
            adapter.setQos(1);
            adapter.setOutputChannel(mqttInputChannelTow());
            return adapter;
        }
    
        @Bean
        @ServiceActivator(inputChannel = "mqttInputChannelTow")
        public MessageHandler handlerTow() {
            return new MyMqttMessageHandler();
        }


