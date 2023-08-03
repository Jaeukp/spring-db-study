package com.jup.myapp.post;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findAllByOrderByNo();
}


