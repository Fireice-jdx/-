package cn.jdx.mp4tools;

import cn.jdx.mp4tools.bilibili.BiliBiliVideoHandler;
import cn.jdx.mp4tools.utils.config.VideoHandlerConfiguration;

public class Main {

    //除了指定位置的符号数组以外，其他的全部拼接
    public static String getSplitAllBut(int nuIndex,String []arr){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <arr.length ; i++) {
            if (nuIndex!=i){
                stringBuilder.append(arr[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {

        VideoHandlerConfiguration configuration = new VideoHandlerConfiguration();
        configuration.setInputParentPath("D:\\test\\71252572");
        configuration.setJustShowName(false);
        configuration.setOutputPath("D:\\test\\Spring");

        int index[] = {0};
        configuration.setOutputName(originName -> {
            index[0]++;
            String indexStr = "";
            if (index[0]<10){
                indexStr="0"+index[0];
            }else{
                indexStr=index[0]+"";
            }
            String[] split = originName.split("-");
//                int startIndex = originName.indexOf("-");
//                String substring = originName.substring(startIndex, originName.length() - startIndex);
//
//                if (split.length>=2){
//                    String str = split[1];
//                    if (str!=null&&!str.equals("")){
//                        return indexStr+"."+split[1];
//                    }
//                }
            return indexStr+"."+getSplitAllBut(0,split);
        });
        BiliBiliVideoHandler handler = new BiliBiliVideoHandler(configuration);
        handler.merge();//开始合并处理

    }
}
