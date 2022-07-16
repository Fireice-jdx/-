package cn.jdx.mp4tools.bilibili;

import cn.jdx.mp4tools.utils.config.OutputName;
import cn.jdx.mp4tools.utils.config.VideoHandlerConfiguration;
import cn.jdx.mp4tools.utils.MergeMovieAndVoiceUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class BiliBiliVideoHandler {
    private final VideoHandlerConfiguration configuration;
    private final OutputName outputName;
    private final boolean isJustShowName;
    public BiliBiliVideoHandler(VideoHandlerConfiguration configuration){
        this.configuration = configuration;
        this.outputName = configuration.getOutputName();
        this.isJustShowName = configuration.isJustShowName();
    }

    public void merge() throws FileNotFoundException {
        //遍历父目录
        String parentDirPath = configuration.getInputParentPath();
        File[] files = new File(parentDirPath).listFiles();
        assert files != null;
        for (File file : files) {
            String absolutePath = file.getAbsolutePath();
            String entryJsonPath = new File(absolutePath,"entry.json").getAbsolutePath();
            BiliBiliJsonResolver biliBiliJsonResolver = new BiliBiliJsonResolver();
            //拿到视频的名称
            String originName = biliBiliJsonResolver.getVideoNameFromJson(entryJsonPath);
            //根据用户自定义名称
            String newName = originName+".mp4";
            if (outputName!=null){
                newName = outputName.returnNewName(originName)+".mp4";
            }
            if (isJustShowName){
                System.out.println(newName);
            }else{
                //再次进入80文件夹
                String videoPath = new File(absolutePath,"80").getAbsolutePath();
                String mp4Path = new File(videoPath,"video.m4s").getAbsolutePath();
                String mp3Path = new File(videoPath,"audio.m4s").getAbsolutePath();
                //处理输出路径
                String outPathStr = configuration.getOutputPath();
                String outPath = new File(parentDirPath,newName).getAbsolutePath();
                if(outPathStr!=null&&!outPathStr.equals("")){
                    outPath = new File(outPathStr,newName).getAbsolutePath();
                }
                System.out.println("===========================================================================================");
                System.out.println("[当前正在处理]"+newName);
                MergeMovieAndVoiceUtil.mergeAudioAndVideo(mp4Path,mp3Path,outPath);
                System.out.println("[处理完成]:"+newName);
            }
        }

    }
}
