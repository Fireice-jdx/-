package cn.jdx.mp4tools.utils;

import org.bytedeco.javacv.*;

import java.math.BigDecimal;
/*
这个类是个工具类，只需要调用
MergeMovieAndVoiceUtil.mergeAudioAndVideo(视频路径，音频路径，输出路径);

 */
public class MergeMovieAndVoiceUtil {

    private static String deciMal(int currentNum, int totalNum) {
        double result = new BigDecimal((float) currentNum / totalNum).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        return ((int)(result * 100)) + "%";
    }

    public static void mergeAudioAndVideo(String videoPath, String audioPath, String outPut) {
        FrameRecorder recorder;
        FrameGrabber videoFrameGrabber;
        FrameGrabber audioFrameGrabber;

        videoFrameGrabber = new FFmpegFrameGrabber(videoPath);//抓取视频帧
        audioFrameGrabber = new FFmpegFrameGrabber(audioPath);//抓取音频帧

        try {
            audioFrameGrabber.start();
        } catch (FrameGrabber.Exception e) {
            System.err.println("[错误信息]:音频捕捉\n" +
                    "\t[路径]:"+audioPath+"\n" +
                    "\t[信息]:"+e);
        }

        try {
            videoFrameGrabber.start();
        } catch (FrameGrabber.Exception e) {
            System.err.println("[错误信息]:视频捕捉\n" +
                    "\t[路径]:"+videoPath+"\n" +
                    "\t[信息]:"+e);
        }
        System.out.println("[处理目标]:"+outPut);
        System.out.println("[视频宽高]:"+videoFrameGrabber.getImageWidth()+"/"+videoFrameGrabber.getImageHeight());
        System.out.println("[声道码率]:"+audioFrameGrabber.getAudioChannels());
        //创建录制
        recorder = new FFmpegFrameRecorder(outPut,
                videoFrameGrabber.getImageWidth(), videoFrameGrabber.getImageHeight(),
                audioFrameGrabber.getAudioChannels());
        recorder.setFormat("mp4");
        //recorder.setAudioChannels(1);//补充一下声道
        recorder.setFrameRate(videoFrameGrabber.getFrameRate());
        recorder.setSampleRate(audioFrameGrabber.getSampleRate());
        recorder.setAudioQuality(0);
        recorder.setVideoQuality(0);

        try {
            recorder.start();
        } catch (FrameRecorder.Exception e) {
            System.err.println("[错误信息]:开启录制器错误\n" +
                    "\t[视频路径]:"+videoPath+"\n" +
                    "\t[音频路径]:"+audioPath+"\n" +
                    "\t[信息]:"+e);
        }

        try {
            Frame videoFrame;
            Frame audioFrame;

            //先录入视频
            int videoLengthInFrames = videoFrameGrabber.getLengthInFrames();
            String upProcess = "";

            while ((videoFrame = videoFrameGrabber.grabFrame()) != null) {
                recorder.record(videoFrame);

                //计算当前进度
                String currentProcess = deciMal(videoFrameGrabber.getFrameNumber(), videoLengthInFrames);
                if (!currentProcess.equals(upProcess)){
                    upProcess = currentProcess;
                    System.out.println("[执行信息]:[视频]当前录入进度:" + currentProcess);
                }
            }

            //再录入音频
            System.out.println("[执行信息]:正在合并音频...");
            while ((audioFrame = audioFrameGrabber.grabFrame()) != null) {
                recorder.record(audioFrame);
            }

            System.out.println("[执行信息]:执行完成");
            videoFrameGrabber.stop();
            audioFrameGrabber.stop();
            recorder.stop();

        } catch (FrameRecorder.Exception | FrameGrabber.Exception e) {
            System.err.println("[出现错误]:合并错误:"+e);
        } finally {
            try {
                recorder.release();
                videoFrameGrabber.release();
                audioFrameGrabber.release();
            } catch (FrameRecorder.Exception | FrameGrabber.Exception e) {
                System.err.println("[出现错误]:" + e);
            }

        }
    }


}




















