package com.jup.myapp.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(value= "/contacts")
public class ContactController {
    // 동시처리에 대한 지원을 해주는 자료구조
    // 여러명의 유저들이 같은 데이터를 접근할 수 있음
    // 데이터베이스는 기본적으로 동시성에 대한 구현이 있음

//    Map<String, Contact> map = new ConcurrentHashMap<>();

    //@Autowired: Bean 객체를 위존성 추입
    // Bean 객체: @cConfiguation 클래스에 등록된 싱글턴 클래스로 생성된 객체

    // 싱글턴: 첫 실행시점 객체가 1번 생성됨. 이미 생성된 객체를 재사용
    // static: JUN 실행 시점에 객체를 생성

    // 메서드의 반환 타입이 싱글턴 클래스
    // @Configuration 클래스에 Bean 객체를 선어

    // 스프링 프레임워크의 DATA JPA 라이브러리
    // @Repository 인터페이스에 맞는 동적 클래스를 실행 시점에서 생성하여
    // --- Hibernate 프레워크로 구성된 클래스(insertm update...) 생성
    // @Aotowired 키워드가 있는 선엉 변수에 주입한다.

    // 의존성 주입(Dependency injection)
    // 객체를 사용하는 곳 외부에서 객체를 생성한 후에
    // 객체를 사용하는 곳에 필드, 메서드 매개변수로 넣어줌

    // 스프링프레임워크에서는
    // 1. @Configration 클래스의 @Bean 클래스를 생성
    //      -> @Bean 클래스 : 싱글턴 객체를 생성하는 메서드의 반환 클래스
    // 2. @Autowired 이노테이션 변수에 객체를 의존성 주입

     /*(Config config = new Config();
     객체를 생성
     Repository config
    @Configuration
    public class ResourceConfig {
        @Bean
         메서드의 리턴타입을 클래스의
        public Repository getReop() {
            return new MangKyuResource();
        }
    }

    public class A {
        @Autowired
        Repository repo;
        public void findAll() {
            repo.findAll();
        }

        public class B {
            @Autowired
            Repository repo;
        } */

    @Autowired
    ContactRepository repo;
//    public ContactController(ContactRepository r) {
//        this.repo = r;
//    }


    @GetMapping
    public List<Contact> getContactList() {
        // repo.findAll();: 전체 테이블 목록 조회
        // SELECT * FROM 테이블
        // repo.findAll(Sort sort): 정렬하여 전체 테이블 목록 조회
        // SELECT * FROM 테이블 ORDER BY 정렬컬럼, 정렬컬럼.....

        List<Contact> list = repo.findAll(Sort.by("email").ascending());
        return list;
    }

    // GET/contacts
//@GetMapping
//    public List<Contact> getContactList() {
////        int no = num.incrementAndGet();
//
////        map.put("asdf@gamil.com", Contact.builder().name("홍길동").phone("010-7271-1234").email("asdf@gamil.com").build());
////        map.put("qwerr@naver.com", Contact.builder().name("김민수").phone("010-5444-1234").email("qwerr@naver.com").build());
//        var list = new ArrayList<>(map.values());
////        list.sort((a, b)-> b.getId() - a.getId());
//        list.sort(Comparator.comparing(Contact::getName));
//
//        return list;
//    }


    // HTTP 1.1 POST / contacts
    // HTTP버전 메서드 리소스URL
    // : Request Line

    // Content-Type: application/json
    // Accept: */* - 서버로 부터 어떤 형식의 데이터를 받을지
    //         ex) image/jpeg, application/json, text/html, plain/text
    // : Request Header
    // : 서버의 부가정보 요청

    // {"name":"홍길동", "phone":"010-1234-5678", "email":"park@gmail.com"}
    // :Request Body(요청 본문)

    // JSON: 문자열, 자바스크립트 객체 표기법
    // Client(브라우저) Request header 에 Content-type: application/json
    // Request Body가 JSON 문자열이면

    // Server(스프링) {@RequestBody 를 메서드 매게변수에 넣어주면
    // JSON(문자열) -> JAVA객체로 변환
    @PostMapping
    public ResponseEntity<Map<String, Object>> addContact(@RequestBody Contact contact) {
        // 클라이언트에서 넘어온 JSON이 객체로 잘 변환됐는지 확인
        System.out.println(contact.getName());
        System.out.println(contact.getPhone());
        System.out.println(contact.getEmail());

        // 이메일 필수값 검증
        // 400: bad request
        if (contact.getEmail() == null || contact.getEmail().isEmpty()) {
            // 응답 객체 생성
            Map<String, Object> res = new HashMap<>();
            res.put("data", null);
            res.put("message", "[email] field is required");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }

        // 이메일 형식 검증
        // 400: bad request

        // 이메일 중복 검증
        // 409: conflict

        if(contact.getEmail() != null && repo.findById(contact.getEmail()).isPresent()) {
//        if (contact.getEmail() != null && map.get(contact.getEmail()) != null) {
            // 맵에 해당 이메일이 있음
            // 이미 있는 데이터를 클라이언트(브라우저) 보냈거나
            // 클라이언트에서 중복 데이터를 보냈거나..
            Map<String, Object> res = new HashMap<>();
            res.put("data", null);
            res.put("message", "동일한 정보가 이미 존재합니다.");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }


        // 맵에 객체 추가
//        map.put(contact.getEmail(), contact);

        // 테이블에 레코드 추가
        // key값이 테이블에 이미 있으면 update
        // 없으면 insert 구문이 실행됨.


        // Contact 테이블에 추가
        // 해당 테이블에 현재 key가 동일한 것이있으면 update(수정)
        // key가 동일한 것이 없으면
        repo.save(contact);


        // 응답 객체 생성
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", map.get(contact.getEmail()));
//        res.put("message", "created");

        // 응답 객체 생성(ResponseEntity)
        //상태코드 , 데이터, 메서지
        // 실제로 생성된 레코드(row)를 응답
        // repo.findById(PK값);
        // Optional은 null이 될 수 없음.
        Optional<Contact> savedContact =
                repo.findById(contact.getEmail());
        // 레코드가 존재하는지 여부..
        if(savedContact.isPresent()) {
            Map<String, Object> res = new HashMap<>();
            res.put("data", savedContact);
            res.put("message", "created");

            // HTTP Status Code: 201 Created
            // 리소스가 정상적으로 생성되었음.
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }

        return ResponseEntity.ok().build();
    }

//    DELETE /contacts/{email}
//           : Path(경로)Variable(변수)
//    DELTE /contacts/kdkcom@naver.com
        @DeleteMapping(value = "/{email}")
        // @PathVariable("email") : 경로 문자열{email}과 변수명 String email이 동일하면 안 써도 된다.
        public ResponseEntity removeContact(@PathVariable String email) {
            System.out.println(email);

            // 해당 키(key)의 데이터가 없으면
//        if(map.get(email) == null) {

            // PK값으로 레코드로 1건 조회해서 없으면
            if(!repo.findById(email).isPresent()){
                // 404: NOT FOUND, 해당 경로에 리소스가 없다.
                // DELETE /contacts/kdkcom@naver.com
                // Response Status Code : 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

//        // 객체(리소스-서버의램) 삭제
//        map.remove(email);
            // 레코드(리소스-데이터베이스의파일일부분) 삭제
            repo.deleteById(email);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }