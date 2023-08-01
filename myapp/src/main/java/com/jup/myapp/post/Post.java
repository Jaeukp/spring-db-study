package com.jup.myapp.post;
// Get /posts
// 게시글 목록이 JSON으로 나오게

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    // database의 outo-increment를 사용함
    // outo_increment: 레코드가 추가될때 자동으로 증가되는 값을 사용
    // 1, 2, 3...
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String creatorName;

    // created time bigint not null,
    // primitive Type: int, char, Long, double
    // nullable 불가, long 기본값이 0
    // 데이터베이스에 NOT NULL로 세팅해줌
    // private long createdTime;
    // 데이터베이스에 NULL을 널고 싶으면 Class 파일을 써야함
    @Column(nullable = false)
    private long createdTime;
    @Column(columnDefinition = "LONGTEXT")
    private String image;


}