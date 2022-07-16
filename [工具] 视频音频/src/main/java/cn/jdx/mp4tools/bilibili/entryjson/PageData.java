package cn.jdx.mp4tools.bilibili.entryjson;

public class PageData {
    private String part;

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "part='" + part + '\'' +
                '}';
    }
}
