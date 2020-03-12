package cn.edu.buaa.judge.bean;

import java.util.List;

/**
 * @Author Lxy
 * @Date 2020/3/10 22:10
 * @Description
 */
public class JudgeTask {
    /**
     * 提交ID
     */
    private long submissionId;
    /**
     * 题目ID
     */
    private long problemId;
    /**
     * 提交的代码所使用的编程语言ID
     * 默认为Python 3，即ID = 1
     */
    private int languageId = 1;

    /**至于编译命令，我直接写死在程序里面**/

    /**
     * 提交的代码
     */
    private String submitCode;

    /**这个属性在Problem里面！！
     * 题目运行最长时间限制，单位为毫秒
     */
    private int timeLimit;

    /**这个属性在Problem里面！！
     * 题目运行最大内存限制，单位为KB
     */
    private int memoryLimit;

    /**这个属性在Problem里面！！并且需要从样例里面查询
     * （这个能不能直接存在外面，或者可以来一个初始化，把数据库里面的测试样例都保存到文件里面！！）
     * 题目输入输出样例数组
     */
    List<InputOutputSample> inputOutputSamples;

    public JudgeTask() {
    }

    public JudgeTask(SimpleSubmission simpleSubmission, SimpleProblem simpleProblem,List<InputOutputSample> inputOutputSamples) {
        this.submissionId = simpleSubmission.getSubmissionId();
        this.problemId = simpleSubmission.getProblemId();
        this.languageId = simpleSubmission.getLanguageId();
        this.submitCode = simpleSubmission.getSubmitCode();
        this.timeLimit = simpleProblem.getTimeLimit();
        this.memoryLimit = simpleProblem.getMemoryLimit();
        this.inputOutputSamples = inputOutputSamples;
    }

    public JudgeTask(long submissionId, long problemId, int languageId, String submitCode, int timeLimit, int memoryLimit, List<InputOutputSample> inputOutputSamples) {
        this.submissionId = submissionId;
        this.problemId = problemId;
        this.languageId = languageId;
        this.submitCode = submitCode;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.inputOutputSamples = inputOutputSamples;
    }

    public long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(long submissionId) {
        this.submissionId = submissionId;
    }

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getSubmitCode() {
        return submitCode;
    }

    public void setSubmitCode(String submitCode) {
        this.submitCode = submitCode;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public List<InputOutputSample> getInputOutputSamples() {
        return inputOutputSamples;
    }

    public void setInputOutputSamples(List<InputOutputSample> inputOutputSamples) {
        this.inputOutputSamples = inputOutputSamples;
    }
}
