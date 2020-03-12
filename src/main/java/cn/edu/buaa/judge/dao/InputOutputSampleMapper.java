package cn.edu.buaa.judge.dao;

import cn.edu.buaa.judge.bean.InputOutputSample;
import cn.edu.buaa.judge.bean.SimpleProblem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Lxy
 * @Description 数据访问层-获取测试用例信息
 */
@Repository
public interface InputOutputSampleMapper {

    /**
     * @Author Lxy
     * @Description 根据problemId获取测试用例（这个测试用例已经保存在文件中，之后再考虑是不是使用初始化方法把它存起来）
     * @Param [problemId]
     * @Return java.util.List<cn.edu.buaa.judge.bean.InputOutputSample>
     */
    List<InputOutputSample> getInputOutputById(long problemId);
}
