package com.jup.myapp.Contact;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor // 전체 필드 초기 생성자
@NoArgsConstructor // 빈 생성자
@Entity
// @Entity는 기본적으로 클래스명(퍼스컬케이스) -> 테이블명(스네이크케이스) 맵핑
// class: ContactAtivity -> table: contact_activity

// ORM(Object Relaship Mapping)
// : 소프트웨어의 객체를 데이터베이스 테이블로 맵핑하는 방법
// : 사용 이유
// : 데이터베이스의 의돈도를 낯추는 방법
// : 객체지향적인 원래의 소프트웨어 개발방법을 사용하자(코드위주로)

// JPA(Java persistent API)
// : 내부적으로 Mibernate를 이용하여 구현된 interface를 제공
// : 자바에서 ORN을 처리하는 표준방법
public class Contact  {
//    private int id;

    // @Id: 엔티티의 PK(primary key)를 지정
    // Primary key: 유일한 속성 값이며, 모든 속성 값이 PK에 종속됨. 대표성
    // PK: 유일성+대표성이 만족이 되어야함.

    // PK 컬럼 및 제약조건 설정
    @Id
    // key
    private String email; // 계정 Id, 인터넷세계의 집수소(불변)

    // 제약조거느 NOT NULL
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone;

    // 컬럼의 데이터 타입을 변경
    // 컬럼크기 1024byte = 1024 = 1mb * 20 = 20mb
    @Column(length = 1024 * 1024 * 20) // MySQL 에서는 longtext로 바뀜
    // 파일을 base64 data-url 문자열로 저장
    private String image;
//    @Override
//    public int compareTo(Object o){
//        Contact c = (Contact) o;
//        return (int) (c.getId() - this.getId());
//    }
}
