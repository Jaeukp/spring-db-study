package com.tjn.controller.Contact;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Contact  {
    private int id;
    private String name;
    private String phone;
    private String email;
//    @Override
//    public int compareTo(Object o){
//        Contact c = (Contact) o;
//        return (int) (c.getId() - this.getId());
//    }
}
