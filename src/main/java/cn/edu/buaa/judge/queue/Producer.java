package cn.edu.buaa.judge.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Message;
import java.util.Map;

@Service
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * @param destinationName 目标通道
     * @param message		    消息内容
     */
    /**
     * @Author Lxy
     * @Date 2020/3/8 22:18
     * @Description This is description of method
     * @Param [destinationName, message]
     * @Return void
     */
    public void sendMsg(String destinationName, Long message) {
        System.out.println(destinationName+"============>>>>> 发送queue消息 " + message);
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

//    public void sendMsg(String destinationName, String message) {
//        System.out.println(destinationName+"============>>>>> 发送queue消息 " + message);
//        Destination destination = new ActiveMQQueue(destinationName);
//        jmsMessagingTemplate.convertAndSend(destination, message);
//    }
//
    public void sendMsg(String destinationName, Map<String,Object> message) {
        System.out.println(destinationName+"============>>>>> 发送queue消息 " + message);
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
