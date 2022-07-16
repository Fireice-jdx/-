package cn.jdx.mp4tools.bilibili.entryjson;

public class VideoData {
    private PageData page_data;

    public PageData getPage_data() {
        return page_data;
    }

    public void setPage_data(PageData page_data) {
        this.page_data = page_data;
    }

    @Override
    public String toString() {
        return "VideoData{" +
                "pageData=" + page_data +
                '}';
    }
}
