package com.example.springproject.repository;

import com.example.springproject.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByHeader(String header);
    List<Post> findAll(Sort sort);
}
