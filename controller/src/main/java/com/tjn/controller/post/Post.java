package com.tjn.controller.post;
// Get /posts
// 게시글 목록이 JSON으로 나오게
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class Post {
    private long no;
    private String title;
    private String content;
    private String creatorName;
    private String createdTime;
//    private static final AtomicLong counter = new AtomicLong();
//    private Long no;
//    private String title;
//    private String content;
//    private String creatorName;
//    private long createdTime;
//    private long deletedTime;
//
//    public Post() {
//        this.no = counter.incrementAndGet();
//    }
//
//    // Getter and Setter
//    public Long getNo() {
//        return no;
//    }
//
//    public void setNo(Long no) {
//        this.no = no;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getCreatorName() {
//        return creatorName;
//    }
//
//    public void setCreatorName(String creatorName) {
//        this.creatorName = creatorName;
//    }
//
//    public long getCreatedTime() {
//        return createdTime;
//    }
//
//    public void setCreatedTime(long createdTime) {
//        this.createdTime = createdTime;
//    }
//
//    public long getDeletedTime() {
//        return deletedTime;
//    }
//
//    public void setDeletedTime(long deletedTime) {
//        this.deletedTime = deletedTime;
//    }


}