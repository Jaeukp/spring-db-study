package com.jup.myapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginRepository repo;
//    @GetMapping(value = "/logins")
//    public List<Login> getLogins() {
//        List<Login> logins = repo.findAll();
//        Login login = logins.get(0);
//        System.out.println(login);
//
//        return repo.findAll();
//    }

    @Autowired
    private AuthService service;

    @PostMapping(value = "/signup")
    public ResponseEntity signup(@RequestBody Login loginCreateReq) {
        // profile 정보 생성과 login 정보 생성은 1개의 처리로 실행
        // 둘중에 하나라도 FAIL(오류)가 나면 둘다 생성이 되면 안됨.

        // insert, update, delete DML(데이터조직)
        // 데이터 조직적임에서 논리적으로 1개 묶을 수 있는 방법
        // 트랜젝션(transaction): 거래
        // Controller에서는 transaction 처리가 안 됨.

        // @Controller: 요청값 검증, 간단한 데이터조작, 적절한 응답값 반환
        // @Service: 트랜잭션처리, 외부시스템연동

        // profile, login 생성 트랜잭션 처리
        // 1. Val
        service.createIdentity(loginCreateReq);

        //201: created
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
