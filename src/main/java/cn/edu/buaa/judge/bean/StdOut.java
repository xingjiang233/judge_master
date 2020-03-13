package cn.edu.buaa.judge.bean;

public class StdOut {
    private Integer status;
    private Long max_memory;
    private Long max_time;

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
}
