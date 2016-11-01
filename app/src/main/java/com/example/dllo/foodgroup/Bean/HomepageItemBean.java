package com.example.dllo.foodgroup.Bean;

import com.example.dllo.foodgroup.strolleat.StrollEatFragment;

/**
 * Created by dllo on 16/10/27.
 */
public class HomepageItemBean {
    String title;
    String cardImage;
    String publisher;
    String publisherImage;
    String description;
    String likeCt;
    String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherImage() {
        return publisherImage;
    }

    public void setPublisherImage(String publisherImage) {
        this.publisherImage = publisherImage;
    }

    public String getLikeCt() {
        return likeCt;
    }

    public void setLikeCt(String likeCt) {
        this.likeCt = likeCt;
    }
}
