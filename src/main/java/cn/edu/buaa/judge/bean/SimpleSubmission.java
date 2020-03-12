package cn.edu.buaa.judge.bean;

import java.sql.Timestamp;

public class SimpleSubmission {
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

    /**
     * 提交的代码
     */
    private String submitCode;


    public SimpleSubmission() {
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


    public String getSubmitCode() {
        return submitCode;
    }

    public void setSubmitCode(String submitCode) {
        this.submitCode = submitCode;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}
