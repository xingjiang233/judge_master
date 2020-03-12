package cn.edu.buaa.judge.queue;

import cn.edu.buaa.judge.bean.JudgeTask;
import cn.edu.buaa.judge.service.impl.SubmissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private Producer producer;

    @Autowired
    private SubmissionServiceImpl submissionService;
    /**
     * @Author Lxy
     * @Date 2020/3/8 20:40
     * @Description 监听submission_queue 获取题目的ID,一旦发现有新的题目提交就去进行判题的操作
     */
    @JmsListener(destination = "submission_queue")
    public void receiveMsg(String text) {
        System.out.println("submission_queue<<<<<<============ 收到消息： " + text);
        //业务逻辑 在这里进行判题，直接把结果给它送回去
        //获得JudgeTask
        JudgeTask judgeTask = submissionService.getJudgeTask(Long.parseLong(text));
        //调用Judge功能


        System.out.println(text + "的判题结果是：xxxx");
        producer.sendMsg("judge_result_queue","result of"+text);

    }
}
