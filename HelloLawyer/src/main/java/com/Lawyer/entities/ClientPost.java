package com.Lawyer.entities;

import jakarta.persistence.*;

@Entity
public class ClientPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;
    private String postTitle;
    @Column(length = 500)
    private String postDescription;

    private String imgUrl;
    @ManyToOne
    private Client client;


    public ClientPost() {
    }

    public ClientPost(int pid, String postTitle, String postDescription, String imgUrl) {
        this.pid = pid;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.imgUrl = imgUrl;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "ClientPost{" +
                "pid=" + pid +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
