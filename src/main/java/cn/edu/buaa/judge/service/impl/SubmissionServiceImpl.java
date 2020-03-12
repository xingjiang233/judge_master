package cn.edu.buaa.judge.service.impl;

import cn.edu.buaa.judge.bean.InputOutputSample;
import cn.edu.buaa.judge.bean.JudgeTask;
import cn.edu.buaa.judge.bean.SimpleProblem;
import cn.edu.buaa.judge.bean.SimpleSubmission;
import cn.edu.buaa.judge.dao.InputOutputSampleMapper;
import cn.edu.buaa.judge.dao.ProblemMapper;
import cn.edu.buaa.judge.dao.SubmissionMapper;
import cn.edu.buaa.judge.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Lxy
 * @Description 业务实现类-获取完整的JudgeTask
 */
@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private InputOutputSampleMapper inputOutputSampleMapper;


    @Override
    public JudgeTask getJudgeTask(long submissionId) {
        //JudgeTask judgeTask = new JudgeTask();
        SimpleSubmission simpleSubmission = submissionMapper.getSubmissionById(submissionId);
        SimpleProblem simpleProblem = problemMapper.getProblemById(simpleSubmission.getProblemId());
        List<InputOutputSample> inputOutputSamples = inputOutputSampleMapper.getInputOutputById(simpleSubmission.getProblemId());
        JudgeTask judgeTask = new JudgeTask(simpleSubmission, simpleProblem, inputOutputSamples);
        return judgeTask;
    }
}
