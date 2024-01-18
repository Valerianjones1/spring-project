package com.example.springproject.service;

import com.example.springproject.entity.Gruz;
import com.example.springproject.entity.Post;
import com.example.springproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repo;

    public List<Post> listAll() {
        return repo.findAll();
    }

    public void save(Post post) {
        repo.save(post);
    }

    public Post get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
