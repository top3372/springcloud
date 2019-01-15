//    @Bean(name="message") 
//    public Queue queueMessage() {
//        return new Queue("topic.message"); queue的名称
//    }
//    @Bean
//    Binding bindingExchangeC(@Qualifier("Cmessage") Queue CMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(CMessage).to(fanoutExchange);
//    }

//    @Bean
//    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message"); //with 后面的 可以理解为 routing-key
//        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");//*表示一个词,#表示零个或多个词
//    将queue通过routing-key绑定到exchange上
//    }