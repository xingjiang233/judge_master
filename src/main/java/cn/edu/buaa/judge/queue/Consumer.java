package cn.edu.buaa.judge.queue;

import cn.edu.buaa.judge.bean.JudgeResult;
import cn.edu.buaa.judge.bean.JudgeTask;
import cn.edu.buaa.judge.service.impl.JudgeService;
import cn.edu.buaa.judge.service.impl.SubmissionServiceImpl;
import cn.edu.buaa.judge.utils.DateUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j
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
    public void receiveMsg(Long text) throws Exception {
        log.info("submission_queue<<<<<<============ 收到判题ID： " + text);
        //System.out.println("submission_queue<<<<<<============ 收到判题ID： " + text);
        //业务逻辑 在这里进行判题，把结果送回去
        //获得JudgeTask
        JudgeTask judgeTask = submissionService.getJudgeTask(text);
        log.info("题目ID—" + text + "是：" + judgeTask);
        // 调用Judge功能
        JudgeResult JR = judgeService.judge(judgeTask);
        //返回judge结果
//        String result = JSON.toJSONString(judgeResult);
        //System.out.println("题目ID—" + text + "的判题结果是：" + result);
        log.info("题目ID—" + text + "的判题结果是：" + JR);
        //JudgeResult JR = new JudgeResult();
        //JR.setJudgeScore(10);
        //JR.setSubmissionId(text);
        //JR.setExecuteTime(DateUtil.getCurrentTimestamp());
        Map<String,Object> result = new HashMap<>();
        result.put("submissionId", JR.getSubmissionId());
        result.put("executeTime", DateUtil.formatTimestamp((JR.getExecuteTime())));
        result.put("usedTime",JR.getUsedTime());
        result.put("usedMemory",JR.getUsedMemory());
        result.put("judgeScore", JR.getJudgeScore());
        result.put("judgeResult", JR.getJudgeResult());

        producer.sendMsg("judge_result_queue",result);

    }

//    @JmsListener(destination = "submission_queue")
//    public void receiveMsg(Message message) throws JMSException {
//        final MapMessage mapMessage = (MapMessage) message;
//        log.info("submission_queue<<<<<<============ 收到判题ID： " + mapMessage.getLongProperty("submissionId"));
//        //System.out.println("submission_queue<<<<<<============ 收到判题ID： " + text);
//        //业务逻辑 在这里进行判题，把结果送回去
//        //获得JudgeTask
////        JudgeTask judgeTask = submissionService.getJudgeTask(Long.parseLong(text));
////        // 调用Judge功能
////        JudgeResult judgeResult = judgeService.judge(judgeTask);
////        //返回judge结果
////        String result = JSON.toJSONString(judgeResult);
////        //System.out.println("题目ID—" + text + "的判题结果是：" + result);
////        log.info("题目ID—" + text + "的判题结果是：" + result);
//        JudgeResult JR = new JudgeResult();
//        //JR.setJudgeScore(10);
//        JR.setSubmissionId(message.getLongProperty("submissionId"));
//        JR.setExecuteTime(DateUtil.getCurrentTimestamp());
//        Map<String,Object> result = new HashMap<>();
//        result.put("submissionId", JR.getSubmissionId());
//        result.put("executeTime", JR.getExecuteTime());
//        result.put("usedTime",JR.getExecuteTime());
//        result.put("usedMemory",JR.getUsedTime());
//        result.put("judgeScore", JR.getJudgeScore());
//        result.put("judgeResult", JR.getJudgeResult());
//
//        producer.sendMsg("judge_result_queue",result);
//
//    }
}
