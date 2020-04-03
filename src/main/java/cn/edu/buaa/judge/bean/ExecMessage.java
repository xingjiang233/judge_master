package cn.edu.buaa.judge.bean;

public class ExecMessage {

    /**
     * 记录执行的错误信息和输出信息
     */
    private String error;

    private String stdout;

    public ExecMessage() {
    }

    public ExecMessage(String message, Object o) {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    @Override
    public String toString() {
        return "ExecMessage{" +
                "error='" + error + '\'' +
                ", stdout='" + stdout + '\'' +
                '}';
    }
}
