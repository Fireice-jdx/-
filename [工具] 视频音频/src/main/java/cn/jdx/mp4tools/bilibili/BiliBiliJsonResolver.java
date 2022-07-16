package cn.jdx.mp4tools.bilibili;

import cn.jdx.mp4tools.bilibili.entryjson.VideoData;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class BiliBiliJsonResolver {
    public String getVideoNameFromJson(String jsonFilePath) throws FileNotFoundException {
        Gson gson = new Gson();
        VideoData videoData = gson.fromJson(new FileReader(jsonFilePath),VideoData.class);
        return videoData.getPage_data().getPart();
    }
}
