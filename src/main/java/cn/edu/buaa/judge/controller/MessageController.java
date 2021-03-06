package cn.edu.buaa.judge.controller;

import cn.edu.buaa.judge.queue.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "MessageCenter")
public class MessageController {

    @Autowired
    //创建一个生产者，消费者在系统运行时已经创建
    private Producer producer;

    /**
     * 1、如果说Controller在judge端写的话，那边需要集成HttpClient
     *
     * 2、如果说制造者在那边的话，judge端只需要对ActiveMQ约定好的通道进行监听处理即可
     */

    /**
     * @param msg 	消息内容
     * @param queue	目标通道名
     */
    @RequestMapping(value = "/SendMessageByQueue.do", method = RequestMethod.GET)
    public void send(Long msg,String queue) {
        try {
            //queue = submission_queue
            System.out.println("开始发出一次请求，时间是"+new Date());
            //Map<String,Object> map = new HashMap<>();
            //map.put("submissionId", msg);
            producer.sendMsg(queue,msg);
            System.out.println(msg+"请求发送完成，时间是"+new Date());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
