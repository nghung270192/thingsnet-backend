package org.thingsnet.nettymqttbroker;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MqttServerHandler extends SimpleChannelInboundHandler<MqttMessage> {

    private static final Properties kafkaProperties = KafkaConfig.getKafkaProperties();
    private KafkaProducer<String, String> kafkaProducer;

    public MqttServerHandler() {
        // Initialize Kafka producer
        kafkaProducer = new KafkaProducer<>(kafkaProperties);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MqttMessage message) throws Exception {
        try {
            System.out.println("Received MQTT message: " + message.fixedHeader().messageType());
            switch (message.fixedHeader().messageType()) {
                case CONNECT:
                    handleConnect(ctx, (MqttConnectMessage) message);
                    break;
                case PUBLISH:
                    handlePublish(ctx, (MqttPublishMessage) message);
                    break;
                case SUBSCRIBE:
                    handleSubscribe(ctx, (MqttSubscribeMessage) message);
                    break;
                default:
                    System.out.println("Unhandled message type: " + message.fixedHeader().messageType());
            }
        } finally {
//            ReferenceCountUtil.release(message);
        }
    }

    private void handleConnect(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        System.out.println("Client " + msg.payload().clientIdentifier() + " requested connect");

        // Acknowledge the connection
        MqttConnAckMessage connAck = new MqttConnAckMessage(new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0), new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, false));
        ctx.writeAndFlush(connAck);
    }

    private void handlePublish(ChannelHandlerContext ctx, MqttPublishMessage message) {
        String topic = message.variableHeader().topicName();
        String payload = message.payload().toString(io.netty.util.CharsetUtil.UTF_8);
        System.out.println("Message received on topic: " + topic + " with payload: " + payload);

        //use kafka to publish message
        kafkaProducer.send(new ProducerRecord<>("mqtt-topic", payload));
    }

    private void handleSubscribe(ChannelHandlerContext ctx, MqttSubscribeMessage msg) {
        for (MqttTopicSubscription subscription : msg.payload().topicSubscriptions()) {
            System.out.println("Subscription requested for topic: " + subscription.topicName() + " with QoS: " + subscription.qualityOfService());
            // Acknowledge the subscription
            MqttSubAckMessage subAck = new MqttSubAckMessage(new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0), MqttMessageIdVariableHeader.from(msg.variableHeader().messageId()), new MqttSubAckPayload(0) // 0 for Success - Maximum QoS 0
            );
            ctx.writeAndFlush(subAck);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}