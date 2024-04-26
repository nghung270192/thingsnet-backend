package org.thingsnet.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumerRunner implements CommandLineRunner {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void run(String... args) throws Exception {
        KafkaMessageConsumer kafkaMessageConsumer = new KafkaMessageConsumer(messagingTemplate);
        kafkaMessageConsumer.consumeMessages();
    }
}
