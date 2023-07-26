package com.tjn.controller.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor // 전체 필드 초기 생성자
@NoArgsConstructor // 빈 생성자
public class Contact  {
//    private int id;
    // key
    private String email;
    private String name;
    private String phone;
//    @Override
//    public int compareTo(Object o){
//        Contact c = (Contact) o;
//        return (int) (c.getId() - this.getId());
//    }
}
