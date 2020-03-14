package cn.edu.buaa.judge.dao;


import cn.edu.buaa.judge.bean.SimpleProblem;
import org.springframework.stereotype.Repository;

/**
 * @Author Lxy
 * @Description 数据访问层-获取problem信息
 */
@Repository
public interface ProblemMapper {

    /**
     * @Author Lxy
     * @Description 通过problemId获取problem信息
     * @Param [problemId]
     * @Return cn.edu.buaa.judge.bean.SimpleProblem
     */
    SimpleProblem getProblemById(long problemId);
}
