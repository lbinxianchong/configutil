package com.lbin.util.cmd;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;


/**
 * 命令行执行工具
 */

public final class CommandLineUtils {


    /**
     *
     * @参数 cmd
     * @返回值 void
     * @开发者 lbin
     * @更新时间 2019/8/21 16:11
     * @描述 cmd命令行执行
     */
    public static void executeCmd(String cmd) throws Exception {
        System.out.println("cmd={}."+cmd);
        CommandLine commandLine = CommandLine.parse(cmd);
        DefaultExecutor executor = new DefaultExecutor();
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        executor.execute(commandLine, resultHandler);
        resultHandler.waitFor();
    }

}
