package com.tjn.controller.post;
// GET / posts
// 게시글 목록이 JSON으로 나오게

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// 게시글 목록이 JSON으로 나오게
@RestController
@RequestMapping("/posts")
public class PostController {
    Map<Integer, Post> map = new ConcurrentHashMap<>();
    AtomicInteger num = new AtomicInteger(0);

    // post 목록 화면을 제작 post.html, post.js
    // fetch api를 사용하여 /posts 주소를 호출한 후
    // 배열 목록을 div(카드)로 표시한다.

//    -----------------
//    | 게시자         |
//    | ------------- |
//    | 제목(h3)       |
//    | 본문(p)        |
//    |  .....        |
//    |  .....        |
//    | ------------- |
//    | 생성시간       |
//    -----------------

    @GetMapping
    public List<Post> getPostList() {
        int no = num.incrementAndGet();
        map.put(1, Post.builder()
                .no(1)
                .creatorName("홍길동")
                .title("1Lorem, ipsum dolor.")
                .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae maiores sunt ab beatae provident? Eius non accusantium vitae dolor ipsa!")
                .createdTime(new Date().toString())
                .build());
        map.put(2, Post.builder()
                .no(2)
                .creatorName("김철수")
                .title("2Lorem, ipsum dolor.")
                .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae maiores sunt ab beatae provident? Eius non accusantium vitae dolor ipsa!")
                .createdTime(new Date().toString())
                .build());

        var list = new ArrayList<>(map.values());
        // 람다식(lambda expression)
        // 식이 1개인 함수식;
        // 매개변수 영역과 함수 본체를 화살표로 구분함
        // 함수 본체의 수식 값이 반환 값
        list.sort((a,b)-> (int)(b.getNo() - a.getNo()));

        return list;
    }

    // title, content 필수 속성
    // creatorName: 서버에서
//    @PostMapping
//    public
}

        // 람다식(lambda expression)
        // 식이 1개인 함수식;
        // 매개변수 영역과 함수 본체를 화살표로 구분함
        //

//    public List<Post> getList() {
//        list.clear();
//        list.add(new Post(1, "titl", 2303200, "park", new Date().getTime()));
//        list.add(Post.builder()
//                .title("제목")
//                .content(1)
//                .no(2)
//                .creatorName("chan")
//                .createdTime(new Date().getTime())
//                .build());
//        return list;
//        return list;
//    }
//    private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
//    private final AtomicLong deletedCounter = new AtomicLong();
//
//    @PostMapping("/posts")
//    public ResponseEntity<Post> addPost(@RequestBody Post post) {
//        // 게시글 추가
//        post.setCreatedTime(System.currentTimeMillis());
//        posts.put(post.getNo(), post);
//        return ResponseEntity.status(HttpStatus.CREATED).body(post);
//    }
//
//    @GetMapping("/posts")
//    public ResponseEntity<List<Post>> getAllPosts() {
//        // 게시글 전체 조회 (삭제되지 않은 것만)
//        List<Post> result = posts.values().stream()
//                .filter(post -> post.getDeletedTime() == 0)
//                .sorted(Comparator.comparingLong(Post::getCreatedTime).reversed())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/posts/search")
//    public ResponseEntity<List<Post>> searchPostsByTitle(@RequestParam String keyword) {
//        // 제목 기준으로 검색
//        List<Post> result = posts.values().stream()
//                .filter(post -> post.getTitle().contains(keyword) && post.getDeletedTime() == 0)
//                .sorted(Comparator.comparingLong(Post::getCreatedTime).reversed())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/posts/{no}")
//    public ResponseEntity<Post> getPostByNo(@PathVariable Long no) {
//        // 게시글 번호로 조회
//        Post post = posts.get(no);
//        if (post != null && post.getDeletedTime() == 0) {
//            return ResponseEntity.ok(post);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/posts/{no}")
//    public ResponseEntity<Post> updatePost(@PathVariable Long no, @RequestBody Post updatedPost) {
//        // 게시글 수정
//        Post post = posts.get(no);
//        if (post != null && post.getDeletedTime() == 0) {
//            post.setTitle(updatedPost.getTitle());
//            post.setContent(updatedPost.getContent());
//            return ResponseEntity.ok(post);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/posts/{no}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long no) {
//        // 게시글 삭제
//        Post post = posts.get(no);
//        if (post != null && post.getDeletedTime() == 0) {
//            post.setDeletedTime(System.currentTimeMillis());
//            deletedCounter.incrementAndGet();
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    public void init() {
//        Post post1 = new Post();
//        post1.setTitle("첫 번째 게시글");
//        post1.setContent("첫 번째 게시글의 내용");
//        post1.setCreatorName("작성자1");
//        addPost(post1);
//    }

