package cn.edu.buaa.judge.service.impl;

import cn.edu.buaa.judge.bean.ExecMessage;
import cn.edu.buaa.judge.bean.JudgeResult;
import cn.edu.buaa.judge.bean.JudgeTask;
import cn.edu.buaa.judge.bean.StdOut;
import cn.edu.buaa.judge.utils.DateUtil;
import cn.edu.buaa.judge.utils.ExecUtil;
import cn.edu.buaa.judge.utils.PropertiesUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @Author Lxy
 * @Description 判题功能实现类
 */
@Service
@Log4j
public class JudgeService {
    /**
     * RESULT_STR = [
                'Accepted',
                'Presentation Error',
                'Time Limit Exceeded',
                'Memory Limit Exceeded',
                'Wrong Answer',
                'Runtime Error',
                'Output Limit Exceeded',
                'Compile Error',
                'System Error']
     */
    private static HashMap<Integer,String> resultMap = new HashMap<>(){
        {
            put(0, "AC");
            put(1, "PE");
            put(2, "TLE");
            put(3, "MLE");
            put(4, "WA");
            put(5, "RE");
            put(6, "OLE");
            put(7, "CE");
            put(8, "SE");
        }
    };
    /**
     * @Description: 在指定的路径下创建待编译执行的文件
     * @Param languageId: 语言ID
     * @Param path: 指定的路径
     * @Param submitCode: 提交代码
     **/
    private static void createFile(int languageId, String path, String submitCode) throws Exception {
        String filename = "";
        /**
         * '1': python, '2': C, '3': C++, '4': Java
         */
        switch (languageId) {
            case 1:
                filename = "Solution.py";
                break;
            case 2:
                filename = "Solution.c";
                break;
            case 3:
                filename = "Solution.cpp";
                break;
            case 4:
                filename = "Solution.java";
                break;
        }

        File file = new File(path + "/" + filename);
        file.createNewFile();
        OutputStream output = new FileOutputStream(file);
        PrintWriter writer = new PrintWriter(output);
        writer.print(submitCode);
        writer.close();
        output.close();
    }

    /**
     * @Description 返回完整的编译命令
     * @Param [languageId, path]
     * @Return java.lang.String
     */
    private static String complie(int languageId, String path) {
        /**
         *  '1': 'python', '2': 'gcc', '3': 'g++', '4': 'javac',
         */
        String cmd = "";
        switch (languageId) {
            case 1:
                cmd = "python -m py_compile " + path + "/Solution.py";
                break;
            case 2:
                cmd = "gcc " + path + "/Solution.c -o " + path + "/Solution " ; //什么奇怪的版本参数？ + PropertiesUtil.StringValue("gccAddition");
                break;
            case 3:
                cmd = "g++ " + path + "/Solution.cpp -o " + path + "/Solution ";// + PropertiesUtil.StringValue("g++Addition");
                break;
            case 4:
                cmd = "javac " + path + "/Solution.java";
                break;
        }
        return cmd;
    }

    /**
     * @Description 返回执行命令
     * @Param [languageId, path]
     * @Return java.lang.String
     */
    private static String process(int languageId, String path) {
        /**
         *  '1': 'python Solution.py', '2': './Solution', '3': './Solution', '4': 'java Solution',
         */
        String cmd = "";
        switch (languageId) {
            case 1:
                cmd = "python " + path + "/Solution.py";
                break;
            case 2:
            case 3:
                cmd = path + "/Solution.py";
                break;
            case 4:
                cmd = "java cp " + path + "/Solution";
                break;
        }
        return cmd;
    }

    /**
     * @Description 安全验证，查看是否包含系统运行关键字
     * @Param [source]
     * @Return boolean
     */
    private static boolean verify(String source) {
        String[] keys = PropertiesUtil.StringValue("dangerousKeys").split(",");
        for (String key : keys) {
            if (source.contains(key))
                return false;
        }
        return true;
    }

    /**
     * @Description 执行judge_script，并返回结果
     * @Param [cmd, result]
     * @Return void
     */
    private static void parseToResult(String cmd, JudgeResult result) {
        ExecMessage exec = ExecUtil.exec(cmd);
        if (exec.getError() != null) {
            result.setJudgeResult(resultMap.get(5));
            log.error("=====error====" + result.getSubmissionId() + ":" + exec.getError());
            //System.out.println("=====error====" + result.getSubmissionId() + ":" + exec.getError());
        } else {
            //将JSON字符串转化为StdOut对象
            StdOut out = JSON.parseObject(exec.getStdout(), StdOut.class);
            log.info("=====stdout====" + out);
            //System.out.println("=====stdout====" + out);
            result.setJudgeResult(resultMap.get(out.getStatus()));
            //返回用例中最长的时间和空间
            result.setUsedTime(out.getMax_time().intValue());
            result.setUsedMemory(out.getMax_memory().intValue());
            //默认每个用例10分，后面再沟通
            result.setJudgeScore(out.getScore() * 10);
            //一共多少个用例，通过了多少个用例
            log.info("共有"+out.getSamples()+"个用例，通过了"+out.getScore()+"个用例。");
        }
    }

    /**
     * @Description 实现判题功能
     * @Param [task]
     * @Return cn.edu.buaa.judge.bean.JudgeResult
     */
    public static JudgeResult judge(JudgeTask task) {
        JudgeResult result = new JudgeResult();
        //设置ID和执行时间
        result.setSubmissionId(task.getSubmissionId());
        result.setExecuteTime(DateUtil.getCurrentTimestamp());
        //为每一个JudgeTask创建一个文件夹
        String path = PropertiesUtil.StringValue("workspace") + "/" + task.getSubmissionId();
        File file = new File(path);
        file.mkdirs();
        try {
            //在对应的路径下创建一个文件
            createFile(task.getLanguageId(), path, task.getSubmitCode());
        } catch (Exception e) {
            e.printStackTrace();
            //System Error
            result.setJudgeResult(resultMap.get(8));
            //删除创建的目录
            ExecUtil.exec("rm -rf " + path);
            return result;
        }
        //安全验证
        if (!verify(task.getSubmitCode())) {
            //包含损害系统安全的关键字 System Error
            result.setJudgeResult(resultMap.get(8));
            ExecUtil.exec("rm -rf " + path);
            return result;
        }

        //获得编译指令
        String compile = complie(task.getLanguageId(), path);
        if (compile == null) {
            //Compile Error
            result.setJudgeResult(resultMap.get(7));
            ExecUtil.exec("rm -rf " + path);
            return result;
        }
        //获得执行指令
        String process = process(task.getLanguageId(), path);
        if (process == null) {
            //Runtime Error
            result.setJudgeResult(resultMap.get(5));
            ExecUtil.exec("rm -rf " + path);
            return result;
        }
        //给目录赋予权限
        ExecUtil.exec("chmod -R 755 " + path);

        //judge
        String testdata = PropertiesUtil.StringValue("testdata") + "/" + task.getProblemId();
        //执行python命令，并传入参数
        String cmd = "python " + PropertiesUtil.StringValue("judge_script") + " " + compile + " "
                + process + " " + testdata + " " + path + " " + task.getTimeLimit() + " " + task.getMemoryLimit();
        parseToResult(cmd, result);
        //最终删除工作目录并退出
        ExecUtil.exec("rm -rf " + path);
        return result;
    }
}
