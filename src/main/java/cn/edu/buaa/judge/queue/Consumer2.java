package cn.edu.buaa.judge.queue;

import cn.edu.buaa.judge.bean.JudgeTask;
import cn.edu.buaa.judge.service.impl.SubmissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer2 {


    /**
     * @Author Lxy
     * @Date 2020/3/8 20:55
     * @Description 监听judge_result_queue获取判题结果
     */
    @JmsListener(destination = "judge_result_queue")
    public void receiveMsg(String text) {
        System.out.println("judge_result_queue<<<<<<============ 收到消息： " + text);
        //业务逻辑

    }
}
