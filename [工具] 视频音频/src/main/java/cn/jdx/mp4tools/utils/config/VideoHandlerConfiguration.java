package cn.jdx.mp4tools.utils.config;

public class VideoHandlerConfiguration {
    private String inputParentPath;//需要处理的父目录
    private String outputPath;//输出的路径
    private OutputName outputName;//输出文件的时候，命名规则，这个可以由用户自定义
    private boolean isJustShowName;//true:仅仅只是测试，把扫描到的文件名称输出出来

    public boolean isJustShowName() {
        return isJustShowName;
    }

    public void setJustShowName(boolean justShowName) {
        isJustShowName = justShowName;
    }

    public String getInputParentPath() {
        return inputParentPath;
    }

    public void setInputParentPath(String inputParentPath) {
        this.inputParentPath = inputParentPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public OutputName getOutputName() {
        return outputName;
    }

    public void setOutputName(OutputName outputName) {
        this.outputName = outputName;
    }
}
