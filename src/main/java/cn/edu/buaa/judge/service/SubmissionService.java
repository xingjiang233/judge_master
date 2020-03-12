package cn.edu.buaa.judge.service;

import cn.edu.buaa.judge.bean.JudgeTask;

/**
 * @Author Lxy
 * @Description 业务层-获取完整的JudgeTask
 */
public interface SubmissionService {

    JudgeTask getJudgeTask(long submissionId);
}
