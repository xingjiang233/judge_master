package cn.edu.buaa.judge.dao;

import cn.edu.buaa.judge.bean.SimpleSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author Lxy
 * @Description 数据访问层-获取submission信息
 */
@Repository
public interface SubmissionMapper {

    /**
     * @Author Lxy
     * @Description 根据submissionId获取提交信息
     * @Param [submissionId]
     * @Return cn.edu.buaa.judge.bean.SimpleSubmission
     */
    SimpleSubmission getSubmissionById(long submissionId);
}
