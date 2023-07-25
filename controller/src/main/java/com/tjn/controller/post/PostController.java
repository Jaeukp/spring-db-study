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

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    List<Post> list = new ArrayList<>();
    @GetMapping
    public List<Post> getPostList() {
        map.put(1, Post.builder().no(1).build());
        map.put(2, Post.builder().no(1).build());

        var list = new ArrayList<>(map.values());
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
        return list;
    }
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
}

