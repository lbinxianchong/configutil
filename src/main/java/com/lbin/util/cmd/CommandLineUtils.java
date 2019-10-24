package com.lbin.util.cmd;

import cn.hutool.core.util.RuntimeUtil;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;


/**
 * 命令行执行工具
 */

public final class CommandLineUtils {


    /**
     *
     * @param cmd 命令行执行代码
     * @date 2019/8/26 15:06
     * @Description cmd命令行执行
     * @throws Exception 无异常抛出
     */
    public static String executeCmd(String cmd) throws Exception {
        /*CommandLine commandLine = CommandLine.parse(cmd);
        DefaultExecutor executor = new DefaultExecutor();
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        executor.execute(commandLine, resultHandler);
        resultHandler.waitFor();*/
        return RuntimeUtil.execForStr(cmd);
    }

}
