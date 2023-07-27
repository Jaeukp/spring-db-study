package com.tjn.controller.post;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// GET /posts
// 게시글 목록이 JSON으로 나오게
@RestController
@RequestMapping("/posts")
public class PostController {
    // 동시성을 위한 자료 구조
    // HashMap -> ConcurrentHashMap
    // Integer -> AtomicInteger
    Map<Long, Post> map = new ConcurrentHashMap<>();
    AtomicLong num = new AtomicLong(0);

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
//        // 증가시키고 값 가져오기
//        int no = num.incrementAndGet();
//        map.put(no, Post.builder()
//                        .no(no)
//                        .creatorName("홍길동")
//                        .title("1Lorem, ipsum dolor.")
//                        .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae maiores sunt ab beatae provident? Eius non accusantium vitae dolor ipsa!")
//                        .createdTime(new Date().getTime())
//                        .build());
//
//        no = num.incrementAndGet();
//        map.put(no, Post.builder()
//                        .no(no)
//                        .creatorName("홍길동")
//                        .title("2Lorem, ipsum dolor.")
//                        .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae maiores sunt ab beatae provident? Eius non accusantium vitae dolor ipsa!")
//                        .createdTime(new Date().getTime())
//                        .build());

        var list = new ArrayList<>(map.values());
        // 람다식(lambda expression)
        // 식이 1개인 함수식;
        // 매개변수 영역과 함수 본체를 화살표로 구분함
        // 함수 본체의 수식 값이 반환 값
        list.sort((a,b)-> (int)(b.getNo() - a.getNo()));

        return list;
    }


    //    -- 받는 정보
//    title, content
//    -> null 또는 없으면 bad-request 응답

//    -- 서버에 생성
//    no = num.incrementAndGet();
//    creatorName = "John Doe"
//    createdTime = new Date().getTime()

    // title, content 필수 속성
    // creatorName: 서버에서 가짜데이터로 넣음
    @PostMapping
    public ResponseEntity<Map<String, Object>> addPost(@RequestBody Post post) {
//     1. 입력값 검증(title, content)

//      -> 입력값 오류(빈 값)가 있으면 400 에러 띄움
        if(post.getTitle() == null || post.getContent() == null || post.getContent().isEmpty() || post.getTitle().isEmpty()){
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "[title] and [content] is Required Field");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

//     2. 채번: 번호를 딴다(1 .. 2, 3....)
        long no = num.incrementAndGet();
        post.setNo(no);

//     3. 번호(no), 시간값(createdTime) 게시자이름(creatorName) 요청 객체에 설정
//        post.setCreatorName("Dodo");
        post.setCreatedTime(new Date().toString());

//     4. 맵에 추가
        map.put(no, post);
        System.out.println(post);

//     5. 생성된 객체를 맵에서 찾아서 반환, 201
        Map<String, Object> res = new HashMap<>();
        System.out.println(post.getNo());
        res.put("data", map.get(no));
        res.put("message", "created");

        System.out.println(ResponseEntity.status(HttpStatus.CREATED).body(res));
//        return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}




//package com.tjn.controller.post;
//// GET / posts
//// 게시글 목록이 JSON으로 나오게
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
//// 게시글 목록이 JSON으로 나오게
//@RestController
//@RequestMapping("/posts")
//public class PostController {
//    Map<Integer, Post> map = new ConcurrentHashMap<>();
////    AtomicInteger num = new AtomicInteger(0);
//
//    private AtomicInteger num = new AtomicInteger(0);
//
//
//    @PostMapping
//    public ResponseEntity<Post> addPost(@ModelAttribute Post request) {
//        // 1. 입력값 검증(title, content)
//        if (request.getTitle() == null || request.getContent() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // 2. 채번: 번호를 딴다(1 .. 2, 3....)
//        int no = num.incrementAndGet();
//
//        // 3. 번호(no), 시간값(createdTime), 게시자이름(creatorName) 설정
//        String creatorName = "John Doe";
//        long createdTime = new Date().getTime();
//        request.setNo(no);
//        request.setCreatedTime(String.valueOf(createdTime));
//        request.setCreatorName(creatorName);
//
//        // 4. 생성된 객체를 반환
//        return ResponseEntity.ok(request);
//    }
////    @PostMapping()
////    public ResponseEntity<Map<String, Object>> addPost(@RequestBody Map<String, String> request) {
////        String title = request.get("title");
////        String content = request.get("content");
////        if (title == null || content == null) {
////            return ResponseEntity.badRequest().build();
////        }
////
////        int no = num.incrementAndGet();
////
////        String creatorName = "John Doe";
////        long createdTime = new Date().getTime();
////        request.put("no", String.valueOf(no));
////        request.put("createdTime", String.valueOf(createdTime));
////        request.put("creatorName", creatorName);
////
////        Map<String, Object> response = new HashMap<>(request);
////        return ResponseEntity.ok(response);
////    }
//
//    @GetMapping
//    public List<Post> getPostList() {
////        String title = request.get("title");
//
////        int no = num.incrementAndGet();
////        map.put(1, Post.builder()
////                .no(1)
////                .creatorName("홍길동")
////                .title("1Lorem, ipsum dolor.")
////                .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae maiores sunt ab beatae provident? Eius non accusantium vitae dolor ipsa!")
////                .createdTime(new Date().toString())
////                .build());
////        map.put(2, Post.builder()
////                .no(2)
////                .creatorName("김철수")
////                .title("2Lorem, ipsum dolor.")
////                .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae maiores sunt ab beatae provident? Eius non accusantium vitae dolor ipsa!")
////                .createdTime(new Date().toString())
////                .build());
//
//
//        var list = new ArrayList<>(map.values());
//        // 람다식(lambda expression)
//        // 식이 1개인 함수식;
//        // 매개변수 영역과 함수 본체를 화살표로 구분함
//        // 함수 본체의 수식 값이 반환 값
//        list.sort((a, b) -> (int) (b.getNo() - a.getNo()));
//
//        return list;
//    }
//}


//        -- 받는 정보
//    title, content
//    -> null 또는 없으면 bad-request 응답
//
//    -- 서버에 생성
//    no = num.incrementAndGet();
//    creatorName = "John Doe"
//    createdTime = new Date().getTime()
//
//     title, content 필수 속성
//     creatorName: 서버에서 가짜데이터로 넣음
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> addPost(@RequestBody Post post) {
////         1. 입력값 검증(title, content)
//        if (post.getTitle() == null || post.getContent() == null) {
//
//        }
////
//
//         2.  채번: 번호를 딴다(1 .. 2, 3....)
//        int no = num.incrementAndGet();
//
//         3. 번호(no), 시간값(createdTime) 게시자이름(creatorName) 요청 객체에 설정
//         (set필드명(...))
//
//         4. 맵에 추가 (서버에서 생성된 값을 설정)
//
//         5. 생성된 객체를 맵에서 찾아서 반환

//        return
//    }
//}


//    public List<Post> getList() {
//        list.clear();
//        list.add(new Post(1, "title", 2303200, "park", new Date().getTime()));
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

