package com.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataPublisher 
{
	public static final String topic ="product-kafka-topic";
	
	@Autowired
	private KafkaTemplate<String, String> tempObj;

	public KafkaTemplate<String, String> getTempObj() {
		return tempObj;
	}

	public void setTempObj(String message) {
		this.tempObj.send(topic,message);
	}
	


	public static String getTopic() {
		return topic;
	}
	
	

}
