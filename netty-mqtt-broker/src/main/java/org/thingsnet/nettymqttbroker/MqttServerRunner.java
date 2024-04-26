package org.thingsnet.nettymqttbroker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MqttServerRunner implements CommandLineRunner {
    private final MqttServer mqttServer;

    public MqttServerRunner(MqttServer mqttServer) {
        this.mqttServer = mqttServer;
    }

    @Override
    public void run(String... args) throws Exception {
        mqttServer.start();
    }
}
