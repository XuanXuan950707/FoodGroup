package com.example.dllo.foodgroup.strolleat.knowledge;

import java.util.List;

/**
 * Created by dllo on 16/10/29.
 */
public class KnowledgeItemBean {
    private String source;
    private String title;
    private String tail;
    private List<String> images;
    private int content_type;

    public int getContent_type() {
        return content_type;
    }

    public void setContent_type(int content_type) {
        this.content_type = content_type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
