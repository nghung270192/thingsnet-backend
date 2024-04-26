package org.thingsnet.nettymqttbroker;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public class KafkaConfig {

    public static Properties getKafkaProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "mqtt-consumer-group");

        // Add more Kafka properties as needed
        return props;
    }
}