package cn.edu.buaa.judge.queue;

import cn.edu.buaa.judge.bean.JudgeResult;
import cn.edu.buaa.judge.bean.JudgeTask;
import cn.edu.buaa.judge.service.impl.JudgeService;
import cn.edu.buaa.judge.service.impl.SubmissionServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private Producer producer;

    @Autowired
    private SubmissionServiceImpl submissionService;

    @Autowired
    private JudgeService judgeService;
    /**
     * @Author Lxy
     * @Date 2020/3/8 20:40
     * @Description 监听submission_queue 获取题目的ID,一旦发现有新的题目提交就去进行判题的操作
     */
    @JmsListener(destination = "submission_queue")
    public void receiveMsg(String text) {
        System.out.println("submission_queue<<<<<<============ 收到判题ID： " + text);
        //业务逻辑 在这里进行判题，直接把结果给它送回去
        //获得JudgeTask
        JudgeTask judgeTask = submissionService.getJudgeTask(Long.parseLong(text));
        // 调用Judge功能
        JudgeResult judgeResult = judgeService.judge(judgeTask);
        //返回judge结果
        String result = JSON.toJSONString(judgeResult);
        System.out.println("题目ID—" + text + "的判题结果是：" + result);
        producer.sendMsg("judge_result_queue",result);

    }
}
