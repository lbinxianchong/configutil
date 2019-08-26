package com.lbin.util.ffmpeg;

import com.lbin.util.ConfigProperties;
import com.lbin.util.cmd.CommandLineUtils;

/**
 * 视频转码工具FFmpeg
 */
public class FFmpegUtil {
    private static String LocalFFmpeg= ConfigProperties.getConfig("ffmpeg");
    /**
     *
     * @参数 ffmpegPath
	 * @参数 inputPath
     * @返回值 void
     * @开发者 lbin
     * @更新时间 2019/8/21 16:18
     * @描述 视频格式转换MP4
     */
    private static void ffmpegToMp4(String ffmpegPath,String inputPath) throws Exception {
        int num = inputPath.lastIndexOf(".");
        String outPath=inputPath.substring(0,num+1)+"mp4";
        String cmd = String.format("%s\\ffmpeg -i %s -vcodec h264 %s", ffmpegPath,inputPath,outPath);
        CommandLineUtils.executeCmd(cmd);
    }
    public static void onToMP4(String inputPath){
        try {
            ffmpegToMp4(LocalFFmpeg,inputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
