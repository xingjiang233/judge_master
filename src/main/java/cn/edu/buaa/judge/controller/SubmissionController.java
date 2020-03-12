package cn.edu.buaa.judge.controller;

import cn.edu.buaa.judge.bean.JudgeTask;
import cn.edu.buaa.judge.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "SubmissionCenter")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @RequestMapping(value = "/GetJudgeTask.do", method = RequestMethod.GET)
    public JudgeTask getJudgeTask(long submissionId){
        return submissionService.getJudgeTask(submissionId);
    }
}
