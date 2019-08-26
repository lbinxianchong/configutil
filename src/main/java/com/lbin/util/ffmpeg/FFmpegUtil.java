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
     * @param ffmpegPath ffmpeg插件路径
	 * @param inputPath 视频转码文件路径
	 * @param format 视频格式
     * @date 2019/8/26 15:07
     * @Description 视频格式转换
     * @throws Exception 视频异常抛出
     */
    private static void ffmpegTranscod(String ffmpegPath,String inputPath,String format) throws Exception {
        int num = inputPath.lastIndexOf(".");
        String outPath=inputPath.substring(0,num+1)+format;
        String cmd = String.format("%s\\ffmpeg -i %s %s", ffmpegPath,inputPath,outPath);
        CommandLineUtils.executeCmd(cmd);
    }
    /**
     *
     * @param ffmpegPath ffmpeg插件路径
     * @param inputPath 视频转码文件路径
     * @param format 视频格式
     * @param coding 视频编码
     * @date 2019/8/26 15:07
     * @Description 视频格式转换（设置编码）
     * @throws Exception 视频异常抛出
     */
    private static void ffmpegTranscod(String ffmpegPath,String inputPath,String format,String coding) throws Exception {
        int num = inputPath.lastIndexOf(".");
        String outPath=inputPath.substring(0,num+1)+format;
        String cmd = String.format("%s\\ffmpeg -i %s -vcodec %s %s", ffmpegPath,inputPath,coding,outPath);
        CommandLineUtils.executeCmd(cmd);
    }
    /**
     *
     * @param inputPath 视频转码文件路径
     * @date 2019/8/26 15:18
     * @Description 视频格式转换MP4（h264编码）
     */
    public static void onToH264MP4(String inputPath){
        try {
            ffmpegTranscod(LocalFFmpeg,inputPath,"mp4","h264");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param inputPath 视频转码文件路径
     * @date 2019/8/26 15:18
     * @Description 视频格式转换MP4（h264编码）
     */
    public static void onToMP4(String inputPath){
        try {
            ffmpegTranscod(LocalFFmpeg,inputPath,"mp4","h264");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
