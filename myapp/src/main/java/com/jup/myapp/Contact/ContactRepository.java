package com.jup.myapp.Contact;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//
// <엔티티클래스, 엔티티의커터합>
public interface ContactRepository extends JpaRepository<Contact, String> {
    @Query(value = "select * " +
            "from contact " +
            "order by name asc ", nativeQuery = true)
    List<Contact> findContactsSortByName();

    @Query(value = "select * " +
            "from contact " +
            "where email = :email ", nativeQuery = true)
    Contact findContactByEmail(String email);
}
