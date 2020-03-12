package cn.edu.buaa.judge.bean;


import java.util.List;

public class SimpleProblem {
    /**
     * 题目ID
     */
    private long problemId;

    /** 这里需不需要做一些判断？ 可以最后添加一波
     * 题目的评测机制，0 - 在线评测，1 - 人工评测
     */
    private int judgeMechanism;

    /**
     * 题目运行最长时间限制，单位为毫秒
     */
    private int timeLimit;

    /**
     * 题目运行最大内存限制，单位为KB
     */
    private int memoryLimit;


    public SimpleProblem() {
    }

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }

    public int getJudgeMechanism() {
        return judgeMechanism;
    }

    public void setJudgeMechanism(int judgeMechanism) {
        this.judgeMechanism = judgeMechanism;
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

}
