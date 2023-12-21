package com.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SubscriberEntity 
{
	@KafkaListener(topics="product-kafka-topic", groupId="mygroup")
	public void consumeFromTopic(String message)
	{
		System.out.println("Consumer message is:  "+ message);
		
	}
		
}
