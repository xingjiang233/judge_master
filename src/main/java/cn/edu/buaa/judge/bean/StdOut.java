package cn.edu.buaa.judge.bean;

public class StdOut {
    private Integer status;
    private Long max_memory;
    private Long max_time;
    //记录通过了多少用例
    private Integer score;
    //记录一共有多少个用例
    private Integer samples;

    public StdOut() {
    }

    public StdOut(Integer status, Long max_memory, Long max_time) {
        this.status = status;
        this.max_memory = max_memory;
        this.max_time = max_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getMax_memory() {
        return max_memory;
    }

    public void setMax_memory(Long max_memory) {
        this.max_memory = max_memory;
    }

    public Long getMax_time() {
        return max_time;
    }

    public void setMax_time(Long max_time) {
        this.max_time = max_time;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSamples() {
        return samples;
    }

    public void setSamples(Integer samples) {
        this.samples = samples;
    }
}
