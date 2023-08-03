package com.jup.myapp.auth;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private LoginRepository repo;
    private ProfileRepository profileRepo;

    @Autowired
    public AuthService(LoginRepository repo, ProfileRepository profileRepo) {
        this.repo = repo;
        this.profileRepo = profileRepo;
    }

    // @Transactional: 메서드 레벨
    // JPA 데이터처리에 대해서 메서드 수준으로 트랜젝션을 처리해줌
    // 메서드 { insert, update, delete} : 1개의 tx로 묶어줌.
    // 메서드에서 예외처리가 발생하면 rollback(원상태로 복구)을 함.
    // 메서드가 정상처리되면 commit(변명)이 실행됨. DB파일에 반영
    // 테이블락(insert), 로우락(delete, update),
    // 아이솔레이션 레벨(commit, uncommited)
    @Transactional
    public void createIdentity(Login loginCreateReq) {
        // profile 정보 생성: insert
        Profile toSaveProfile = Profile.builder()
                .nickname(loginCreateReq.getProfile().getNickname())
                .email(loginCreateReq.getProfile().getEmail())
                .build();
        // insert
        Profile savedProfile = profileRepo.save(toSaveProfile);

        // login 정보 생성(profile_id를 널어서 생성): insert
        Login toSaveLogin = Login.builder()
                .password(loginCreateReq.getPassword())
                .username(loginCreateReq.getUsername())
                .profile(savedProfile)
                .build();
        repo.save(toSaveLogin);
    }
}