package cn.edu.buaa.judge.bean;

import java.sql.Timestamp;

public class JudgeResult {
    /**
     * 提交ID
     */
    private long submissionId;

    /**
     * 代码评测执行时间
     */
    private Timestamp executeTime;

    /**
     * 代码执行耗时
     */
    private Integer usedTime;

    /**
     * 代码执行内存消耗
     */
    private Integer usedMemory;

    /**
     * 评测得分
     */
    private int judgeScore;

    /**
     * 评测结果，默认为"PD"(Pending)
     */
    private String judgeResult = "PD";

    /** 编译输出是个啥？ 可以修改一下底层的C
     * 编译输出
     */
    private String compileOutput;

    public JudgeResult(long submissionId, Timestamp executeTime, Integer usedTime, Integer usedMemory, int judgeScore, String judgeResult, String compileOutput) {
        this.submissionId = submissionId;
        this.executeTime = executeTime;
        this.usedTime = usedTime;
        this.usedMemory = usedMemory;
        this.judgeScore = judgeScore;
        this.judgeResult = judgeResult;
        this.compileOutput = compileOutput;
    }

    public JudgeResult() {
    }

    public long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(long submissionId) {
        this.submissionId = submissionId;
    }

    public Timestamp getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Timestamp executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Integer usedTime) {
        this.usedTime = usedTime;
    }

    public Integer getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(Integer usedMemory) {
        this.usedMemory = usedMemory;
    }

    public int getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(int judgeScore) {
        this.judgeScore = judgeScore;
    }

    public String getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(String judgeResult) {
        this.judgeResult = judgeResult;
    }

    public String getCompileOutput() {
        return compileOutput;
    }

    public void setCompileOutput(String compileOutput) {
        this.compileOutput = compileOutput;
    }
}
